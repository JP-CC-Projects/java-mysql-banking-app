package com.coderscampus.assignment13.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.coderscampus.assignment13.domain.Account;

import java.util.Set;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    @Query("select a from Account a join a.users u where u.userId = :userId")
    public Set<Account> findAccountsByUserId(Long userId);
}
