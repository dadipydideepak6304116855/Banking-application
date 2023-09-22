package com.wipro.bank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.wipro.bank.entity.Account;
import com.wipro.bank.entity.Customer;
import com.wipro.bank.entity.FundTransferRequest;
import com.wipro.bank.exception.AccountNotFoundException;
import com.wipro.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
	
	
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(int accountId) {
       
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        // Check if the account exists and return it, or return null if it doesn't exist
        return optionalAccount.orElse(null);
    }

	public Customer getCustomerById(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Account> getAccountDetailsByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean transferFunds(int sourceAccountNumber, int destinationAccountNumber, float amount) {
        if (sourceAccountNumber==destinationAccountNumber) {
        	System.out.println("Source and destination accounts are the same, should be different.");
            return false;//Source and destination accounts are the same, should be different.;
        }

        Account sourceAccount = accountRepository.findById(sourceAccountNumber).orElse(null);
        Account destinationAccount = accountRepository.findById(destinationAccountNumber).orElse(null);

        if (sourceAccount == null || destinationAccount == null) {
        	System.out.println("Source or destination account does not exist.");
            return false;//Source or destination account does not exist.;
        }

         if (amount <= 0) {
        	 System.out.println("Transfer amount should be greater than 1 rupee");
             return false;//Transfer amount should be greater than 1 rupee"
        }

        if (sourceAccount.getBalance()-(amount) < 0) {
        	 System.out.println("Insufficient balance in the source account.");
            return false;//Insufficient balance in the source account.;
        }

        // Perform the fund transfer
        sourceAccount.setBalance(sourceAccount.getBalance()-(amount));
        destinationAccount.setBalance(destinationAccount.getBalance()+(amount));

        // Save the updated account balances
        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);
        return true;//Funds transferred successfully.;
    }
	

		public List<Account> getAccountByType(String type) throws AccountNotFoundException {

			List<Account>accounts=accountRepository.findByAccountType(type);

			if(accounts.isEmpty()) {

				throw new AccountNotFoundException("there is no account of "+type+" type account");

			}

			return accounts;

		}
	}
  