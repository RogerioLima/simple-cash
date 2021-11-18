package com.br.simplecash.core.interactor.account;

import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.Account;
import com.br.simplecash.core.domain.User;

@Component
public interface AccountCreateInteractor {
	public User execute(Long userCode, Account account);
}
