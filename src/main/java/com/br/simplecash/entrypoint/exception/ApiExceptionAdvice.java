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

import com.br.simplecash.core.exception.DuplicatedException;
import com.br.simplecash.core.exception.NotFoundException;
import com.br.simplecash.core.exception.UnAuthorizeException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ApiExceptionAdvice extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> entityNotFoundExceptionHandle(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ApiErrorBody errorBody = buildErrorBody("Não encontrado", status.value(), ex.getMessage(), null);
		
		return handleExceptionInternal(ex, errorBody, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(UnAuthorizeException.class)
	public ResponseEntity<Object> unauthorizeExceptionHandle(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		ApiErrorBody errorBody = buildErrorBody("Não autorizado", status.value(), ex.getMessage(), null);
		
		log.error(ex.getMessage(), ex);
		
		return handleExceptionInternal(ex, errorBody, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(DuplicatedException.class)
	public ResponseEntity<Object> duplicatedExceptionHandle(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;		
		ApiErrorBody errorBody = buildErrorBody("Valores duplicados", status.value(), ex.getMessage(), null);
		
		log.error(ex.getMessage(), ex);
		
		return handleExceptionInternal(ex, errorBody, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exceptionHadle(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ApiErrorBody errorBody = buildErrorBody("Erro interno no Servidor", status.value(), ex.getMessage(), null);
		
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

		ApiErrorBody errorBody = buildErrorBody("Dados inválidos", status.value(), "Um ou mais campos estão inválidos.", fields);
		
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
			body = buildErrorBody(title, status.value(), ex.getMessage(), null);
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private ApiErrorBody buildErrorBody(String title, int status, String detail, List<ApiErrorBody.Field> fields) {
		return ApiErrorBody.builder()
              .title(title)
              .status(status)
              .detail(detail)
              .fields(fields)
              .build();
	}
}
