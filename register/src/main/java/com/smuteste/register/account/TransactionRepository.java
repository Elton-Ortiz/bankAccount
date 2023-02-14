package com.smuteste.register.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smuteste.register.model.account.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
