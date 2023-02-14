package com.smuteste.register.account;

import java.math.BigDecimal;

public class TransferDto {

	private String accountNumber;
	
	private String relatedAccountNumber;
	
	private BigDecimal amount;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	

	public String getRelatedAccountNumber() {
		return relatedAccountNumber;
	}

	public void setRelatedAccountNumber(String relatedAccountNumber) {
		this.relatedAccountNumber = relatedAccountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
