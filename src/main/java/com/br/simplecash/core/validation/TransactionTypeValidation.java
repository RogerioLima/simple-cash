package com.br.simplecash.core.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = TransactionTypeValidator.class)
@NotBlank
public @interface TransactionTypeValidation {
	String message() default "Tipo da transação inválido.\nOs tipos permitidos são: EXPENSE e INCOME";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
