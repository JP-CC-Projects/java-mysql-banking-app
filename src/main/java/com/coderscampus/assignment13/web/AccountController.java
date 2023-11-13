package com.coderscampus.assignment13.web;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Set;

@Controller
public class AccountController {

    private AccountService accountService;
    @Autowired
    public AccountController (AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/users/{userId}/accounts/{accountId}")
    public String getAccountsByUserId (ModelMap model,
                                       @PathVariable Long userId) {
        Set<Account> accounts = accountService.findAccountsByUserId(userId);
        model.put("accounts", Arrays.asList(accounts));
        model.put("accounts", accounts);
        return "accounts";
    }
    @PostMapping("/users/{userId}/accounts/")
    public String createAccountsByUserId (ModelMap model,
                                       @PathVariable Long userId) {
        Set<Account> accounts = accountService.findAccountsByUserId(userId);
        model.put("accounts", Arrays.asList(accounts));
        model.put("accounts", accounts);
        return "accounts";
    }
}
