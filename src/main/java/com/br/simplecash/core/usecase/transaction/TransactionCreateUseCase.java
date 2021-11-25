package com.br.simplecash.core.usecase.transaction;

import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.Transaction;
import com.br.simplecash.core.interactor.transaction.TransactionCreateInteractor;

@Component
public class TransactionCreateUseCase implements TransactionCreateInteractor {
	@Override
	public Transaction execute(Long userCode, Transaction newTransaction) {
		// TODO implementar regras de negocio para criação da transação
		return newTransaction;
	}
}
