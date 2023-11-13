package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.repository.AddressRepository;
import com.coderscampus.assignment13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;

@Service

public class AccountService {
    private UserRepository userRepo;
    private AccountRepository accountRepo;
    private AddressRepository addressRepo;

    @Autowired
    public AccountService(UserRepository userRepo,
                          AccountRepository accountRepo,
                          AddressRepository addressRepo) {
        this.userRepo = userRepo;
        this.accountRepo = accountRepo;
        this.addressRepo = addressRepo;
    }

    public Set<Account> findAccountsByUserId(Long userId) {
        return accountRepo.findAccountsByUserId(userId);
    }


}
