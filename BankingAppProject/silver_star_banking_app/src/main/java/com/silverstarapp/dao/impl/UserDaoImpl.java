package com.silverstarapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.silverstarapp.dao.UserDAO;
import com.silverstarapp.dao.dbutil.PostgresSqlConnection;
import com.silverstarapp.dao.dbutil.UserSearchQueries;
import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.main.SilverStarAppMain;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.User;

/* This Silver Star Banking App User DAO implementation provides
 * all of the body for the requirements specified in the UserDAO 
 * Interface.
 */

public class UserDaoImpl implements UserDAO {

	@Override
	public boolean isUniqueUserName(String userName) throws BusinessException {
		boolean isUnique = false;
		// check for return of the username from the DB	
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = UserSearchQueries.USER_NAME_UNIQUE_CHECK;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			SilverStarAppMain.log.debug("User DAO isUniqueUserName prepared statement: "+ preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				isUnique = true;
			}	
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Invalid User name checkout.");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("SQL ERROR Invalid User name checkout.");	
		}
		return isUnique;
	}

	@Override
	public Customer createNewCustomer(User user) throws BusinessException {
	// Insert the user data into the appropriate tables to create a new Silver Star Banking customer
		
		Customer customer = new Customer();
		int addressID=-1;
		
		// Insert into customer table
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = UserSearchQueries.CUSTOMER_TABLE_INSERT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setLong(3, user.getContact());
			SilverStarAppMain.log.debug("User DAO createNewCustomer prepared statement: "+ preparedStatement);
			int result = preparedStatement.executeUpdate();
			if(result==1) {
				// Update success condition
				System.out.println("New customer table insert.");
			}	
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("customer table insert issue");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("SQL customer table insert issue");	
		}

		// Get Latest Customer ID
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = UserSearchQueries.GET_LATEST_CUSTOMER_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			SilverStarAppMain.log.debug("User DAO GetLatest_Customer_ID prepared statement: "+ preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer.setId(resultSet.getInt("customer_id"));
			}
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Get customer id from table issue.");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("SQL ERROR Get customer id from table issue");	
		}
		
		// Insert into customer logins table
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = UserSearchQueries.CUSTOMER_LOGIN_TABLE_INSERT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword().toString());
			preparedStatement.setInt(3, customer.getId());
			SilverStarAppMain.log.debug("User DAO CUstomerLoginTableInsert prepared statement: "+ preparedStatement);
			int result = preparedStatement.executeUpdate();
			if(result==1) {
				// Update success condition
				//System.out.println("New customer login table insert.");
			}	
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("customer login table insert issue");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("SQL customer login table insert issue");	
		}	
		
		// Insert into addresses table
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = UserSearchQueries.ADDRESSES_TABLE_INSERT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user.getAddress().getHouseNo());
			preparedStatement.setString(2, user.getAddress().getStreetName());
			preparedStatement.setString(3, user.getAddress().getSecondLine());
			preparedStatement.setString(4, user.getAddress().getCity());
			preparedStatement.setString(5, user.getAddress().getState());
			preparedStatement.setString(6, user.getAddress().getZip());
			SilverStarAppMain.log.debug("User DAO AddressTableInsert prepared statement: "+ preparedStatement);
			int result = preparedStatement.executeUpdate();
			if(result==1) {
				// Update success condition
				SilverStarAppMain.log.debug("User creation success!");
			}	
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("customer addresses table insert issue");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("SQL ERROR customer addressses table insert issue");	
		}	
		
		// Get Latest Customer Address ID
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = UserSearchQueries.GET_LATEST_ADDRESS_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			SilverStarAppMain.log.debug("User DAO getLatestAddress prepared statement: "+ preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				addressID = resultSet.getInt("address_id");
			}
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("Get address id from table issue.");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("SQL ERROR Get address id from table issue");	
		}		

		// Insert into customer address link table
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = UserSearchQueries.CUST_ADDRESS_LINK_INSERT;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer.getId());
			preparedStatement.setInt(2, addressID);
			SilverStarAppMain.log.debug("User DAO customer_address_link_insert prepared statement: "+ preparedStatement);
			int result = preparedStatement.executeUpdate();
			if(result==1) {
				// Update success condition
				SilverStarAppMain.log.debug("Customer Link Table update complete!");
			}	
		} catch (ClassNotFoundException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("customer address link table insert issue");
		} catch (SQLException e) {
			SilverStarAppMain.log.debug(e.getMessage());
			throw new BusinessException("SQL ERROR customer address link table insert issue");	
		}	
		
		customer.setFirstName(user.getFirstName());
		customer.setLastName(user.getLastName());
		customer.setContact(Long.toString(user.getContact()));
		
		return customer;
	}

}
