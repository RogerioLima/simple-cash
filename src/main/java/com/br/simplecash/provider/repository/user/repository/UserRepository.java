package com.br.simplecash.provider.repository.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.simplecash.provider.repository.user.tables.UserTable;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {

}
