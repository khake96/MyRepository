package com.silverstarapp.model;

import org.postgresql.util.PGTimestamp;

public class Account {
	
	private int accountNo;
	private double balance;
	private PGTimestamp ts = new PGTimestamp(accountNo, null);
	
	public Account(int accountNo, double balance) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [Account Number= " + accountNo + ", Account Balance= " + balance + ", Account Last Accessed= " + ts + "]";
	}
	
}
