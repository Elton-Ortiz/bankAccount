package com.smuteste.register.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smuteste.register.model.account.CurrentAccount;
import com.smuteste.register.model.account.Transaction;
import com.smuteste.register.model.account.TransactionType;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	private Date date = new Date();

	
	public ResponseEntity<String> withdraw(String accountNumber, BigDecimal amount) {
		CurrentAccount account = accountRepository.getByAccountNumber(accountNumber).get();
		balanceIsNull(account);

		if (account.getBalance().compareTo(amount) >= 0) {
			BigDecimal balance = account.getBalance().subtract(amount);
			account.setBalance(balance);
			fillTransaction(account, null, amount, TransactionType.WITHDRAW);
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String jsonExtract = gson.toJson(account);
			return ResponseEntity.ok().body(jsonExtract);	
		} else {
			return ResponseEntity.badRequest().body("Saldo insuficiente!");
		}	
}
		
	public String deposit(String accountNumber, BigDecimal amount) {
			CurrentAccount account = accountRepository.getByAccountNumber(accountNumber).get();
			balanceIsNull(account);
			BigDecimal balance = account.getBalance().add(amount);
			account.setBalance(balance);
			accountRepository.save(account);
			fillTransaction(account, null, amount, TransactionType.DEPOSIT);
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String jsonExtract = gson.toJson(account);
			return jsonExtract;
		
	}

	private void balanceIsNull(CurrentAccount account) {
		if (account.getBalance() == null) {
			account.setBalance(BigDecimal.ZERO);
		} 
	}

	public String extract(String accountNumber) {
			CurrentAccount account = accountRepository.getByAccountNumber(accountNumber).get();
			List<Transaction> transactions = account.getTransactions();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String jsonExtract = gson.toJson(transactions);
			return jsonExtract;
	}

	public boolean accountExists(String accountNumber) {
		return accountRepository.existsCurrentAccountByAccountNumber(accountNumber);
	}
	
	public ResponseEntity<String> transfer(String accountNumber, TransferDto transferDto) {
		CurrentAccount account = accountRepository.getByAccountNumber(accountNumber).get();
		CurrentAccount relatedAccount = accountRepository.getByAccountNumber(transferDto.getRelatedAccountNumber()).get();
		balanceIsNull(account);
		balanceIsNull(relatedAccount);
		BigDecimal amount = transferDto.getAmount();
		
		
		if (account.getBalance().compareTo(amount) >= 0) {
			BigDecimal balance = account.getBalance().subtract(amount);
			account.setBalance(balance);
			BigDecimal relateBalance = relatedAccount.getBalance().add(amount);
			relatedAccount.setBalance(relateBalance);
			
			
			fillTransaction(account, relatedAccount, amount, TransactionType.TRANSFER_OUT);
			fillTransaction(relatedAccount, account, amount, TransactionType.TRANSFER_IN);
			
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String jsonExtract = gson.toJson(account);
			return ResponseEntity.ok().body(jsonExtract);	
		} else {
			return ResponseEntity.badRequest().body("Saldo insuficiente!");
		}	
	}
	
	private void fillTransaction(CurrentAccount currentAccount, CurrentAccount relatedCurrentAccount, BigDecimal amount,
			TransactionType transactionType) {
		Transaction transaction = new Transaction();
		transaction.setTransactionType(transactionType);
		transaction.setAmount(amount);
		transaction.setCriationDate(date);
		transaction.setCurrentAccount(currentAccount);
		transactionRepository.save(transaction);
		if (currentAccount.getTransaction() != null) {
			currentAccount.getTransactions().add(transaction);
		} else {
			List<Transaction> list = new ArrayList<Transaction>();
			list.add(transaction);
			currentAccount.setTransactions(list);
		}
	}
	
	public String currentAccountDelete(CurrentAccount currentAccount) {
		String resp = currentAccount.getAccountNumber();
		accountRepository.delete(currentAccount);
		return "Conta número: " + resp + " deletada!";
	}

	public boolean hasRefund() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
