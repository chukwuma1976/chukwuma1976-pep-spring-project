package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    @Query("FROM Account WHERE username = :username && password =:password")
    Account loginUser(@Param("username") String username, @Param("password") String password);
}
