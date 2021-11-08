package com.br.simplecash.entrypoint.user.request;

import java.time.LocalDate;

import com.br.simplecash.core.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
	private String name;
	private String email;
	private LocalDate birthDate;
	private String password;
	
	public User toUser() {
		return User.builder()
								.name(this.name)
								.email(this.email)
								.birthDate(this.birthDate)
								.password(this.password)
								.build();
	}
}
