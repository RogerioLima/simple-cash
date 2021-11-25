package com.br.simplecash.core.domain;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	private Long code;
	private String name;
	private String type;
	
	public static List<Category> getDefaultCategories() {
		return Arrays.asList(
			Category.builder().name("Lazer").type(TransactionTypes.EXPENSE.toString()).build(),
			Category.builder().name("Salario").type(TransactionTypes.INCOME.toString()).build()
		);
	}
}
