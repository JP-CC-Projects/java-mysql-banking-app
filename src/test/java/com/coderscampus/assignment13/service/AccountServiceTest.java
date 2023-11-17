package com.coderscampus.assignment13.service;
import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.repository.AddressRepository;
import com.coderscampus.assignment13.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    private AccountService accountService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountService(userRepository, accountRepository, addressRepository);
    }

    @Test
    public void testFindAccountsByUserId() {
        // Arrange
        Long userId = 1L;
        Set<Account> expectedAccounts = new HashSet<>();
        when(accountRepository.findAccountsByUserId(userId)).thenReturn(expectedAccounts);

        // Act
        Set<Account> actualAccounts = accountService.findAccountsByUserId(userId);

        // Assert
        assertEquals(expectedAccounts, actualAccounts);
        verify(accountRepository, times(1)).findAccountsByUserId(userId);
    }

    @Test
    public void testFindUsersByAccountId() {
        // Arrange
        Long accountId = 1L;
        Set<User> expectedUsers = new HashSet<>();
        when(accountRepository.findUsersByAccountId(accountId)).thenReturn(expectedUsers);

        // Act
        Set<User> actualUsers = accountService.findUsersByAccountId(accountId);

        // Assert
        assertEquals(expectedUsers, actualUsers);
        verify(accountRepository, times(1)).findUsersByAccountId(accountId);
    }

    @Test
    public void testSaveAccount() {
        // Arrange
        Account account = new Account();
        when(accountRepository.save(account)).thenReturn(account);

        // Act
        Account savedAccount = accountService.saveAccount(account);

        // Assert
        assertEquals(account, savedAccount);
        verify(accountRepository, times(1)).save(account);
    }
}
