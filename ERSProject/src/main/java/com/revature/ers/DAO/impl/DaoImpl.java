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
import com.revature.ers.model.HistoryDTO;
import com.revature.ers.model.Login;
import com.revature.ers.model.PendingDTO;
import com.revature.ers.model.ProcessRequestDTO;
import com.revature.ers.model.Request;
import com.revature.ers.model.User;

public class DaoImpl implements DAO {

	@Override
	public List<HistoryDTO> getCompanyRequestHistory(User manager) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<HistoryDTO> reimbRequestCompanyHistory = new ArrayList();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<HistoryDTO> updateResolverList = new ArrayList();
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = SearchQueries.VIEW_COMPANY_REQUESTS_HISTORY;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			com.revature.ers.model.RevatureErsMain.log.debug("Users DAO getCompanyRequestHistory preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				HistoryDTO request = new HistoryDTO(resultSet.getInt("reimb_id"), 
					resultSet.getDouble("reimb_amount"), 
					resultSet.getTimestamp("reimb_resolved"), 
					resultSet.getTimestamp("reimb_submitted"), 
					resultSet.getString("reimb_description"), 
					resultSet.getInt("reimb_author"), 
					resultSet.getInt("reimb_resolver"), 
					resultSet.getInt("reimb_status_id"), 
					resultSet.getInt("reimb_type_id"),
					resultSet.getString("user_last_name"),
					resultSet.getString("user_first_name"));
				reimbRequestCompanyHistory.add(request); 
				//System.out.println(request.toString());
			}
			// Get resolver names and add to the HistoryDTO objects
			String sql2 = SearchQueries.ADD_RESOLVER_TO_HISTORY;
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			com.revature.ers.model.RevatureErsMain.log.debug("Users DAO ADD_RESOLVER_TO_HISTORY preparedStatement: "+ preparedStatement2 + ".");
			ResultSet resultSet2 = preparedStatement2.executeQuery();
			while(resultSet2.next()) {
				HistoryDTO request = new HistoryDTO(resultSet2.getInt("reimb_id"), 
					resultSet2.getString("user_last_name"),
					resultSet2.getString("user_first_name"));
				updateResolverList.add(request); 
				//System.out.println(request);
			}			

				for(HistoryDTO entry: reimbRequestCompanyHistory) {
					for(HistoryDTO updateEntry: updateResolverList) {
						if (updateEntry.reimbId == entry.reimbId) {
							entry.process_first_name = updateEntry.process_first_name;
							entry.process_last_name = updateEntry.process_last_name;
						}
					}
				}	

		} catch (PSQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		} catch (SQLException e1) {
			com.revature.ers.model.RevatureErsMain.log.debug(e1.getMessage());
		}		
		return reimbRequestCompanyHistory;		
	} 
	
	@Override
	public List<HistoryDTO> getEmployeeRequestHistory(User employee, Integer employeeId) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<HistoryDTO> reimbRequestEmployeeHistory = new ArrayList();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<HistoryDTO> updateResolverList = new ArrayList();
		try (Connection connection = PostgresSqlConnection.getConnection()) {
			String sql = SearchQueries.VIEW_EMPLOYEE_REQUESTS_HISTORY;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			com.revature.ers.model.RevatureErsMain.log.debug("Users DAO getEmployeeRequestHistory preparedStatement: "+ preparedStatement + ".");
			preparedStatement.setInt(1, employeeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				HistoryDTO request = new HistoryDTO(resultSet.getInt("reimb_id"), 
					resultSet.getDouble("reimb_amount"), 
					resultSet.getTimestamp("reimb_resolved"), 
					resultSet.getTimestamp("reimb_submitted"), 
					resultSet.getString("reimb_description"), 
					resultSet.getInt("reimb_author"), 
					resultSet.getInt("reimb_resolver"), 
					resultSet.getInt("reimb_status_id"), 
					resultSet.getInt("reimb_type_id"),
					resultSet.getString("user_last_name"),
					resultSet.getString("user_first_name"));
				reimbRequestEmployeeHistory.add(request); 
				System.out.println(request.reimbId);
			}
			// Get resolver names and add to the HistoryDTO objects
			String sql2 = SearchQueries.ADD_RESOLVER_TO_EMPLOYEE_HISTORY;
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			preparedStatement2.setInt(1, employeeId);
			System.out.println("Users DAO ADD_RESOLVER_TO_EMPLOYEE_HISTORY preparedStatement: "+ preparedStatement2 + ".");
			ResultSet resultSet2 = preparedStatement2.executeQuery();
			while(resultSet2.next()) {
				HistoryDTO request = new HistoryDTO(resultSet2.getInt("reimb_id"), 
					resultSet2.getString("user_last_name"),
					resultSet2.getString("user_first_name"));
				updateResolverList.add(request); 
				System.out.println(request.getAuthor_first_name());
			}			

				for(HistoryDTO entry: reimbRequestEmployeeHistory) {
					for(HistoryDTO updateEntry: updateResolverList) {
						if (updateEntry.reimbId == entry.reimbId) {
							entry.process_first_name = updateEntry.process_first_name;
							entry.process_last_name = updateEntry.process_last_name;
						}
					}
				}	

		} catch (PSQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		} catch (SQLException e1) {
			com.revature.ers.model.RevatureErsMain.log.debug(e1.getMessage());
		}		
		return reimbRequestEmployeeHistory;		
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
			com.revature.ers.model.RevatureErsMain.log.debug(e1.getMessage());
		}
		return reimbRequestSingleEmployeeHistory;
	}

	@Override
	public List<PendingDTO> getPendingRequests(User manager) {
		List<PendingDTO> pendingList = new ArrayList<PendingDTO>();
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = SearchQueries.VIEW_PENDING_REQUESTS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, manager.getiD());	
			com.revature.ers.model.RevatureErsMain.log.debug("DAO getPendingRequests preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				PendingDTO request = new PendingDTO(resultSet.getInt("reimb_id"), 
					resultSet.getDouble("reimb_amount"), 
					resultSet.getTimestamp("reimb_submitted"), 
					resultSet.getString("reimb_description"), 
					resultSet.getInt("reimb_author"), 
					resultSet.getInt("reimb_type_id"),
					resultSet.getString("user_first_name"),
					resultSet.getString("user_last_name"));		
				pendingList.add(request); 
			}
		} catch (PSQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		} catch (SQLException e1) {
			com.revature.ers.model.RevatureErsMain.log.debug(e1.getMessage());
		}
		return pendingList;
	}

	@Override
	public int processRequest(ArrayList<ProcessRequestDTO> processList, User manager) {
		int approval, returnValue = 0;
		for (ProcessRequestDTO request:processList) {
			if (request.isApproved) {
				approval = 2; 
			} else approval = 3;
			try (Connection connection = PostgresSqlConnection.getConnection()){
				String sql = SearchQueries.SET_REQUEST_STATUS;
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, approval);
				preparedStatement.setInt(2, manager.getiD());
				preparedStatement.setInt(3, request.getReimbId());	
				preparedStatement.setInt(4, manager.getiD());
				com.revature.ers.model.RevatureErsMain.log.debug("DAO processRequest preparedStatement: "+ preparedStatement + ".");
				int result = preparedStatement.executeUpdate();	
				if (result==1) {
					returnValue++;
				}
			} catch (PSQLException e) {
				com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
			} catch (SQLException e1) {
				com.revature.ers.model.RevatureErsMain.log.debug(e1.getMessage());
			}
		}
			return returnValue;
	} 
	
	public Request getLastRequestEntered() {
		Request lastRequestEntered = null;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = SearchQueries.LAST_REQUEST_ENTERED;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			com.revature.ers.model.RevatureErsMain.log.debug("DAO getLastRequestEntered preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();	
			while(resultSet.next()) {
				lastRequestEntered = new Request(resultSet.getInt("reimb_id"), resultSet.getDouble("reimb_amount"), 
					resultSet.getTimestamp("reimb_resolved"), resultSet.getTimestamp("reimb_submitted"), 
					resultSet.getString("reimb_description"), resultSet.getBlob("reimb_receipt"), 
					resultSet.getInt("reimb_author"), resultSet.getInt("reimb_resolver"), 
					resultSet.getInt("reimb_status_id"), resultSet.getInt("reimb_type_id"));
			}
		} catch (SQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		}
		return lastRequestEntered;
	}

	@Override
	public Request makeRequest(Request request) {
		DaoImpl ownMethodCaller = new DaoImpl();
		Request application = null;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = SearchQueries.MAKE_NEW_REQUEST;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, request.getReimbAmount());	
			preparedStatement.setString(2, request.getReimbDescription());	
			preparedStatement.setInt(3, request.getReimbAuthor());	
			preparedStatement.setInt(4, request.getReimbTypeId());
			com.revature.ers.model.RevatureErsMain.log.debug("DAO makeRequest preparedStatement: "+ preparedStatement + ".");
			int result = preparedStatement.executeUpdate();	
			if (result==1) {
			}
		} catch (PSQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		} catch (SQLException e1) {
			com.revature.ers.model.RevatureErsMain.log.debug(e1.getMessage());
		}
		application = ownMethodCaller.getLastRequestEntered();
		return application;
	}

	@Override
	public User userLogin(Login loginUser) {
		User user = new User();
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = SearchQueries.USER_LOGIN;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginUser.getPassword());
			preparedStatement.setString(2, loginUser.getUserName());
			com.revature.ers.model.RevatureErsMain.log.debug("User Login preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				user.setiD(resultSet.getInt("ers_user_id"));
				user.setFirstName(resultSet.getString("user_first_name"));
				user.setLastName(resultSet.getString("user_last_name"));
				user.setRole(resultSet.getInt("user_role_id"));
			} else com.revature.ers.model.RevatureErsMain.log.debug("Not a valid employee username or password. Please try again.");
		} catch (SQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		}
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
		} catch (PSQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		} catch (SQLException e1) {
			com.revature.ers.model.RevatureErsMain.log.debug(e1.getMessage());
		}
		return request;
	}

	@Override
	public User getUserByUserName(String userName) {
		User user = null;
		@SuppressWarnings("unused")
		Request requestReceiptAdded = null;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = SearchQueries.GET_USER_BY_USERNAME;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);	
			com.revature.ers.model.RevatureErsMain.log.debug("DAO getUserByUserName preparedStatement: "+ preparedStatement + ".");
			ResultSet resultSet = preparedStatement.executeQuery();	
			while(resultSet.next()) {
				user = new User(resultSet.getInt("ers_user_id"), 
					resultSet.getString("user_first_name"), 
					resultSet.getString("user_last_name"), 
					resultSet.getInt("user_role_id"));
			}
		} catch (PSQLException e) {
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		} catch (SQLException e1) {
			com.revature.ers.model.RevatureErsMain.log.debug(e1.getMessage());
		}		
		return user;
	}
		
}
