package com.smuteste.register.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.smuteste.register.model.AccountUser;
import com.smuteste.register.register.RegisterService;

import jakarta.transaction.Transactional;

@SpringBootTest
@WebAppConfiguration
public class AccountServiceTest {

	@Autowired
	private RegisterService registerService;
	
	@Autowired
	private AccountService accountService;
	
	@Test
	public void accountExistsTest() {
		
		boolean accountExist = accountService.accountExists("144496384738");
		assertEquals(true, accountExist);
		boolean accountExistFalse = accountService.accountExists("123");
		assertEquals(false, accountExistFalse);
	}
	
	@Test
	@Transactional
	public void createAndDeleteAccountTest() {
		AccountUser accountUser = new AccountUser();
		accountUser.setFiscalNumber("20533567319");
		accountUser.setName("Jose Bueno da Silva");
		assertEquals("48889445997", registerService.register(accountUser));
		assertEquals("cliente com o número fiscal: 20533567319 deletado!", registerService.deleteAccountUser("20533567319"));
		
	}
	
	@Test
	@Transactional
	public void depositTest() {
		
		createAccountUser();
		
		assertEquals("{\"balance\":200,\"accountNumber\":\"48889445997\"}",accountService.deposit("48889445997", new BigDecimal(200.00)));
		
		deleteAccountUser();
	}
	

//	@Test
//	@Transactional
//	public void whitdrawTest() {
//		createAccountUser();
//		
//		assertEquals("{\"balance\":200,\"accountNumber\":\"48889445997\"}",accountService.withdraw("48889445997", new BigDecimal(200.00)));
//		
//		deleteAccountUser();
//	}
	
	private void createAccountUser () {
		AccountUser accountUser = new AccountUser();
		accountUser.setFiscalNumber("20533567319");
		accountUser.setName("Jose Bueno da Silva");
		registerService.register(accountUser);
	}
	
	
	private void deleteAccountUser () {
		registerService.deleteAccountUser("20533567319");

	}
}
