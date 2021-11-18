package com.br.simplecash.entrypoint.category.response;

import java.util.ArrayList;
import java.util.List;

import com.br.simplecash.core.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CategoryCreateResponse {
	private String userName;
	private List<CategoryRequest> categories = new ArrayList<>();
	
	public CategoryCreateResponse fromUser(User user) {
		this.userName = user.getName();
		
		if (user.getCategories() != null) {
			user.getCategories().forEach(category -> {
				CategoryRequest categoryRequest = new CategoryRequest(category.getCode(), category.getName(), category.getType());
				this.categories.add(categoryRequest);
			});
		}
		
		return this;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	private class CategoryRequest {
		private Long code;
		private String name;
		private String type;
	}
}
