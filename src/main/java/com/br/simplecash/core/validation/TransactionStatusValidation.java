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
@Constraint(validatedBy = TransactionStatusValidator.class)
@NotBlank
public @interface TransactionStatusValidation {
	String message() default "Status inválido.\nOs status permitidos são: PENDING e PAID";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}