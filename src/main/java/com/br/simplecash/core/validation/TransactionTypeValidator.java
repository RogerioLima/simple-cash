package com.br.simplecash.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.br.simplecash.core.domain.TransactionTypes;

public class TransactionTypeValidator implements ConstraintValidator<TransactionTypeValidation, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			String type = value.toUpperCase();
			
			boolean typeIsExpense = TransactionTypes.EXPENSE.toString().equals(type);
			boolean typeIsIncome = TransactionTypes.INCOME.toString().equals(type);
			
			if (typeIsExpense || typeIsIncome) {
				return true;
			}
			
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
