package com.smuteste.register.register;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smuteste.register.account.AccountRepository;
import com.smuteste.register.model.AccountType;
import com.smuteste.register.model.AccountUser;
import com.smuteste.register.model.account.CurrentAccount;
import com.smuteste.register.service.CPFCNPJValidator;

import jakarta.persistence.EntityManager;

@Service
public class RegisterService {

	@Autowired
	RegisterRepository registerRepository;
	
	@Autowired
	AccountRepository accountRepository;

	public String register(AccountUser accountUser) {
		AccountType accountType = CPFCNPJValidator.isValid(accountUser.getFiscalNumber());
		CurrentAccount currentAccount = new CurrentAccount();
		accountRepository.save(currentAccount);
		accountUser.setCurrentAccount(currentAccount);
		accountUser.getCurrentAccount().setAccountType(accountType);
				accountUser.getCurrentAccount().setAccountNumber(accountUser.getFiscalNumber());
				registerRepository.save(accountUser);
				return accountUser.getCurrentAccount().getAccountNumber().toString();				
			
	}
	public String fiscalNumberAlredyRegistered(AccountUser accountUser) {
		Optional<AccountUser> account = registerRepository.findByFiscalNumber(accountUser.getFiscalNumber());
		if(account.isPresent()) {
			return account.get().getCurrentAccount().getAccountNumber().toString();
		}
		return null;
	}
	
	public boolean fiscalNumberIsValid(AccountUser accountUser) {
		AccountType accountType = CPFCNPJValidator.isValid(accountUser.getFiscalNumber());
		if (accountType != null) {
			return true;				
		}
		return false;
	}
	
	public String deleteAccountUser(String fiscalNumber) {
		AccountUser accountUser = registerRepository.findByFiscalNumber(fiscalNumber).get();
		registerRepository.delete(accountUser);
			return "cliente com o número fiscal: " + fiscalNumber + " deletado!";
	}
}
