package com.br.simplecash.entrypoint.transaction;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.simplecash.core.interactor.transaction.TransactionCreateInteractor;
import com.br.simplecash.entrypoint.transaction.request.TransactionCreateRequest;
import com.br.simplecash.entrypoint.transaction.response.TransactionCreateResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/users/{userCode}/transactions")
public class TransactionEndpoints {
	private final TransactionCreateInteractor transactionCreateInteractor;
	
	public TransactionEndpoints(TransactionCreateInteractor transactionCreateInteractor) {
		this.transactionCreateInteractor = transactionCreateInteractor;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Criar uma nova transação")
	public TransactionCreateResponse createTransaction(
			@PathVariable Long userCode,
			@RequestBody @Valid TransactionCreateRequest transactionRequest
	) {
		var transactionCreated = transactionCreateInteractor.execute(userCode, transactionRequest.toTransaction(userCode));
		
		return new TransactionCreateResponse().fromTransaction(transactionCreated);
	}
}
