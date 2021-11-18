package com.br.simplecash.entrypoint.account.response;

import java.util.ArrayList;
import java.util.List;

import com.br.simplecash.core.domain.User;
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
public class AccountCreateResponse {
	private String userName;
	private List<AccountRequest> accounts = new ArrayList<>();
	
	public AccountCreateResponse fromUser(User user) {
		this.userName = user.getName();
		
		if (user.getAccounts() != null) {
			user.getAccounts().forEach(account -> {
				AccountRequest accountRequest = new AccountRequest(account.getCode(), account.getName(), account.getBalance());
				this.accounts.add(accountRequest);
			});
		}
		
		return this;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(Include.NON_NULL)
	private class AccountRequest {
		private Long code;
		private String name;
		private Integer balance;
	}
}
