package com.br.simplecash.entrypoint.account;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.interactor.account.AccountCreateInteractor;
import com.br.simplecash.entrypoint.account.request.AccountCreateRequest;
import com.br.simplecash.entrypoint.account.response.AccountCreateResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/users/{userCode}")
public class AccountEndpoints {
	private final AccountCreateInteractor accountCreateInteractor;
	
	public AccountEndpoints(AccountCreateInteractor accountCreateInteractor) {
		this.accountCreateInteractor = accountCreateInteractor;
	}
	
	@PostMapping("/accounts")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar nova conta")
	public AccountCreateResponse createAccount(
			@PathVariable Long userCode,
			@RequestBody @Valid AccountCreateRequest body
	) {
		User userCreated = accountCreateInteractor.execute(userCode, body.toAccount());
		return new AccountCreateResponse().fromUser(userCreated);
	}
}
