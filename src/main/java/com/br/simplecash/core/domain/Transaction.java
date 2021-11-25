package com.br.simplecash.core.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	private Long id;
	private TransactionStatus status;
	private TransactionTypes type;
	private LocalDate date;
	private String description;
	private Category category;
	private Account account;
	private Integer amount;
}
