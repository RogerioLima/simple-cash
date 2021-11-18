package com.br.simplecash.provider.user.repository.tables;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.br.simplecash.core.domain.Account;
import com.br.simplecash.core.domain.Category;
import com.br.simplecash.core.domain.User;
import com.br.simplecash.provider.account.repository.tables.AccountTable;
import com.br.simplecash.provider.category.repository.tables.CategoryTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class UserTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CODE")
	private Long code;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;
	
	@Column(name = "BIRTH_DATE", nullable = false)
	private LocalDate birthDate;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AccountTable> accounts = new ArrayList<>();
	
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CategoryTable> categories = new ArrayList<>();
	
	public UserTable fromUser(User user) {
		List<AccountTable> accounts = new ArrayList<>();
		List<CategoryTable> categories = new ArrayList<>();
		
		if (user.getAccounts() != null) {
			user.getAccounts().forEach(account -> {
				accounts.add(new AccountTable().fromAccount(account));
			});
		}
		
		if (user.getCategories() != null) {
			user.getCategories().forEach(category -> {
				categories.add(new CategoryTable().fromCategory(category));
			});
		}
		
		return UserTable.builder()
				            .name(user.getName())
				            .email(user.getEmail())
				            .birthDate(user.getBirthDate())
				            .password(user.getPassword())
				            .accounts(accounts)
				            .categories(categories)
				            .build();
	}
	
	public User toUser() {
		List<Account> accounts = new ArrayList<>();
		List<Category> categories = new ArrayList<>();
		
		this.accounts.forEach(accountTable -> {
			accounts.add(accountTable.toAccount());
		});
		
		this.categories.forEach(categoryTable -> {
			categories.add(categoryTable.toCategory());
		});
		
		return User.builder()
               .code(code)
               .name(name)
               .email(email)
               .birthDate(birthDate)
               .password(password)
               .accounts(accounts)
               .build();
	}
}
