package com.br.simplecash.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.br.simplecash.core.domain.TransactionStatus;

public class TransactionStatusValidator implements ConstraintValidator<TransactionStatusValidation, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			String status = value.toUpperCase();
			boolean statusIsPending = TransactionStatus.PENDING.toString().equals(status);
			boolean statusIsPaid = TransactionStatus.PAID.toString().equals(status);
			
			if (statusIsPending || statusIsPaid) {
				return true;
			}
			
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
