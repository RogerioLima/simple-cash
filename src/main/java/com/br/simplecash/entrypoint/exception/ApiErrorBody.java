package com.br.simplecash.entrypoint.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class ApiErrorBody {
	private int status;
	private String title;
	private String detail;
	private List<Field> fields;
	
	@Data
	@Builder
	public static class Field {
		private String name;
		private String message;
	}
}
