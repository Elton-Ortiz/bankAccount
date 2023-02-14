package com.smuteste.register.model.account;



import java.math.BigDecimal;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.smuteste.register.model.AccountType;
import com.smuteste.register.model.AccountUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class CurrentAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Expose
	private BigDecimal balance;

	@Expose
	private String accountNumber;

	private AccountType accountType;

	@OneToOne(mappedBy = "currentAccount",cascade=CascadeType.ALL)
	private AccountUser accountUser;
	
	@OneToMany(mappedBy = "currentAccount")
	private List<Transaction> transactions;
	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@OneToOne
	private Transaction transaction;

	public CurrentAccount() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String fiscalNumber) {
		this.accountNumber = Long.toString(Long.parseLong(fiscalNumber) * 100l / 42);
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

//	public void deposit(BigDecimal depositAmount) {
//		balance = balance.add(depositAmount);
//	}

//	public void withdraw(BigDecimal withdrawAmount) {
//		if (balance.compareTo(withdrawAmount) >= 0) {
//			balance = balance.subtract(withdrawAmount);
//		} else {
//			throw new IllegalArgumentException("Insufficient balance.");
//		}
//	}

	public void transfer(CurrentAccount targetAccount, BigDecimal transferAmount, String accountHolderId) {
		if (this.accountNumber.equals(targetAccount.getAccountNumber())) {
			throw new IllegalArgumentException("Cannot transfer to the same account.");
		}
		if (balance.compareTo(transferAmount) >= 0) {
			balance = balance.subtract(transferAmount);
//			targetAccount.deposit(transferAmount);
		} else {
			throw new IllegalArgumentException("Insufficient balance.");
		}
	}

	public void extract(String accountHolderId) {
		// Add OAuth authentication to verify that the account holder is authorized to
		// perform this operation.
	}
}
