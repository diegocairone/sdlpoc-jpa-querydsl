package com.cairone.sdlpocjpa.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class HttpAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired Logger logger = null;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
		logger.info("Authentication failure");
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		
        PrintWriter writer = response.getWriter();
        writer.write(exception.getMessage());
        writer.flush();
	}
}
