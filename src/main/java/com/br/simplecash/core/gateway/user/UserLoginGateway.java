package com.br.simplecash.core.gateway.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public interface UserLoginGateway extends UserDetailsService {
	UserDetails loadUserByUsername(String email);
}
