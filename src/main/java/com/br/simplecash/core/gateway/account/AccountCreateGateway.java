package com.br.simplecash.core.gateway.account;

import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.Account;
import com.br.simplecash.core.domain.User;

@Component
public interface AccountCreateGateway {
	public User execute(Long userCode, Account account);
}
