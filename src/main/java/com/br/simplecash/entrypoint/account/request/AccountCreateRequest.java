package com.br.simplecash.entrypoint.account.request;

import javax.validation.constraints.NotBlank;

import com.br.simplecash.core.domain.Account;
import com.br.simplecash.core.validation.IntegerValidation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class AccountCreateRequest {
	@NotBlank
	private String name;
	
	@IntegerValidation
	private String balance;
	
	public Account toAccount() {
		return Account.builder()
                  .name(this.name)
                  .balance(Integer.parseInt(this.balance))
                  .build();
	}
}
