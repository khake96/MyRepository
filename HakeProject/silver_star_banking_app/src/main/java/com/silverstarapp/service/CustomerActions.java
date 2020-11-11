package com.silverstarapp.service;

import java.util.List;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Login;

/* This Service level CustomerAction interface file contains the requirements for a Silver
 * Star Banking customer useage. The implementation is found in the corresponding Impl class.
 */

public interface CustomerActions {
	
	public Customer customerLogin(Login login) throws BusinessException;
	public AccountApplication makeApplication(int customerID, double startingBalance, double incomeGross, double expenses) throws BusinessException;
	public List<Account> transferFunds(int customerID, Account debitAccount, Account creditAccount, double amount) throws BusinessException;
	public Account depositAccount(Account account, double depositAmount) throws BusinessException;
	public Account debitAccount(int customerID, Account account, double debitAmount) throws BusinessException;
	public Account getAccount(int customerID, int accountNumber) throws BusinessException;
	public Account getAccountNoID(int accountNumber) throws BusinessException;
	
}
