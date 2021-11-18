package com.br.simplecash.core.usecase.account;

import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.Account;
import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.gateway.account.AccountCreateGateway;
import com.br.simplecash.core.interactor.account.AccountCreateInteractor;

@Component
public class AccountCreateUseCase implements AccountCreateInteractor {
	
	private final AccountCreateGateway accountCreateGateway;
	
	public AccountCreateUseCase(AccountCreateGateway accountCreateGateway) {
		this.accountCreateGateway = accountCreateGateway;
	}

	@Override
	public User execute(Long userCode, Account account) {
		return accountCreateGateway.execute(userCode, account);
	}
}
