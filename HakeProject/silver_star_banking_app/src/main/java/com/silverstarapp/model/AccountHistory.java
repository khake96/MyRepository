package com.silverstarapp.model;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.main.SilverStarAppMain;
import com.silverstarapp.service.CustomerActions;
import com.silverstarapp.service.impl.CustomerActionsImpl;

/* This AccountHistory class file contains the data required for a Silver
 * Star Banking account history. The data for each transaction is stored in
 * the database and viewing this function is only available to Silver Star 
 * Banking employees.
 */

public class AccountHistory {
	private int account_id;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<Double> transactionValue = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Double> balance = new ArrayList();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Timestamp> transactionDate = new ArrayList();

	
	public AccountHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountHistory(int account_id, List<Double> transactionValue, List<Double> balance,
			List<Timestamp> transactionDate) {
		super();
		this.account_id = account_id;
		this.transactionValue = transactionValue;
		this.balance = balance;
		this.transactionDate = transactionDate;
	}

	// Provided so that test cases can be run comparing objects
	@Override
    public boolean equals(Object obj){
        AccountHistory x = (AccountHistory) obj;
        boolean status = false;
        if(this.account_id == x.account_id){
            status = true;
        }
        return status;
    }


	public int getAccount_id() {
		return account_id;
	}



	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}



	public List<Double> getTransactionValue() {
		return transactionValue;
	}



	public void setTransactionValue(List<Double> transactionValue) {
		this.transactionValue = transactionValue;
	}



	public List<Double> getBalance() {
		return balance;
	}



	public void setBalance(List<Double> balance) {
		this.balance = balance;
	}



	public List<Timestamp> getTransactionDate() {
		return transactionDate;
	}



	public void setTransactionDate(List<Timestamp> transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		CustomerActions actions = new CustomerActionsImpl();
		Account account = new Account();
		try {
			account = actions.getAccountNoID(account_id);
		} catch (BusinessException e) {
			SilverStarAppMain.log.trace(e.getMessage());
		}
		Formatter fmt = new Formatter();
		fmt.format("%15s %15s %15s %35s\n", "Debit", "Credit", "Balance", "Transaction Time");
		NumberFormat usd = NumberFormat.getCurrencyInstance(Locale.US);
		SimpleDateFormat sdf=new SimpleDateFormat("EEEE dd.MM.yyyy hh:mm:ss");
		StringBuffer output = new StringBuffer();
		int count = 0;
		
		output.append("Account number: " + account_id + "            Account Approval Date: " + sdf.format(account.getApprovedDate())+ "\n"
				+ "-----------------------------------------------------------------------\n");	
		//output.append(fmt);
		
		for(double d:transactionValue) {
			if (d < 0) {
				 fmt.format("%15s %15s %15s %35s\n", usd.format(d), " ", usd.format(balance.get(count)), sdf.format(transactionDate.get(count)));
			}
			else {
				fmt.format("%15s %15s %15s %35s\n", " ", usd.format(d), usd.format(balance.get(count)), sdf.format(transactionDate.get(count)));
			}	
			count++;
		}
		output.append(fmt);
		return output.toString();
	}
	
	

}
