package com.coderscampus.assignment13.repository;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test") // Specify the active profile for testing
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        // Add test data to the database before each test
        User user1 = new User();
        User user2 = new User();

        Account account1 = new Account();
        account1.setAccountName("Account 1");
        account1.getUsers().add(user1);

        Account account2 = new Account();
        account2.setAccountName("Account 2");
        account2.getUsers().add(user2);

        entityManager.persistAndFlush(user1);
        entityManager.persistAndFlush(user2);
        entityManager.persistAndFlush(account1);
        entityManager.persistAndFlush(account2);
        // Log the generated IDs
        System.out.println("User 1 ID: " + user1.getUserId());
        System.out.println("User 2 ID: " + user2.getUserId());
        System.out.println("Account 1 ID: " + account1.getAccountId());
        System.out.println("Account 2 ID: " + account2.getAccountId());
    }

    @Test
    public void testFindAccountsByUserId() {
        // Arrange
        Long userId = 1L;

        // Act
        Set<Account> accounts = accountRepository.findAccountsByUserId(userId);

        // Assert
        assertThat(accounts).hasSize(1);
        assertThat(accounts).allMatch(account -> account.getAccountName().equals("Account 1"));
    }

    @Test
    public void testFindUsersByAccountId() {
        // Arrange
        Long accountId = 1L; // Use account ID 1

        // Act
        Set<User> users = accountRepository.findUsersByAccountId(accountId);

        // Assert
        assertThat(users).hasSize(1);
    }

}
