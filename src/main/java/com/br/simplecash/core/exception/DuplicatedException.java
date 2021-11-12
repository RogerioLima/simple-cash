package com.br.simplecash.core.exception;

public class DuplicatedException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public DuplicatedException(String message) {
		super(message);
	}
}
