package com.cairone.sdlpocjpa.dtos;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImplDto implements UserDetails {

	private String username = null;
	private String password = null;
	private List<GrantedAuthority> grantedAuthorities = null;
	
	public UserDetailsImplDto(String username, String password, List<GrantedAuthority> grantedAuthorities) {
		super();
		this.username = username;
		this.password = password;
		this.grantedAuthorities = grantedAuthorities;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
