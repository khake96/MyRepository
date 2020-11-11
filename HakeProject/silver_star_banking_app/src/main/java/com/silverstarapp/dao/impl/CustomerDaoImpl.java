package com.silverstarapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.silverstarapp.dao.CustomerDAO;
import com.silverstarapp.dao.dbutil.CustomerSearchQueries;
import com.silverstarapp.dao.dbutil.DbUtil;
import com.silverstarapp.dao.dbutil.PostgresSqlConnection;
import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.main.SilverStarAppMain;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Login;

/* This Silver Star Banking App Customer DAO implementation provides
 * all of the body for the requirements specified in the CustomerDAO 
 * Interface.
 * 
 * Possible improvements: remove some of the try with resources to DbUtil
 */


public class CustomerDaoImpl implements CustomerDAO {
	

	DbUtil methodCaller = new DbUtil();

	@Override
	public List<Account> getAllCustomerAccounts(Customer customer) throws BusinessException {
		return null;
	}

	@Override
	public void setModifiedDate (int account_id)  throws BusinessException {
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = CustomerSearchQueries.UPDATE_MODIFIED_DATE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, account_id);
			SilverStarAppMain.log.debug("User DAO setModifiedDate preparedStatement: "+ preparedStatement + ".");
			@SuppressWarnings("unused")
			int result = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid debit input!");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid debit input!");	
		}
	}
	
	@Override
	public Account getAccount(int customerID, int accountNumber) throws BusinessException {
	    Account account = new Account();
	    boolean isCustAccount = methodCaller.isCustAccount(accountNumber, customerID);
	    if(isCustAccount) {
			try (Connection connection = PostgresSqlConnection.getConnection()){
				String sql = CustomerSearchQueries.CHECK_BALANCE;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, accountNumber);
				SilverStarAppMain.log.debug("User DAO getAccount preparedStatement: "+ preparedStatement + ".");
				ResultSet resultSet = preparedStatement.executeQuery();			
				if(resultSet.next()) {
					account.setAccountNo(accountNumber); 
					account.setBalance(resultSet.getDouble("balance"));	
					account.setLastModifiedDate(resultSet.getTimestamp("lastmodifieddate"));
					account.setApprovedDate(resultSet.getTimestamp("approveddate"));
					//SilverStarAppMain.log.trace("account approved date: " + account.getApprovedDate());
				}
			} catch (ClassNotFoundException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("Invalid account number!");
			} catch (SQLException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("Invalid account number!");	
			}	    	
	    } else {
	    	SilverStarAppMain.log.info("\nCustomer ID: "+customerID+" does not own account number: "+accountNumber);
	    	SilverStarAppMain.log.info("Cannot complete transaction.");
	    }
		return account;
	}
	public Account getAccountNoID(int accountNumber) throws BusinessException {
	    Account account = new Account();
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = CustomerSearchQueries.CHECK_BALANCE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountNumber);
			SilverStarAppMain.log.debug("User DAO getAccountNoID preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				account.setAccountNo(accountNumber); 
				account.setBalance(resultSet.getDouble("balance"));	
				account.setLastModifiedDate(resultSet.getTimestamp("lastmodifieddate"));
				account.setApprovedDate(resultSet.getTimestamp("approveddate"));
			}
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid account number!");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid account number!");	
		}
		if (account.getAccountNo()==0) {
	    	SilverStarAppMain.log.info("\nInvalid account id: "+accountNumber);
	    	SilverStarAppMain.log.info("Cannot complete transaction.");
	    }
		return account;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Account> transferFunds(int customerID, Account debitAccount, Account creditAccount, double amount) throws BusinessException {
		// Check debit account belongs to the customer
		CustomerDaoImpl ownMethodCaller = new CustomerDaoImpl();
		@SuppressWarnings("unchecked")
		List<Account> accountList = new ArrayList();
		// Perform debit and deposit
		try {
		accountList.add(ownMethodCaller.debitAccount(customerID, debitAccount, amount));
		ownMethodCaller.setModifiedDate(accountList.get(0).getAccountNo());
		// Only perform the credit if the debit has been completed successfully
		if(accountList.size()>0) {
			accountList.add(ownMethodCaller.depositAccount(creditAccount, amount));
			ownMethodCaller.setModifiedDate(accountList.get(1).getAccountNo());		
		}
		} catch (Exception e) {
			SilverStarAppMain.log.debug(e.getMessage());
		}
		return accountList;
	} // end transfer funds

	@Override
	public Account debitAccount(int customerID, Account account, double debitAmount) throws BusinessException {			
		CustomerDaoImpl ownMethodCaller = new CustomerDaoImpl();
		// Check debit account belongs to the customer
		boolean isCustAccount = methodCaller.isCustAccount(account.getAccountNo(), customerID);
		if(isCustAccount) {
			// Execute withdrawal
			try (Connection connection = PostgresSqlConnection.getConnection()){
				String sql = CustomerSearchQueries.WITHDRAW;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setDouble(1, debitAmount);
				preparedStatement.setInt(2, account.getAccountNo());
				SilverStarAppMain.log.debug("User DAO debitAccount preparedStatement: "+ preparedStatement + ".");
				@SuppressWarnings("unused")
				int i = preparedStatement.executeUpdate();		
			} catch (ClassNotFoundException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("Invalid debit input!");
			} catch (SQLException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("Invalid debit input!");	
			}	
			// Update account history
			try {
				account = ownMethodCaller.getAccount(customerID, account.getAccountNo());
				ownMethodCaller.setAccountHistory(account, -debitAmount);
			} catch (BusinessException e) {
				SilverStarAppMain.log.debug(e.getMessage());
			}
		}				
		return account;
	}
	
@Override	
public Account setAccountHistory(Account account, double transactionAmount) throws BusinessException {
	// Update account history
	try (Connection connection = PostgresSqlConnection.getConnection()){		
		String sql = CustomerSearchQueries.SET_ACCOUNT_HISTORY;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, account.getAccountNo());
		preparedStatement.setDouble(2, transactionAmount);
		preparedStatement.setDouble(3, account.getBalance());
		SilverStarAppMain.log.debug("User DAO setAccountHistory preparedStatement: "+ preparedStatement + ".");
		@SuppressWarnings("unused")
		int i = preparedStatement.executeUpdate();		
	} catch (ClassNotFoundException e) {
		SilverStarAppMain.log.debug(e.getMessage());
		throw new BusinessException("Unable to update account history!");
	} catch (SQLException e) {
		SilverStarAppMain.log.debug(e.getMessage());
		throw new BusinessException("SQL issues. Unable to update account history!");	
	}	
	return account;
}
	
	@Override
	public Account depositAccount(Account account, double depositAmount) throws BusinessException {
		CustomerDaoImpl ownMethodCaller = new CustomerDaoImpl();
		int depositComplete = 0;
		// Perform deposit
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = CustomerSearchQueries.DEPOSIT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, depositAmount);
			preparedStatement.setInt(2, account.getAccountNo());
			SilverStarAppMain.log.debug("User DAO depositAccount preparedStatement: "+ preparedStatement + ".");
			depositComplete = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid deposit input!");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid deposit input!");	
		}
		// Update the account balance for return
		if (depositComplete==1) {
			account = ownMethodCaller.getAccountNoID(account.getAccountNo());
			ownMethodCaller.setModifiedDate(account.getAccountNo());
		}
		// Update account history
		try {
			ownMethodCaller.setAccountHistory(account, depositAmount);
		} catch (BusinessException e) {
			SilverStarAppMain.log.debug(e.getMessage());
		}
		return account;
	}
	
	public AccountApplication viewLatestAccountApplication() throws BusinessException {
		AccountApplication application = new AccountApplication();
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = CustomerSearchQueries.VIEW_ACCOUNT_APPLICATION;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			SilverStarAppMain.log.debug("User DAO viewLatestAccountApplication preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			application.setApplicationID(resultSet.getInt("account_appl_ID"));
			application.setCustomer(resultSet.getInt("customer_ID"));
			application.setStartingBalance(resultSet.getDouble("startingBalance"));
			application.setIncomeGross(resultSet.getDouble("incomeGross"));
			application.setExpenses(resultSet.getDouble("expenses"));
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid account number!");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid account number!");	
		}
		return application;
	}

	@SuppressWarnings("unused")
	@Override
	public AccountApplication makeApplication(int customerID, double incomeGross, double expenses, double newBalance) throws BusinessException {
		CustomerDaoImpl ownMethodCaller = new CustomerDaoImpl();
		AccountApplication accountApplication = new AccountApplication();
		int result = 0;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = CustomerSearchQueries.APPLY_FOR_ACCOUNT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerID);
			preparedStatement.setDouble(2, newBalance );
			preparedStatement.setDouble(3, incomeGross);
			preparedStatement.setDouble(4, expenses);
			SilverStarAppMain.log.debug("User DAO makeApplication preparedStatement: "+ preparedStatement + ".");
			result = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid deposit input!");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid deposit input!");	
		}
			accountApplication = ownMethodCaller.viewLatestAccountApplication();
		return accountApplication;
	}
	
	@Override
	public Customer setCustomer(int customerID) throws BusinessException {
		Customer customer = new Customer();
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = CustomerSearchQueries.CUSTOMER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerID);
			SilverStarAppMain.log.debug("User DAO setCustomer preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer.setId(customerID); 
				customer.setFirstName(resultSet.getString("firstname"));
				customer.setLastName(resultSet.getString("lastname"));
				customer.setContact(resultSet.getString("contact"));		
			}
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid customer ID!");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid customer ID!");	
		}
		return customer;
	}

	@Override
	public Customer customerLogin(Login login) throws BusinessException {
		CustomerDaoImpl ownMethodCaller = new CustomerDaoImpl();
		Customer customer = new Customer();
		int customerID= -1;	
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = CustomerSearchQueries.CUSTOMER_LOGIN;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login.getUserName());
			preparedStatement.setString(2, login.getPassword());
			SilverStarAppMain.log.debug("User DAO customerLogin preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
			customerID = resultSet.getInt("customer_ID");	
			}
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		} catch (PSQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException(e.getMessage());	
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
		}
		try {
			customer = ownMethodCaller.setCustomer(customerID);
		} catch (Exception e) {
			SilverStarAppMain.log.debug(e.getMessage());
		}
		return customer;
	}


}