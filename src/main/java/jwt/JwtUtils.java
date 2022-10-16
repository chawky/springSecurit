package jwt;

import java.util.Date;
import java.util.logging.Logger;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import security.securityServices.UserDetailsImpl;

@Component
public class JwtUtils {

	private static final Logger logger = Logger.getLogger(JwtUtils.class.getName());
	
	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal() ;
		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS512, "secret")
				.compact();

		
	}
	
	
	public String getUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody().getSubject();
		
	}
	public Boolean validateToken(String token) {
		try {
			 Jwts.parser().setSigningKey("secret").parseClaimsJws(token);
			return true;
		}catch (SignatureException e) {
			logger.info(e.getMessage());
		}
		catch (MalformedJwtException e) {
			logger.info(e.getMessage());
		}
		catch (ExpiredJwtException e) {
			logger.info(e.getMessage());
		}
		catch (UnsupportedJwtException e) {
			logger.info(e.getMessage());
		}
		catch (IllegalArgumentException e) {
			logger.info(e.getMessage());
		}
		return false;

		
	}

}
