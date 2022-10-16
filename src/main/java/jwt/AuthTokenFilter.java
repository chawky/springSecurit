package jwt;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import security.securityServices.userDetailsServiceImpl;

public class AuthTokenFilter extends OncePerRequestFilter {
	private static final Logger logger = Logger.getLogger(AuthTokenFilter.class.getName());
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private userDetailsServiceImpl detailsServiceImpl;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = parseJwt(request);
			if(java.util.Objects.nonNull(jwt)&&jwtUtils.validateToken(jwt)) {
				String userName = jwtUtils.getUserNameFromToken(jwt);
				UserDetails userDetails = detailsServiceImpl.loadUserByUsername(userName);
				UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		
	}


	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if(StringUtils.hasText(headerAuth)&&headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7,headerAuth.length());
		}
		return null;
	}

}
