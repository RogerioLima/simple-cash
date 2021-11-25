package com.br.simplecash.entrypoint.transaction.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.br.simplecash.core.domain.Account;
import com.br.simplecash.core.domain.Category;
import com.br.simplecash.core.domain.Transaction;
import com.br.simplecash.core.domain.TransactionStatus;
import com.br.simplecash.core.domain.TransactionTypes;
import com.br.simplecash.core.validation.DateValidation;
import com.br.simplecash.core.validation.IntegerValidation;
import com.br.simplecash.core.validation.TransactionStatusValidation;
import com.br.simplecash.core.validation.TransactionTypeValidation;
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
public class TransactionCreateRequest {
	
	@TransactionStatusValidation
	private String status;
	
	@TransactionTypeValidation
	private String type;
	
	@DateValidation
	private String date;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String category;
	
	@NotBlank
	private String account;
	
	@IntegerValidation
	private String amount;
	
	public Transaction toTransaction(Long userCode) {
		TransactionStatus transactionStatus = TransactionStatus.valueOf(status.toUpperCase());
		TransactionTypes transactionType = TransactionTypes.valueOf(type.toUpperCase());
		LocalDate transactionDate = LocalDate.parse(date);
		Category transactionCategory = Category.builder()
                                           .name(category)
                                           .type(transactionType.toString())
                                           .build();
		Account transactionAccount = Account.builder()
                                        .name(account)
                                        .build();
		
		return Transaction.builder()
                      .status(transactionStatus)
                      .type(transactionType)
                      .date(transactionDate)
                      .description(description)
                      .category(transactionCategory)
                      .account(transactionAccount)
                      
                      .build();
	}
}
