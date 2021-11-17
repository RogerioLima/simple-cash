package com.br.simplecash.core.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	private Long code;
	private String name;
	private String email;
	private LocalDate birthDate;
	private String password;
}
