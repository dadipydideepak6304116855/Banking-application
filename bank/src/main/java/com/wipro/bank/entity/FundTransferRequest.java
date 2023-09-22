package com.wipro.bank.entity;

public class FundTransferRequest {
    private int sourceAccountNumber;
    private int destinationAccountNumber;
    private float amount;
    
    
	public FundTransferRequest() {
		super();
	}


	public FundTransferRequest(int sourceAccountNumber, int destinationAccountNumber, float amount) {
		super();
		this.sourceAccountNumber = sourceAccountNumber;
		this.destinationAccountNumber = destinationAccountNumber;
		this.amount = amount;
	}


	public int getSourceAccountNumber() {
		return sourceAccountNumber;
	}


	public void setSourceAccountNumber(int sourceAccountNumber) {
		this.sourceAccountNumber = sourceAccountNumber;
	}


	public int getDestinationAccountNumber() {
		return destinationAccountNumber;
	}


	public void setDestinationAccountNumber(int destinationAccountNumber) {
		this.destinationAccountNumber = destinationAccountNumber;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "FundTransferRequest [sourceAccountNumber=" + sourceAccountNumber + ", destinationAccountNumber="
				+ destinationAccountNumber + ", amount=" + amount + "]";
	}


	public Boolean transferFunds(FundTransferRequest request) {
		// TODO Auto-generated method stub
		return null;
	}




	
	
   
}
