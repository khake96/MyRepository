package com.revature.ers.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;

import com.revature.ers.DAO.DAO;
import com.revature.ers.DAO.PostgresSqlConnection;
import com.revature.ers.DAO.SearchQueries;
import com.revature.ers.model.Login;
import com.revature.ers.model.Request;
import com.revature.ers.model.User;

public class DaoImpl implements DAO {

	@Override
	public List<Request> getCompanyRequestHistory(User manager) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Request> reimbRequestCompanyHistory = new ArrayList();
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = SearchQueries.VIEW_COMPANY_REQUESTS_HISTORY;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			com.revature.ers.model.RevatureErsMain.log.debug("Users DAO setModifiedDate preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Request request = new Request(resultSet.getInt("reimb_id"), resultSet.getDouble("reimb_amount"), 
					resultSet.getTimestamp("reimb_resolved"), resultSet.getTimestamp("reimb_submitted"), 
					resultSet.getString("reimb_description"), resultSet.getBlob("reimb_receipt"), 
					resultSet.getInt("reimb_author"), resultSet.getInt("reimb_resolver"), 
					resultSet.getInt("reimb_status_id"), resultSet.getInt("reimb_type_id"));
				reimbRequestCompanyHistory.add(request); 
			} 
		} catch (PSQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return reimbRequestCompanyHistory;		
	} 

	@Override
	public List<Request> getSingleEmployeeHistory(User employee) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Request> reimbRequestSingleEmployeeHistory = new ArrayList();
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = SearchQueries.VIEW_SINGLE_EMPLOYEE_REQUEST_HISTORY;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, employee.getiD());	
			com.revature.ers.model.RevatureErsMain.log.debug("DAO getSingleEmployeeHistory preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();	
			while(resultSet.next()) {
				Request request = new Request(resultSet.getInt("reimb_id"), resultSet.getDouble("reimb_amount"), 
					resultSet.getTimestamp("reimb_resolved"), resultSet.getTimestamp("reimb_submitted"), 
					resultSet.getString("reimb_description"), resultSet.getBlob("reimb_receipt"), 
					resultSet.getInt("reimb_author"), resultSet.getInt("reimb_resolver"), 
					resultSet.getInt("reimb_status_id"), resultSet.getInt("reimb_type_id"));
				reimbRequestSingleEmployeeHistory.add(request); 
			}		
		} catch (PSQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return reimbRequestSingleEmployeeHistory;
	}

	@Override
	public List<Request> getPendingRequests(User manager) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Request> reimbPendingRequests = new ArrayList();
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = SearchQueries.VIEW_PENDING_REQUESTS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 1);	
			com.revature.ers.model.RevatureErsMain.log.debug("DAO getPendingRequests preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();	
			while(resultSet.next()) {
				Request request = new Request(resultSet.getInt("reimb_id"), resultSet.getDouble("reimb_amount"), 
					resultSet.getTimestamp("reimb_resolved"), resultSet.getTimestamp("reimb_submitted"), 
					resultSet.getString("reimb_description"), resultSet.getBlob("reimb_receipt"), 
					resultSet.getInt("reimb_author"), resultSet.getInt("reimb_resolver"), 
					resultSet.getInt("reimb_status_id"), resultSet.getInt("reimb_type_id"));
				reimbPendingRequests.add(request); 
			}
		} catch (PSQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return reimbPendingRequests;
	}

	@Override
	public Request processRequest(User manager, Request request, boolean approved) {
		int approval = 0;
		if (approved) {
			approval = 1; 
		} else approval = 3;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = SearchQueries.SET_REQUEST_STATUS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, approval);	
			preparedStatement.setInt(2, request.getReimbId());	
			preparedStatement.setInt(3, manager.getiD());
			com.revature.ers.model.RevatureErsMain.log.debug("DAO processRequest preparedStatement: "+ preparedStatement + ".");
			int result = preparedStatement.executeUpdate();	
			if (result==1) {
				System.out.println("Set status successful.");
			}
		} catch (PSQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// need to execute request to pull fixed request to return
		return request;
	} 
	
	public Request getLastRequestEntered() {
		Request lastRequestEntered = null;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = SearchQueries.LAST_REQUEST_ENTERED;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			System.out.println("DAO getLastRequestEntered preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();	
			while(resultSet.next()) {
				lastRequestEntered = new Request(resultSet.getInt("reimb_id"), resultSet.getDouble("reimb_amount"), 
					resultSet.getTimestamp("reimb_resolved"), resultSet.getTimestamp("reimb_submitted"), 
					resultSet.getString("reimb_description"), resultSet.getBlob("reimb_receipt"), 
					resultSet.getInt("reimb_author"), resultSet.getInt("reimb_resolver"), 
					resultSet.getInt("reimb_status_id"), resultSet.getInt("reimb_type_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastRequestEntered;
	}

	@Override
	public Request makeRequest(Request request) {
		DaoImpl ownMethodCaller = new DaoImpl();
		Request application = null;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = SearchQueries.MAKE_NEW_REQUEST;
//			String sql = "INSERT INTO ers_reimb(reimb_id, reimb_amount, reimb_submitted,"
//			+ " reimb_description, reimb_receipt, reimb_status_id, reimb_author, reimb_type_id)"
//			+ " VALUES (default, 3240.45, current_timestamp, 'Company picnic costs.' , null, 1, 106, 1);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, request.getReimbAmount());	
			preparedStatement.setString(2, request.getReimbDescription());	
			preparedStatement.setInt(3, request.getReimbAuthor());	
			preparedStatement.setInt(4, request.getReimbTypeId());
			System.out.println("DAO makeRequest preparedStatement: "+ preparedStatement + ".");
			int result = preparedStatement.executeUpdate();	
			if (result==1) {
				System.out.println("Set status successful.");
			}
		} catch (PSQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		application = ownMethodCaller.getLastRequestEntered();
		return application;
	}

	@Override
	public User userLogin(Login loginUser) {
		User user = new User();
		System.out.println(loginUser.toString());
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = SearchQueries.USER_LOGIN;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginUser.getPassword());
			preparedStatement.setString(2, loginUser.getUserName());
			com.revature.ers.model.RevatureErsMain.log.debug("User Login preparedStatement: "+ preparedStatement + ".");
			System.out.println("User Login preparedStatement: "+ preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				user.setiD(resultSet.getInt("ers_user_id"));
				user.setFirstName(resultSet.getString("user_first_name"));
				user.setLastName(resultSet.getString("user_last_name"));
				user.setRole(resultSet.getInt("user_role_id"));
			} else System.out.println("Not a valid employee username or password. Please try again.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		}
		System.out.println(user.toString());
		return user;
	}

	@Override
	public Request updateRequest(User user, Request request) {
		return null;
	}

	@Override
	public Request insertReceipt(Request request, int receiptImage) {
		@SuppressWarnings("unused")
		Request requestReceiptAdded = null;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = SearchQueries.UPDATE_REIMBURSEMENT_RECEIPT_IMAGE;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBlob(1, request.getReimbReceipt());	
			preparedStatement.setInt(2, request.getReimbId());	
			com.revature.ers.model.RevatureErsMain.log.debug("DAO insertReceipt preparedStatement: "+ preparedStatement + ".");
			int result = preparedStatement.executeUpdate();	
			if (result==1) {
				System.out.println("Add receipt image successful.");
			}
		} catch (PSQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return request;
	}
		
}
