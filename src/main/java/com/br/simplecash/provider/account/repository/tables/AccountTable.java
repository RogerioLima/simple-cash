package com.br.simplecash.provider.account.repository.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.br.simplecash.core.domain.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ACCOUNTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CODE")
	private Long code;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "BALANCE", nullable = false)
	private Integer balance;
	
	public AccountTable fromAccount(Account account) {
		return AccountTable.builder()
                       .name(account.getName())
                       .balance(account.getBalance())
                       .build();
	}
	
	public Account toAccount() {
		return Account.builder()
                  .code(this.code)
                  .name(this.name)
                  .balance(this.balance)
                  .build();
	}
}
