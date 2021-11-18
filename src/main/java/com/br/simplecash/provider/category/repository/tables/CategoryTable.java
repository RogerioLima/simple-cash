package com.br.simplecash.provider.category.repository.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.br.simplecash.core.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CATEGORIES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CODE")
	private Long code;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "TYPE", nullable = false)
	private String type;
	
	public CategoryTable fromCategory(Category category) {
		return CategoryTable.builder()
                        .code(category.getCode())
                        .name(category.getName())
                        .type(category.getType())
                        .build();
	}
	
	public Category toCategory() {
		return Category.builder()
                   .code(this.code)
                   .name(this.name)
                   .type(this.type)
                   .build();
	}
}
