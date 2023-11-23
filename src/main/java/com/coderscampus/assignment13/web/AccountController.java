package com.coderscampus.assignment13.web;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Set;

@Controller
public class AccountController {

    private AccountService accountService;
    private UserService userService;

    @Autowired
    public AccountController (AccountService accountService, UserService userService){
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping("/users/{userId}/accounts")
    public String getAllAccountsByUserId(ModelMap model,
                                         @PathVariable Long userId) {
        Set<Account> accounts = accountService.findAccountsByUserId(userId);
        model.put("accounts", Arrays.asList(accounts));
        System.out.println("Get Accounts: " + accounts);
        model.put("accounts", accounts);
        return "accounts";
    }
    @PostMapping ("/users/{userId}/accounts")
    public String postCreateOneAccount(ModelMap model, @PathVariable Long userId) {
        System.out.println("Controller: postOneAccountByUserId");
        Account newAccount = accountService.createOneBankAccount(userId);
        return "redirect:/users/{userId}/accounts/" + newAccount.getAccountId();
    }
    @GetMapping("/users/{userId}/accounts/{accountId}")
    public String getOneAccountByAccountId(ModelMap model,
                                           @PathVariable Long userId,
                                            @PathVariable Long accountId) {
        System.out.println("Controller: getOneAccountByAccountId");
        Account account = accountService.findAccountByAccountId(accountId);
        System.out.println("Retrieved Account: " + account);
        System.out.println(account.getAccountId());
        model.addAttribute("account", account);
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "accounts";
    }
    @PostMapping("/users/{userId}/accounts/{accountId}")
    public String postUpdateOneAccount(@ModelAttribute("account") Account account) {
        System.out.println("Controller: postOneAccountsByAccountId");
        accountService.saveAccount(account);
        return "redirect:/users/{userId}/accounts/{accountId}";
    }


}
