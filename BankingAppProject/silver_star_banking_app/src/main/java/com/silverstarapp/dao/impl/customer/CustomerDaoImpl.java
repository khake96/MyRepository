package com.silverstarapp.dao.impl.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.silverstarapp.dao.customer.CustomerDAO;
import com.silverstarapp.dao.dbutil.CustomerSearchQueries;
import com.silverstarapp.dao.dbutil.PostgresSqlConnection;
import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Login;

/* This Silver Star Banking App Employee DAO implementation provides
 * all of the body for the requirements specified in the EmployeeDAO 
 * Interface.
 */


public class CustomerDaoImpl implements CustomerDAO {

	@Override
	public List<Account> getAllCustomerAccounts(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(int accountNumber) throws BusinessException {
		Account account = new Account();
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = CustomerSearchQueries.CHECK_BALANCE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				account.setAccountNo(accountNumber); 
				account.setBalance(resultSet.getDouble("balance"));		
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException("Invalid account number!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Invalid account number!");	
		}
		return account;
	}

	@Override
	public List<Account> transferFunds(Account debitAccount, Account creditAccount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account debitAccount(Account account, double debitAmount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account depositAccount(Account account, double depositAmount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makeApplication(Customer customer, double newBalance) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Customer setCustomer(int customerID) throws BusinessException {
		Customer customer = new Customer();
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = CustomerSearchQueries.CUSTOMER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer.setId(customerID); 
				customer.setFirstName(resultSet.getString("firstname"));
				customer.setLastName(resultSet.getString("lastname"));
				customer.setContact(resultSet.getString("contact"));		
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException("Invalid customer ID!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("Invalid customer ID!");	
		}
		return customer;
	}

	@Override
	public int customerLogin(Login login) throws BusinessException {
		int customerID=-1;
		String customerPassword = null;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = CustomerSearchQueries.CUSTOMER_LOGIN;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, login.getUserName());
			customerPassword = login.toString(login.getPassword());
			preparedStatement.setString(2, customerPassword);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
			customerID = resultSet.getInt("customer_ID");	
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		} catch (PSQLException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerID;
	}


}