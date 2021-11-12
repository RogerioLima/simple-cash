package com.br.simplecash.core.usecase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.gateway.user.UserCreateGateway;
import com.br.simplecash.core.interactor.user.UserCreateInteractor;
import com.br.simplecash.core.util.encryption.EncryptDecrypt;

@Component
public class UserCreateUseCase implements UserCreateInteractor {

	@Autowired
	private EncryptDecrypt encrypt;
	
	@Autowired
	private UserCreateGateway userCreateGateway;
	
	@Override
	public User create(User user) {
		byte[] encryptedPassword = encrypt.encrypt(user.getPassword());
		user.setEncryptedPassword(encryptedPassword);
		
		return userCreateGateway.create(user);
	}
}
