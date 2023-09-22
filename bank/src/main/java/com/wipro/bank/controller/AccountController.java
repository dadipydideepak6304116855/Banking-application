package com.wipro.bank.controller;


import java.math.BigDecimal;
import java.util.List;

import com.wipro.bank.entity.Account;
import com.wipro.bank.entity.FundTransferRequest;
import com.wipro.bank.exception.AccountNotFoundException;
import com.wipro.bank.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account") 
public class AccountController {

    // Here I Inject AccountService 
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    
    //Get account details based on id
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable int accountId) {
        // Retrieve account by account ID from the service
        Account account = accountService.getAccountById(accountId);

        if (account == null) {
        	
            // If Account not found, return 404 Not Found
        	throw new AccountNotFoundException("Account not found with ID: " + accountId);
        }

        // If the account is found, return it with a 200 OK response
        return ResponseEntity.ok(account);
    }
	
	
	// get account details by type of account
		@GetMapping("/type/{accountType}")

		public ResponseEntity<List<Account>>getAccountByType(@PathVariable String accountType) throws AccountNotFoundException{
			
			List<Account> accounts = accountService.getAccountByType(accountType);
			return new ResponseEntity<>(accounts,HttpStatus.OK);

		}
		
		
		
	//transfer funds from source account to destination account
		@PostMapping("/fundtransfer")
		public Boolean transferFunds(@RequestBody FundTransferRequest request) {
		    int sourceAccountNumber = request.getSourceAccountNumber();
		    int destinationAccountNumber = request.getDestinationAccountNumber();
		    float amount = request.getAmount();

		    if (accountService.transferFunds(sourceAccountNumber, destinationAccountNumber, amount)) {
		    	System.out.println("Funds transfered successfully...!");
		        return true;
		    } 
		    System.out.println("Funds transfered failed...!");
		    return false;
		}

	
	
	
	
	
  
}
