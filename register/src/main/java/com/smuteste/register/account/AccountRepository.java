package com.smuteste.register.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smuteste.register.model.account.CurrentAccount;

@Repository
public interface AccountRepository extends JpaRepository<CurrentAccount, Long>{

	boolean existsCurrentAccountByAccountNumber(String accountNumber);

	Optional<CurrentAccount> getByAccountNumber(String accountNumber);

}
