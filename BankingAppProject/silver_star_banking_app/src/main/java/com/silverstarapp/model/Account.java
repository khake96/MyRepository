package com.silverstarapp.model;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.Locale;

import com.silverstarapp.main.util.MainUtilProperties;

/* This Account class file the data required for a Silver
 * Star Banking account.
 */

public class Account {
	
	private int accountNo;
	private double balance;
	MainUtilProperties methodCaller = new MainUtilProperties();
	private Timestamp approvedDate;
	private Timestamp lastModifiedDate;

	public Account() {
		super();
	}

	public Account(int accountNo, double balance, MainUtilProperties methodCaller, Timestamp approvedDate,
			Timestamp lastModifiedDate) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
		this.methodCaller = methodCaller;
		this.approvedDate = approvedDate;
		this.lastModifiedDate = lastModifiedDate;
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



	public MainUtilProperties getMethodCaller() {
		return methodCaller;
	}



	public void setMethodCaller(MainUtilProperties methodCaller) {
		this.methodCaller = methodCaller;
	}



	public Timestamp getApprovedDate() {
		return approvedDate;
	}



	public void setApprovedDate(Timestamp approvedDate) {
		this.approvedDate = approvedDate;
	}



	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}



	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String toString() {
		Formatter fmt = new Formatter();
		fmt.format("%20s %35s %35s \n", "Balance", "Approved Date", "Last Modified");
		NumberFormat usd = NumberFormat.getCurrencyInstance(Locale.US);
		SimpleDateFormat sdf=new SimpleDateFormat("EEEE dd.MM.yyyy hh:mm:ss");
		StringBuffer output = new StringBuffer();
		output.append("Account number: " + accountNo + "\n"
				+ "-----------------------------------------------------------------------\n");	
		fmt.format("%20s %35s %35s\n", usd.format(balance), sdf.format(approvedDate), sdf.format(lastModifiedDate));
		output.append(fmt);
		return output.toString();
	}

}
