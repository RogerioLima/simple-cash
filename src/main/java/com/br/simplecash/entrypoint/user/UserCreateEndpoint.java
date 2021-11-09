package com.br.simplecash.entrypoint.user;

import javax.validation.Valid;

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

@RestController
@RequestMapping("/api/v1/users")
public class UserCreateEndpoint {
	private UserCreateInteractor userInteractor;
	
	public UserCreateEndpoint(UserCreateInteractor userInteractor) {
		this.userInteractor = userInteractor;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserCreateResponse createUser(@RequestBody @Valid UserCreateRequest body) {
		User userCreated = userInteractor.create(body.toUser());
		return new UserCreateResponse().fromUser(userCreated);
	}
}
