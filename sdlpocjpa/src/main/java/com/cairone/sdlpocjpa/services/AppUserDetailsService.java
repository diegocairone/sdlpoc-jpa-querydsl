package com.cairone.sdlpocjpa.services;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.cairone.sdlpocjpa.dtos.UserDetailsImplDto;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(username.equals("admin")) {
			return new UserDetailsImplDto("admin", "qwerty", Arrays.asList(new SimpleGrantedAuthority("ADMINISTRADOR"), new SimpleGrantedAuthority("USUARIO")));
		}
		
		if(username.equals("usuario")) {
			return new UserDetailsImplDto("usuario", "asdfgh", Arrays.asList(new SimpleGrantedAuthority("USUARIO")));
		}
		
		throw new UsernameNotFoundException("El usuario no existe!");
	}
}
