package com.silverstarapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.silverstarapp.dao.EmployeeDAO;
import com.silverstarapp.dao.dbutil.EmployeeSearchQueries;
import com.silverstarapp.dao.dbutil.PostgresSqlConnection;
import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.main.SilverStarAppMain;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.AccountHistory;
import com.silverstarapp.model.Employee;
import com.silverstarapp.model.Login;

/* This Silver Star Banking App Employee DAO implementation provides
 * all of the body for the requirements specified in the EmployeeDAO 
 * Interface.
 */

public class EmpoloyeeDaoImpl implements EmployeeDAO {
	
	@SuppressWarnings({"unchecked" })
	@Override
	public AccountHistory viewCustomerAccount(int accountNumber) throws BusinessException , SQLException{
	    AccountHistory account = new AccountHistory();
	    @SuppressWarnings("rawtypes")
		List<Double> balanceArray = new ArrayList();
	    @SuppressWarnings("rawtypes")
		List<Double> transactionArray = new ArrayList();
	    @SuppressWarnings("rawtypes")
		List<Timestamp> timeArray = new ArrayList();
	    // transaction_value, balance, transaction_time
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = EmployeeSearchQueries.VIEW_ACCOUNT_HISTORY;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountNumber);	
			SilverStarAppMain.log.debug("Employee DAO viewCustomerAccount preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();	
			int count = 0;
			//SilverStarAppMain.log.debug("Before while loop" + transactionArray.toString());
			while(resultSet.next()) {
				timeArray.add(resultSet.getTimestamp("transaction_time")); 
				transactionArray.add(count, resultSet.getDouble("transaction_value"));
				balanceArray.add(count, resultSet.getDouble("balance"));
				count++;
			}
			account.setAccount_id(accountNumber);
			account.setBalance(balanceArray);
			//SilverStarAppMain.log.debug("After while loop balance: " +balanceArray.toString());
			account.setTransactionValue(transactionArray);
			//SilverStarAppMain.log.debug("After while loop transaction: " +transactionArray.toString());
			account.setTransactionDate(timeArray);
			//SilverStarAppMain.log.trace("after the while loop time: " +timeArray.toString());
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
		} 
		if(account.getAccount_id() == 0) {
			throw new BusinessException ("Unable to access account number: " + account.getAccount_id() + ". Account may not exist.");
		}
		return account;
	}


	@Override
	public ArrayList<AccountApplication> getAllApplications() throws BusinessException, SQLException {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<AccountApplication> accountApplicationList = new ArrayList();
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = EmployeeSearchQueries.VIEW_ALL_ACCOUNT_APPLICATIONS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			SilverStarAppMain.log.debug("Employee DAO getAllApplications preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				AccountApplication application = new AccountApplication(resultSet.getInt("account_appl_id"), 
						resultSet.getInt("customer_ID"), resultSet.getDouble("startingBalance"), 
						resultSet.getDouble("incomeGross"), resultSet.getDouble("expenses"));
				accountApplicationList.add(application); 
				//System.out.println("accountApplicationList from DaoImpl" + resultSet);
			}
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid account number!");
		} catch (PSQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid account number!");	
		}
		return (ArrayList<AccountApplication>) accountApplicationList;
	}


	@Override
	public Employee employeeLogin(Login login) throws BusinessException, SQLException {
		EmpoloyeeDaoImpl ownMethodCaller = new EmpoloyeeDaoImpl();
		Employee employee = null;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = EmployeeSearchQueries.EMPLOYEE_LOGIN;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login.getUserName());
			preparedStatement.setString(2, login.getPassword());
			SilverStarAppMain.log.debug("Employee DAO employeeLogin preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int employeeID = resultSet.getInt("employee_ID"); 
				employee = ownMethodCaller.setEmployee(employeeID);
			}
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");	
		}
		return employee;
	}

	@Override
	public Employee setEmployee(int employeeID) throws BusinessException, SQLException {
		Employee employee = new Employee();
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = EmployeeSearchQueries.EMPLOYEE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, employeeID);
			SilverStarAppMain.log.debug("Employee DAO setEmployee preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				employee.setId(employeeID); 
				employee.setFirstName(resultSet.getString("firstname"));
				employee.setLastName(resultSet.getString("lastname"));
				employee.setContact(resultSet.getString("contact"));		
			}
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid employee ID!");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid employee ID!");	
		}
		return employee;
	}
	
	public static int getLatestAccount() throws BusinessException {
		// get account number
		int newAccountNumber = 0;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = EmployeeSearchQueries.GET_LATEST_ACCOUNT_CREATED;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			SilverStarAppMain.log.debug("Employee DAO getLatestAccount preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
				newAccountNumber = resultSet.getInt("account_id");
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid account number!");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
		}
		return newAccountNumber;
	}


	@Override
	public boolean approveApplication(int applicationID, boolean approved) throws BusinessException, SQLException {
		boolean result = false;
		if(approved) {
			
			double startingBalance = 0;
			int customerID = 0;
			int newAccountNumber = 0;
				
			// get data from application
			try (Connection connection = PostgresSqlConnection.getConnection()){
				String sql = EmployeeSearchQueries.GET_APPLICATION_DATA;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, applicationID);
				SilverStarAppMain.log.debug("Employee DAO approveApplication preparedStatement: "+ preparedStatement + ".");
				ResultSet resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					startingBalance = resultSet.getDouble("startingBalance");
					customerID = resultSet.getInt("customer_id");	
				} else SilverStarAppMain.log.debug(applicationID + " does not meet our business criteria.");
			} catch (ClassNotFoundException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("Invalid application number!");
			} catch (SQLException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("SQL Error. Invalid application number!");	
			}
			
			// create new account
			try (Connection connection = PostgresSqlConnection.getConnection()){
				String sql = EmployeeSearchQueries.CREATE_NEW_ACCOUNT;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setDouble(1, startingBalance);
				SilverStarAppMain.log.debug("Employee DAO createNewAccount preparedStatement: "+ preparedStatement + ".");
				int resultSet = preparedStatement.executeUpdate();
				if(resultSet==1) {
					// check for success?
				}
			} catch (ClassNotFoundException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("Invalid customer ID!");
			} catch (SQLException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("Invalid customer ID!");	
			}
			
			// get account number
			newAccountNumber = EmpoloyeeDaoImpl.getLatestAccount();
			
			// create new customeraccountlink entry
			try (Connection connection = PostgresSqlConnection.getConnection()){
				String sql = EmployeeSearchQueries.CREATE_NEW_USER_ACCOUNT_ASSOCIATION;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, customerID);
				preparedStatement.setInt(2, newAccountNumber);
				SilverStarAppMain.log.debug("Employee DAO createNewUserAccount preparedStatement: "+ preparedStatement + ".");
				int resultSet = preparedStatement.executeUpdate();
				if(resultSet == 1) {
					// check for success?
				}
			} catch (ClassNotFoundException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("Invalid customer ID!");
			} catch (SQLException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("Invalid customer ID!");	
			}
			
			// Create a new accountHistory table entry
			try (Connection connection = PostgresSqlConnection.getConnection()){
				String sql = EmployeeSearchQueries.CREATE_NEW_ACCOUNT_HISORY_ASSOCIATION;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, newAccountNumber);
				preparedStatement.setDouble(2, startingBalance);
				preparedStatement.setDouble(3, startingBalance);
				SilverStarAppMain.log.debug("Employee DAO createNewAccountHistory preparedStatement: "+ preparedStatement + ".");
				int resultSet = preparedStatement.executeUpdate();
				if(resultSet == 1) {
					// check for success?
				}
			} catch (ClassNotFoundException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("Invalid data, please try again!");
			} catch (SQLException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("We have experienced database issues. Please try again.");	
			}
			
			// delete application from the application table	
			try (Connection connection = PostgresSqlConnection.getConnection()){
				String sql = EmployeeSearchQueries.DELETE_ACCNT_APPLICATION;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, applicationID);
				SilverStarAppMain.log.debug("Employee DAO deleteAccountApplication preparedStatement: "+ preparedStatement + ".");
				int resultSet = preparedStatement.executeUpdate();
				if(resultSet == 1) {
					result = true;
				}	
			} catch (ClassNotFoundException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("Invalid application number!");
			} catch (SQLException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("SQL Error.  Invalid application number!");	
			}

		} // end if statement for account approved condition
		else
			{
			// delete application from the application table	
			try (Connection connection = PostgresSqlConnection.getConnection()){
				String sql = EmployeeSearchQueries.DELETE_ACCNT_APPLICATION;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, applicationID);
				SilverStarAppMain.log.debug("Employee DAO deletAccountApplication preparedStatement: "+ preparedStatement + ".");
				int resultSet = preparedStatement.executeUpdate();
				if(resultSet == 1) {
					result = true;
					SilverStarAppMain.log.error("Account application denied. Application ID: "+ applicationID);
				}	
			} catch (ClassNotFoundException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("Invalid application number!");
			} catch (SQLException e) {
				SilverStarAppMain.log.debug(e.getMessage());
				throw new BusinessException("SQL Error. Invalid application number!");	
			}
			} return result;
	} // end application approved method
}
