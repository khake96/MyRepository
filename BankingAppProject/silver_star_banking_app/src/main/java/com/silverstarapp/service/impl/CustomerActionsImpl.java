package com.silverstarapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.silverstarapp.dao.CustomerDAO;
import com.silverstarapp.dao.impl.CustomerDaoImpl;
import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Login;
import com.silverstarapp.service.CustomerActions;

/* This Service level CustomerAction class file contains the implementation for a Silver
 * Star Banking customer Interface file. 
 */

public class CustomerActionsImpl implements CustomerActions {
	
	private CustomerDAO customerDAO = new CustomerDaoImpl();

	@Override
	public Customer customerLogin(Login login) throws BusinessException {
		CustomerActionsImpl ownMethodCaller = new CustomerActionsImpl();
		Customer customer = null;
		// Check business logic for login			
		if (ownMethodCaller.loginBusinessValid(login)) {
			customer = customerDAO.customerLogin(login);
		} else {
			throw new BusinessException("So sorry! Your login doesn't meet our business requirements!");
		}
		return customer;
	}
	
	public boolean loginBusinessValid (Login login) {
		boolean result = false;
		if(login.getPassword().length() > 0 && login.getPassword().length() <= 20 && login.getUserName().length() > 0 
				&& login.getUserName().length() <= 20) result = true;		
		return result;
	}

	@Override
	public AccountApplication makeApplication(int customerID, double startingBalance, double incomeGross,
			double expenses) throws BusinessException {
		CustomerActionsImpl ownMethodCaller = new CustomerActionsImpl();
		AccountApplication application = new AccountApplication();
		// Business  logic check
		if(ownMethodCaller.applicationIsBusinessValid(customerID, startingBalance, incomeGross, expenses)) {
			application = customerDAO.makeApplication(customerID, incomeGross, expenses, startingBalance);
		} else {
			throw new BusinessException("So sorry! Your application doesn't meet our business requirements!");
		}
		return application;
	}
	
	public boolean applicationIsBusinessValid (int customerID, double startingBalance, double incomeGross, 
			double expenses) {
		boolean result = false;
		if(customerID>=0 && customerID <1000 && startingBalance > 500 && (incomeGross-expenses) > 0.1*incomeGross)
			result = true;
		return result;
	}


	@Override
	public List<Account> transferFunds(int customerID, Account debitAccount, Account creditAccount, double amount)
			throws BusinessException {
		List<Account> accountList = new ArrayList<>();
		// Execute business logic
		if(customerID > 0 && customerID < 1000 && debitAccount.getAccountNo() >= 100000 && debitAccount.getAccountNo() <= 500000 && amount>0 && amount>0) {
			try {
				 accountList = customerDAO.transferFunds(customerID, debitAccount, creditAccount, amount);
			} catch (Exception e) {
				
			}
		} else {
			throw new BusinessException("This transfer request doesn't meet our business requirements. Please try again.");
		}	
		return accountList;
	}

	@Override
	public Account depositAccount(Account account, double depositAmount) throws BusinessException {
		Account destinationAccount = new Account();
		// perform business logic
		if(account.getAccountNo() >= 100000 && account.getAccountNo() < 500000 &&  depositAmount > 0) {
			destinationAccount = customerDAO.depositAccount(account, depositAmount);
			customerDAO.setModifiedDate(destinationAccount.getAccountNo());
		} else {
			throw new BusinessException("Business criteria not met for deposit. Please try again.");
		}
		// Update account balance to return account
		destinationAccount = customerDAO.getAccountNoID(account.getAccountNo());
		return destinationAccount;
	}

	@Override
	public Account debitAccount(int customerID, Account account, double debitAmount) throws BusinessException {
		Account sourceAccount = new Account();
		// Perform business logic
		if(customerID>0 && customerID<1000 && account.getBalance() > debitAmount && debitAmount > 0) {
			sourceAccount = customerDAO.debitAccount(customerID, account, debitAmount);
			customerDAO.setModifiedDate(sourceAccount.getAccountNo());
			sourceAccount = customerDAO.getAccount(customerID, account.getAccountNo());
		} else {
			throw new BusinessException("Your debit request did not meet our business criteria. Please try again.\n"
					+ "Negative or zero debit values are not allowed.");	
		}
		// Update account balance to return account	
		return sourceAccount;
	}

	@Override
	public Account getAccount(int customerID, int accountNumber) throws BusinessException {
		Account account = new Account();
		// Perform business logic
		if(accountNumber >= 100000 && accountNumber < 500000) {
			account = customerDAO.getAccount(customerID, accountNumber);
		} else throw new BusinessException("Account number "+ accountNumber +" is not valid. Please try again.");
		return account;
	}
	
	@Override // Used for deposit accounts where ownership is not critical
	public Account getAccountNoID(int accountNumber) throws BusinessException {
		Account account = new Account();
		// Perform business logic
		if(accountNumber >= 100000 && accountNumber < 500000) {
			account = customerDAO.getAccountNoID(accountNumber);
		} else throw new BusinessException("Account number "+ accountNumber +" is not valid. Please try again.");
		return account;
	}

}
