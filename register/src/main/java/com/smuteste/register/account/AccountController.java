package com.smuteste.register.account;

import org.apache.catalina.Contained;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smuteste.register.model.account.Transaction;
import com.smuteste.register.register.BaseController;

@Controller
public class AccountController extends BaseController {


	@Autowired
	AccountService accountService;

	@RequestMapping(value = "/deposit/{accountNumber}", consumes = MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.POST)
	public ResponseEntity<String> deposit(@PathVariable("accountNumber") String accountNumber,
			@RequestBody Transaction transaction) {		
		if(!accountService.accountExists(accountNumber)) {
			return ResponseEntity.badRequest().body("Conta não existe!");
		}else {
			return ResponseEntity.ok().body(accountService.deposit(accountNumber, transaction.getAmount()));			
		}
	}

	@RequestMapping(value = "/extract/{accountNumber}", method = RequestMethod.GET)
	public ResponseEntity<String> extract(@PathVariable("accountNumber") String accountNumber) {
		
		if(accountService.accountExists(accountNumber)) {
			return ResponseEntity.ok().body(accountService.extract(accountNumber));							
		}else {
			return ResponseEntity.badRequest().body("Conta não existe!");
		}	
	}
	
	@RequestMapping(value = "/withdraw/{accountNumber}", method = RequestMethod.POST)
	public ResponseEntity<String> withdraw(@PathVariable("accountNumber") String accountNumber,
			@RequestBody Transaction transaction) {		
		if(!accountService.accountExists(accountNumber)) {
			return ResponseEntity.badRequest().body("Conta não existe!");
		}else {
			return accountService.withdraw(accountNumber, transaction.getAmount());			
		}	
	}
	
	@RequestMapping(value = "/transfer/{accountNumber}", method = RequestMethod.POST)
	public ResponseEntity<String> transfer(@PathVariable("accountNumber") String accountNumber,
			@RequestBody TransferDto transferDto) {	
		if(!accountService.accountExists(accountNumber) || !accountService.accountExists(transferDto.getRelatedAccountNumber()) ) {
			return ResponseEntity.badRequest().body("Conta não existe!");
		}else {
			return accountService.transfer(accountNumber, transferDto);			
		}	
	}
}
