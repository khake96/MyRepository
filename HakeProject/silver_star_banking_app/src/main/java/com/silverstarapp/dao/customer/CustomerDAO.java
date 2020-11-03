package com.silverstarapp.dao.customer;

import java.util.List;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Login;

/* This Silver Star Banking App customer DAO Interface specifies the 
 * requirements for the Database Acquisition Objects required by the
 * customers of the Silver Star Bank.
 */
public interface CustomerDAO {
	
	// Silver Star Banking Customer DAO read objects
				
	public List<Account> getAllCustomerAccounts(Customer customer) throws BusinessException;
	public Account getAccount(int accountNumber) throws BusinessException;
	
	// Silver Star Banking Customer DAO insert objects
	
	public List<Account> transferFunds(Account debitAccount, Account creditAccount) throws BusinessException;
	public Account debitAccount(Account account, double debitAmount) throws BusinessException;
	public Account depositAccount(Account account, double depositAmount) throws BusinessException;
	
	// Silver Start Banking Customer DAO complex operations
	
	public void makeApplication(Customer customer, double newBalance) throws BusinessException;
	public int customerLogin(Login login) throws BusinessException;
	public Customer setCustomer(int customerID) throws BusinessException;
	    
}
	


