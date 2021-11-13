package com.br.simplecash.core.usecase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.gateway.user.UserCreateGateway;
import com.br.simplecash.core.interactor.user.UserCreateInteractor;

@Component
public class UserCreateUseCase implements UserCreateInteractor {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserCreateGateway userCreateGateway;
	
	@Override
	public User create(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		
		return userCreateGateway.create(user);
	}
}
