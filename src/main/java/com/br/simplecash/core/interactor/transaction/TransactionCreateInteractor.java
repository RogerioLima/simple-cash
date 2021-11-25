package com.br.simplecash.core.interactor.transaction;

import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.Transaction;

@Component
public interface TransactionCreateInteractor {
	public Transaction execute(Long userCode, Transaction newTransaction);
}
