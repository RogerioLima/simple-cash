package com.br.simplecash.core.gateway.user;

import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.User;

@Component
public interface UserCreateGateway {
	User create(User user);
}
