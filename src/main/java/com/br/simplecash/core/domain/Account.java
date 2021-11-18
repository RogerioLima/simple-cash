package com.br.simplecash.core.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
	private Long code;
	private String name;
	private Integer balance;
	
	public static Account accountDefault() {
		return Account.builder().name("Carteira").balance(0).build();
	}
}
