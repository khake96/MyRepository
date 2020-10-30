package com.silverstarapp.dao.customer;

import java.util.List;

import com.silverstarapp.dao.employee.Account;
import com.silverstarapp.dao.employee.Application;
import com.silverstarapp.dao.employee.BusinessException;
import com.silverstarapp.dao.employee.Customer;
import com.silverstarapp.dao.employee.log;

/* This Silver Star Banking App customer DAO Interface specifies the 
 * requirements for the Database Acquisition Objects required by the
 * customers of the Silver Star Bank.
 */
public interface CustomerDAO {
	
	// Silver Star Banking Customer DAO read objects
				
	public List<Account> getAllCustomerAccounts(Customer customer) throws BusinessException;
	public Account getAccount(Long accountNumber) throws BusinessException;
	
	// Silver Star Banking Customer DAO insert objects
	
	public List<Account> transferFunds(Account debitAccount, Account creditAccount) throws BusinessException;
	public Account debitAccount(Account account, double debitAmount) throws BusinessException;
	public Account depositAccount(Account account, double depositAmount) throws BusinessException;
	
	// Silver Start Banking Customer DAO complex operations
	
	public void makeApplication(Customer customer, double newBalance) throws BusinessException;
	    
}
	


