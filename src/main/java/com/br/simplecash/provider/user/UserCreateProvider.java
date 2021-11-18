package com.br.simplecash.provider.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.exception.DuplicatedException;
import com.br.simplecash.core.gateway.user.UserCreateGateway;
import com.br.simplecash.provider.user.repository.UserRepository;
import com.br.simplecash.provider.user.repository.tables.UserTable;

@Service
public class UserCreateProvider implements UserCreateGateway {
	private final UserRepository userRepository;
	
	public UserCreateProvider(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User execute(User user) {
		try {
			UserTable userCreated = userRepository.saveAndFlush(new UserTable().fromUser(user));
			return userCreated.toUser();
		} catch (DataIntegrityViolationException ex) {
			ex.printStackTrace();
			throw new DuplicatedException(String.format("O e-mail %s já está cadastrado no sistema", user.getEmail()));
		}
	}
}
