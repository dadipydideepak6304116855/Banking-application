package com.wipro.bank.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account_details")
public class Account {

    @Id
    private int accountNumber;
    private String accountType;
    private float balance;
  
    
    public Account() {
    	
    }
    
    

	public Account(int accountNumber, String accountType, float balance) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}



	// Getter for accountNumber
    public int getAccountNumber() {
        return accountNumber;
    }

    // Setter for accountNumber 
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Getter for accountType
    public String getAccountType() {
        return accountType;
    }

    // Setter for accountType
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    // Getter for balance
    public float getBalance() {
        return balance;
    }

    // Setter for balance
    public void setBalance(float balance) {
        this.balance = balance;
    }


    // To string 
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", balance=" + balance
				+ "]";
	}

   
    
}
