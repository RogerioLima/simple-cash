package com.br.simplecash.entrypoint.user.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.br.simplecash.core.domain.User;
import com.br.simplecash.core.validation.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class UserCreateRequest {
	
	@NotBlank
	private String name;
	
	@Email
	private String email;
	
	@NotBlank
	@Date
	private String birthDate;
	
	@NotBlank
	private String password;
	
	public User toUser() {
		return User.builder()
								.name(this.name)
								.email(this.email)
								.birthDate(LocalDate.parse(this.birthDate))
								.password(this.password)
								.build();
	}
}
