package com.br.simplecash.provider.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.gateway.user.UserLoginGateway;
import com.br.simplecash.provider.repository.user.UserRepository;
import com.br.simplecash.provider.repository.user.tables.UserTable;

@Component
public class UserLoginProvider implements UserLoginGateway {
	private final UserRepository userRepository;
	
	public UserLoginProvider(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserTable> userTable = userRepository.findByEmail(email);
		
		if (userTable.isEmpty()) {
			throw new UsernameNotFoundException("Usuario [" + email + "] não encontrado");
		}
		
		User user = userTable.get().toUser();
		
		return new UserDetail(user);
	}
}
