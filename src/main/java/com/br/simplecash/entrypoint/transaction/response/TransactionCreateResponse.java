package com.br.simplecash.entrypoint.transaction.response;

import com.br.simplecash.core.domain.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class TransactionCreateResponse {
	private Long id;
	private String status;
	private String type;
	private String date;
	private String description;
	private String category;
	private String account;
	private String amount;
	
	public TransactionCreateResponse fromTransaction(Transaction transaction) {
		this.id = transaction.getId();
		this.status = transaction.getStatus().toString();
		this.type = transaction.getType().toString();
		this.date = transaction.getDate().toString();
		this.description = transaction.getDescription();
		this.category = transaction.getCategory().getName();
		this.account = transaction.getAccount().getName();
		this.amount = String.valueOf(transaction.getAmount());
		
		return this;
	}
}
