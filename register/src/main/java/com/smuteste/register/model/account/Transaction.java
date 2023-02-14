package com.smuteste.register.model.account;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.Expose;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private CurrentAccount currentAccount;

	@Expose
	private Long relatedCurrentAccount;

	@Expose
	private Date criationDate;
	
	@Expose
	private BigDecimal amount;
	
	private Status status;
	
	@Expose
	private TransactionType transactionType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CurrentAccount getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(CurrentAccount currentAccount) {
		this.currentAccount = currentAccount;
	}

	public Long getRelatedCurrentAccountId() {
		return relatedCurrentAccount;
	}

	public void setRelatedCurrentAccountId(Long relatedCurrentAccountId) {
		this.relatedCurrentAccount = relatedCurrentAccountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCriationDate() {
		return criationDate;
	}

	public void setCriationDate(Date criationDate) {
		this.criationDate = criationDate;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getRelatedCurrentAccount() {
		return relatedCurrentAccount;
	}

	public void setRelatedCurrentAccount(Long relatedCurrentAccount) {
		this.relatedCurrentAccount = relatedCurrentAccount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	 @Override
	    public String toString()
	    {
	        return "{currentAccount="
	            + currentAccount
	            + ", relatedCurrentAccount="
	            + relatedCurrentAccount
	            + ", criationDate="
	            + criationDate 
	            + ", amount="
	            + amount
	            + ", status="
	            + status
	            + ", transactionType="
	            + transactionType
	            + "}";
	    }
	 
	 
}
