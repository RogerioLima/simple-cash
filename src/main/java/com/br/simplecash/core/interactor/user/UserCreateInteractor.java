package com.br.simplecash.core.interactor.user;

import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.User;

@Component
public interface UserCreateInteractor {
	User execute(User user);
}
