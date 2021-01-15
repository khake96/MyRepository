package com.silverstarapp.dao;

import java.util.List;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Login;

/* This Silver Star Banking App customer DAO Interface specifies the 
 * requirements for the Database Acquisition Objects required by the
 * customers of the Silver Star Bank.
 */

public interface CustomerDAO {
	
	// Silver Star Banking Customer DAO read objects
				
	public List<Account> getAllCustomerAccounts(Customer customer) throws BusinessException;
	public Account getAccount(int customerID, int accountNumber) throws BusinessException;
	public Account getAccountNoID(int accountNumber) throws BusinessException;
	
	// Silver Star Banking Customer DAO insert objects
	
	public Account setAccountHistory(Account account, double transactionAmount) throws BusinessException;
	public List<Account> transferFunds(int customerID, Account debitAccount, Account creditAccount, double amount) throws BusinessException;
	public Account debitAccount(int customerID, Account account, double debitAmount) throws BusinessException;
	public Account depositAccount(Account account, double depositAmount) throws BusinessException;
	void setModifiedDate(int account_id) throws BusinessException;
	
	// Silver Start Banking Customer DAO complex operations
	
	public AccountApplication makeApplication(int customerID, double incomeGross, double expenses, double newBalance) throws BusinessException;
	public Customer customerLogin(Login login) throws BusinessException;
	public Customer setCustomer(int customerID) throws BusinessException;

	    
}
	


