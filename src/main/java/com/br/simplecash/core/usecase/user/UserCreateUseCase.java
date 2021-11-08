package com.br.simplecash.core.usecase.user;

import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.interactor.user.UserCreateInteractor;

@Component
public class UserCreateUseCase implements UserCreateInteractor {

	@Override
	public User create(User user) {
		return user;
	}
}
