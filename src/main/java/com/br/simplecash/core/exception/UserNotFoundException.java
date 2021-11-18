package com.br.simplecash.core.exception;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Long userCode) {
		super(String.format("Usuário não encontrado para o código %s", userCode));
	}
}
