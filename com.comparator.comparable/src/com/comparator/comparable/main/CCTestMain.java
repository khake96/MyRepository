package com.comparator.comparable.main;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import com.comparator.comparable.model.Account;
import com.comparator.comparable.model.AccountByBalance;
import com.comparator.comparable.model.AccountByLastName; 

public class CCTestMain {

	public static void main(String[] args) 
	{ 
		List<Account> list = new ArrayList<Account>(); 
		list.add(new Account(1004, "Allen", "Smith", 8550.60)); 
		list.add(new Account(1007, "Sue", "Smith", 59495.20));  
		list.add(new Account(1002, "Jane", "Wilson", 1560.23));  
		list.add(new Account(1003, "Tom", "Ashford", 15600.87)); 
		list.add(new Account(1010, "Patrice", "Aggerwall", 6549.24)); 
		list.add(new Account(1008, "Moreland", "Acenheimer", 58741.00));  
		list.add(new Account(1001, "Bob", "Ollinger", 5945));  
		list.add(new Account(1000, "John", "Culley", -5269.56)); 
		
		

		NumberFormat usd = NumberFormat.getCurrencyInstance(Locale.US);
		
		
		// Using the Account.java as a Comparable to sort by account number:
		Collections.sort(list);
		Formatter fmt = new Formatter();
		fmt.format("%15s %15s %15s %15s\n", "Account ID", "Balance", "First Name", "Last Name");
		StringBuffer output = new StringBuffer();
		output.append("Sorted by Account ID: \n" 
				+ "-----------------------------------------------------------------------\n");
		for (Account account: list) {
				 fmt.format("%15s %15s %15s %15s\n", account.getAccountID(), usd.format(account.getBalance()), account.getFirstName(), account.getLastName());
			}
		output.append(fmt);
		System.out.println(output.toString()); 
		
		
		// Using a Comparator class to sort the accounts by Last Name	
		AccountByLastName byName = new AccountByLastName();
		Collections.sort(list, byName);
		Formatter fmt2 = new Formatter();
		fmt2.format("%15s %15s %15s %15s\n", "Account ID", "Balance", "First Name", "Last Name");
		StringBuffer output2 = new StringBuffer();
		output2.append("Sorted by last name: \n " 
				+ "-----------------------------------------------------------------------\n");
		for (Account account: list) {
				 fmt2.format("%15s %15s %15s %15s\n", account.getAccountID(), usd.format(account.getBalance()), account.getFirstName(), account.getLastName());
			}
		output2.append(fmt2);
		System.out.println(output2.toString()); 
		fmt.flush();
		
		// Using a Comparator class to sort the accounts by balance
		
		AccountByBalance byBalance = new AccountByBalance();
		Collections.sort(list, byBalance);
		Formatter fmt3 = new Formatter();
		fmt.format("%15s %15s %15s %15s\n", "Account ID", "Balance", "First Name", "Last Name");
		StringBuffer output3 = new StringBuffer();
		output3.append("Sorted by Account balance: " 
				+ "-----------------------------------------------------------------------\n");
		for (Account account: list) {
				 fmt3.format("%15s %15s %15s %15s\n", account.getAccountID(), usd.format(account.getBalance()), account.getFirstName(), account.getLastName());
			}
		output3.append(fmt3);
		System.out.println(output3.toString()); 
	} 
} 