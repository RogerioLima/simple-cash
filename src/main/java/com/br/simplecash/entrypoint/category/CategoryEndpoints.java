package com.br.simplecash.entrypoint.category;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.interactor.category.CategoryCreateInteractor;
import com.br.simplecash.entrypoint.category.request.CategoryCreateRequest;
import com.br.simplecash.entrypoint.category.response.CategoryCreateResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/users/{userCode}")
public class CategoryEndpoints {
	private final CategoryCreateInteractor categoryCreateInteractor;

	public CategoryEndpoints(CategoryCreateInteractor categoryCreateInteractor) {
		this.categoryCreateInteractor = categoryCreateInteractor;
	}
	
	@PostMapping("/categories")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Cadastrar nova categoria")
	public CategoryCreateResponse createCategory(
		@PathVariable Long userCode, 
		@RequestBody @Valid CategoryCreateRequest categoryRequest
	) {
		User userWithCategoryCreated = categoryCreateInteractor.execute(userCode, categoryRequest.toCategory());
		return new CategoryCreateResponse().fromUser(userWithCategoryCreated);
	}
}
