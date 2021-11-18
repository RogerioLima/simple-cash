package com.br.simplecash.provider.account;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.Account;
import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.exception.DuplicatedException;
import com.br.simplecash.core.exception.NotFoundException;
import com.br.simplecash.core.gateway.account.AccountCreateGateway;
import com.br.simplecash.provider.account.repository.tables.AccountTable;
import com.br.simplecash.provider.user.repository.UserRepository;
import com.br.simplecash.provider.user.repository.tables.UserTable;

@Component
public class AccountCreateProvider implements AccountCreateGateway {
	
	private final UserRepository userRepository;
	
	public AccountCreateProvider(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User execute(Long userCode, Account account) {
		try {
			UserTable userTable = userRepository.getById(userCode);
			
			AccountTable accountTable = new AccountTable().fromAccount(account);
			
			userTable.getAccounts().add(accountTable);
			
			UserTable userUpdated = userRepository.saveAndFlush(userTable);
			
			return userUpdated.toUser();
		} catch (EntityNotFoundException e) {
			throw new NotFoundException(String.format("Usuário não encontrado para o código %s", userCode));
		} catch (DataIntegrityViolationException e) {
			throw new DuplicatedException(String.format("A conta `%s` já está cadastrada para este usuário", account.getName()));
		}
	}
}
