package com.br.simplecash.provider.category;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.br.simplecash.core.domain.Category;
import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.exception.DuplicatedException;
import com.br.simplecash.core.exception.UserNotFoundException;
import com.br.simplecash.core.gateway.category.CategoryCreateGateway;
import com.br.simplecash.provider.category.repository.tables.CategoryTable;
import com.br.simplecash.provider.user.repository.UserRepository;
import com.br.simplecash.provider.user.repository.tables.UserTable;

@Component
public class CategoryCreateProvider implements CategoryCreateGateway {
	private final UserRepository userRepository;
	
	public CategoryCreateProvider(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User execute(Long userCode, Category category) {
		try {
			UserTable userTable = userRepository.getById(userCode);
			CategoryTable categoryTable = new CategoryTable().fromCategory(category);
			
			userTable.getCategories().add(categoryTable);
			UserTable userUpdated = userRepository.saveAndFlush(userTable);
			
			return userUpdated.toUser();
		} catch (EntityNotFoundException e) {
			throw new UserNotFoundException(userCode);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicatedException(String.format("A categoria `%s` já está cadastrada para este usuário", category.getName()));
		}
	}
}
