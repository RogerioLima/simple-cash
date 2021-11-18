package com.br.simplecash.provider.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.simplecash.provider.user.repository.tables.UserTable;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {
	public Optional<UserTable> findByEmail(String email);
}
