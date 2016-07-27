package com.cairone.sdlpocjpa.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class HttpAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler /* SavedRequestAwareAuthenticationSuccessHandler*/ {
	
	@Autowired Logger logger = null;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		
		logger.info("Authentication success");
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		clearAuthenticationAttributes(request);
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
