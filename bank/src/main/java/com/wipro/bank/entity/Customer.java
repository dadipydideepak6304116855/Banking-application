package com.wipro.bank.entity;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_table")

public class Customer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int customerId;
	private String customerName;
	private String customerAddress;
	private int aadharNumber;
	
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Account> accounts; //accounts should be unique so i used set here



	public Customer() {
		super();
	}



	public Customer(int customerId, String customerName, String customerAddress, int aadharNumber,
			Set<Account> accounts) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.aadharNumber = aadharNumber;
		this.accounts = accounts;
	}



	public int getCustomerId() {
		return customerId;
	}



	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}



	public String getCustomerName() {
		return customerName;
	}



	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public String getCustomerAddress() {
		return customerAddress;
	}



	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}



	public int getAadharNumber() {
		return aadharNumber;
	}



	public void setAadharNumber(int aadharNumber) {
		this.aadharNumber = aadharNumber;
	}



	public Set<Account> getAccounts() {
		return accounts;
	}



	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}



	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", aadharNumber=" + aadharNumber + ", accounts=" + accounts + "]";
	}



	public void setId(long l) {
		// TODO Auto-generated method stub
		
	}



	public void setId(Long customerId2) {
		// TODO Auto-generated method stub
		
	}





}