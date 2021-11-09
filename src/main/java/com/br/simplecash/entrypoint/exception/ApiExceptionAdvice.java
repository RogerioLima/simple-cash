package com.br.simplecash.entrypoint.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionAdvice extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exceptionHadle(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		ApiErrorBody errorBody = ApiErrorBody.builder()
				                       .status(status.value())
				                       .title("Erro interno no Servidor")
				                       .build();
		
		log.error(ex.getMessage(), ex);
		
		return handleExceptionInternal(ex, errorBody, new HttpHeaders(), status, request);
	}
	
	private ResponseEntity<Object> handleValidationInternal(
    Exception ex,
    HttpHeaders headers,
    HttpStatus status,
    WebRequest request,
    BindingResult bindingResult
  ) {
		List<ApiErrorBody.Field> fields = new ArrayList<>();
		
		bindingResult.getAllErrors().forEach(error -> {
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			String fieldName = error.getObjectName();
			
			if (error instanceof FieldError) {
				fieldName = ((FieldError) error).getField();
			}
			
			ApiErrorBody.Field field = ApiErrorBody.Field.builder()
					                          .message(message)
					                          .name(fieldName)
					                          .build();
			
			fields.add(field);
		});
		
		ApiErrorBody errorBody = ApiErrorBody.builder()
        .status(status.value())
        .title("Dados inválidos")
        .detail("Um ou mais campos estão inválidos.")
        .fields(fields)
        .build();
		
		return handleExceptionInternal(ex, errorBody, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
	}
	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		return super.handleBindException(ex, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex,
			Object body,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request
	) {
		boolean bodyIsString = (body != null) && (body instanceof String);
		
		if (body == null || bodyIsString) {
			String title = bodyIsString ? (String) body : status.getReasonPhrase();
					
			body = ApiErrorBody.builder()
					            .title(title)
					            .status(status.value())
					            .detail(ex.getMessage())
					            .build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
}
