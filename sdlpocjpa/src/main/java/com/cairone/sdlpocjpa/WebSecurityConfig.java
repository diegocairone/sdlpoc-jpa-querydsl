package com.cairone.sdlpocjpa;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.cairone.sdlpocjpa.auth.HttpAuthenticationEntryPoint;
import com.cairone.sdlpocjpa.services.AppUserDetailsService;

@Configuration @EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private AppUserDetailsService appUserDetailsService = null;
	
	@Autowired private HttpAuthenticationEntryPoint httpAuthenticationEntryPoint = null;
	
	@Autowired private Logger logger = null;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/odata/PoCService.svc/$metadata").permitAll()
	    	.anyRequest()
	    	.fullyAuthenticated()
	    	.and()
	    .httpBasic()
	    	.authenticationEntryPoint(httpAuthenticationEntryPoint)
			.and()
		.csrf().disable()
	    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  }
		
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
    }
	
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		return new AuthenticationProvider() {
			
			@Override
			public boolean supports(Class<?> authentication) {
				return true;
			}
			
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				
				String username = authentication.getName();
		        String password = authentication.getCredentials().toString();
		        
				UserDetails userDetails = appUserDetailsService.loadUserByUsername(username);
				
				if(userDetails == null) {
					throw new BadCredentialsException("El USUARIO no existe!");
				}
				
				if(!userDetails.getPassword().equals(authentication.getCredentials().toString())) {
					throw new BadCredentialsException("La contrasena es incorrecta!");
				}
				
				// *** NOTE: http://docs.spring.io/spring-security/site/docs/current/reference/html/concurrency.html
				SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL);
				SecurityContext sc = SecurityContextHolder.getContext();
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
				sc.setAuthentication(usernamePasswordAuthenticationToken);
				
				return usernamePasswordAuthenticationToken;
			}
		};
	}
	
}
