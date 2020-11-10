package com.comparator.comparable.model;


public class Account implements Comparable<Account> {

	private int accountID = 0;
	private String firstName = null;
	private String lastName = null;
	private double balance = 0;
	
	// Used to sort account by accountID
	@Override
	public int compareTo(Account o) {
		return this.accountID - o.accountID;
	}

	public Account(int accountID, String firstName, String lastName, double balance) {
		super();
		this.accountID = accountID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
	}

	public int getAccountID() {
		return accountID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public double getBalance() {
		return balance;
	}	

}