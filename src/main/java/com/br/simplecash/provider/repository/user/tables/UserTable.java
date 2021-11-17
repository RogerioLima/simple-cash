package com.br.simplecash.provider.repository.user.tables;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.br.simplecash.core.domain.User;

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
	
	public UserTable fromUser(User user) {
		return UserTable.builder()
				            .name(user.getName())
				            .email(user.getEmail())
				            .birthDate(user.getBirthDate())
				            .password(user.getPassword())
				            .build();
	}
	
	public User toUser() {
		return User.builder()
               .code(code)
               .name(name)
               .email(email)
               .birthDate(birthDate)
               .password(password)
               .build();
	}
}
