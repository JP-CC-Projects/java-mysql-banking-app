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
import java.util.List;
import java.util.Optional;
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
    public Account findAccountByAccountId(Long accountId) {
        return accountRepo.findAccountByAccountId(accountId).orElse(null);
    }

    public Set<User> findUsersByAccountId(Long accountId) {
        return accountRepo.findUsersByAccountId(accountId);
    }
    @Transactional
    public Account createOneBankAccount(Long userId) {
        User existingUser = userRepo.findById(userId).orElse(null);
        Account newAccount = new Account();
        newAccount.setAccountName("New Account");
        newAccount.getUsers().add(existingUser);
        existingUser.getAccounts().add(newAccount);

        accountRepo.save(newAccount);
        userRepo.save(existingUser);
        return newAccount;
    }

    @Transactional
    public Account updateAccount(Account postedAccount, Long accountId){
        Account existingAccount = accountRepo.findAccountByAccountId(accountId).orElse(null);
        Set<User> existingUsers = findUsersByAccountId(accountId);
        for(User existingUser : existingUsers){
            existingAccount.getUsers().add(existingUser);
        }

        existingAccount.setAccountName(postedAccount.getAccountName());
        accountRepo.save(existingAccount);
        return existingAccount;
    }
}
