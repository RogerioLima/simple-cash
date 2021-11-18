package com.br.simplecash.core.gateway.category;

import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.Category;
import com.br.simplecash.core.domain.User;

@Component
public interface CategoryCreateGateway {
	public User execute(Long userCode, Category category);
}
