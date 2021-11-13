package com.br.simplecash.provider.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.exception.DuplicatedException;
import com.br.simplecash.core.gateway.user.UserCreateGateway;
import com.br.simplecash.provider.repository.user.repository.UserRepository;
import com.br.simplecash.provider.repository.user.tables.UserTable;

@Service
public class UserCreateGatewayImpl implements UserCreateGateway {
	@Autowired
	private UserRepository userRepository;
	
	public User create(User user) {
		try {
			UserTable userCreated = userRepository.save(new UserTable().fromUser(user));
			return userCreated.toUser();
		} catch (DataIntegrityViolationException ex) {
			throw new DuplicatedException(String.format("O e-mail %s já está cadastrado no sistema", user.getEmail()));
		}
	}
}
