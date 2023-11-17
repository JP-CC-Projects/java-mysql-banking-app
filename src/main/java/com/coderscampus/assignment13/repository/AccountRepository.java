package com.coderscampus.assignment13.repository;
import com.coderscampus.assignment13.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.coderscampus.assignment13.domain.Account;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

    @Query("SELECT a FROM Account a WHERE a.accountId = :accountId")
    Optional<Account> findAccountByAccountId(@Param("accountId") Long accountId);

    @Query("select a from Account a join a.users u where u.userId = :userId")
    public Set<Account> findAccountsByUserId(Long userId);

    @Query("SELECT u FROM User u JOIN u.accounts a WHERE a.accountId = :accountId")
    Set<User> findUsersByAccountId(@Param("accountId") Long accountId);

}
