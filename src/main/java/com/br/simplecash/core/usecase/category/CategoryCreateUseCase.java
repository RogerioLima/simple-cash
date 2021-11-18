package com.br.simplecash.core.usecase.category;

import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.Category;
import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.gateway.category.CategoryCreateGateway;
import com.br.simplecash.core.interactor.category.CategoryCreateInteractor;

@Component
public class CategoryCreateUseCase implements CategoryCreateInteractor {
	private final CategoryCreateGateway categoryCreateGateway;
	
	public CategoryCreateUseCase(CategoryCreateGateway categoryCreateGateway) {
		this.categoryCreateGateway = categoryCreateGateway;
	}

	@Override
	public User execute(Long userCode, Category category) {
		return categoryCreateGateway.execute(userCode, category);
	}	
}
