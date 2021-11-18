package com.br.simplecash.entrypoint.category.request;

import javax.validation.constraints.NotBlank;

import com.br.simplecash.core.domain.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryCreateRequest {
	@NotBlank
	private String name;
	
	@NotBlank
	private String type;
	
	public Category toCategory() {
		return Category.builder()
                   .name(this.name)
                   .type(this.type)
                   .build();
	}
}
