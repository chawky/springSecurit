package jwt;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint{
	private static final Logger logger = Logger.getLogger(AuthEntryPointJwt.class.getName());
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.info(authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"error: Unauthorized");
		
	}

}
