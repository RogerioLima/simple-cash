package com.br.simplecash.core.validation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<Date, String> {
	@Override
	public boolean isValid(String date, ConstraintValidatorContext context) {
		try {
			LocalDate.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
