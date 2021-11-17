package com.br.simplecash.core.exception;

public class UnAuthorizeException extends BusinessException {
	private static final long serialVersionUID = 1L;
	
	public UnAuthorizeException(String message) {
		super(message);
	}
}
