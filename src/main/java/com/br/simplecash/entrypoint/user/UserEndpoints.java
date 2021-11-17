package com.br.simplecash.entrypoint.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.interactor.user.UserCreateInteractor;
import com.br.simplecash.entrypoint.user.request.UserCreateRequest;
import com.br.simplecash.entrypoint.user.response.UserCreateResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/users")
public class UserEndpoints {
	
	@Autowired
	private UserCreateInteractor userInteractor;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar novo usuário")
	public UserCreateResponse createUser(@RequestBody @Valid UserCreateRequest body) {
		User userCreated = userInteractor.execute(body.toUser());
		return new UserCreateResponse().fromUser(userCreated);
	}
}
