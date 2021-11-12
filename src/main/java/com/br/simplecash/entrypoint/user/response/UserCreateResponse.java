package com.br.simplecash.entrypoint.user.response;

import java.time.LocalDate;

import com.br.simplecash.core.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateResponse {
	private Long userCode;
	private String name;
	private String email;
	private LocalDate birthDate;
	
	public UserCreateResponse fromUser(User user) {
		this.userCode = user.getCode();
		this.name = user.getName();
		this.email = user.getEmail();
		this.birthDate = user.getBirthDate();
		
		return this;
	}
}
