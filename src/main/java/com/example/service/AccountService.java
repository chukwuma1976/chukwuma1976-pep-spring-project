package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    
    @Autowired
    AccountRepository accountRepository;
    
    public AccountService(AccountRepository accountRepository ){
        this.accountRepository = accountRepository;
    }
    
    public Account addUser(Account user){
        if (user.getPassword().length() >= 4)
            return accountRepository.save(user);
            else return null;
    }

    public Account loginUser(Account user){
        return accountRepository.findAccountByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
