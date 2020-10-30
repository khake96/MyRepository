package com.silverstarapp.service;

import java.util.List;

import org.postgresql.util.PGTimestamp;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.Customer;

public interface CustomerActions {
	
	public boolean login(String username, char[] password) throws BusinessException;
	public AccountApplication accountApplication(Customer customer, double startingBalance, PGTimestamp ts) throws BusinessException;
	public List<Account> transferFunds(int sourceID, int destinationID, double amount) throws BusinessException;
	public Account deposit(int accountID, double deposit) throws BusinessException;
	public Account withdraw(int accountID, double withdrawal) throws BusinessException;
	
}
