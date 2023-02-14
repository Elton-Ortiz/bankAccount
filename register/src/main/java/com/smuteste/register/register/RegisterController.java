package com.smuteste.register.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smuteste.register.model.AccountUser;

@Controller
	public class RegisterController extends BaseController{
	  

	@Autowired
	RegisterRepository registerRepository;
	
	@Autowired
	RegisterService registerService;
	
	    @RequestMapping(value = "/register/create",  method = RequestMethod.POST)
	    public ResponseEntity<String> register(@RequestBody AccountUser accountUser) {
	    	String account = registerService.fiscalNumberAlredyRegistered(accountUser);
	    	if(account != null) {
	    		return ResponseEntity.badRequest()
	    				.body("fiscalNumber já cadastrado, na conta: " + account);
	    	}else if(!registerService.fiscalNumberIsValid(accountUser)) {
	    		return ResponseEntity.badRequest()
	    				.body("fiscalNumber inválido!");
	    	}else {
	    		return ResponseEntity.ok()
	    				.body("Número da conta: " + registerService.register(accountUser));
	    	}	    	
	    }	    
	    
	    @RequestMapping(value = "/delete/{accountNumber}", method = RequestMethod.GET)
		public void delete(@PathVariable("accountNumber") String accountNumber) {		
			
	    	registerService.deleteAccountUser(accountNumber);		}
	    
	    
}
