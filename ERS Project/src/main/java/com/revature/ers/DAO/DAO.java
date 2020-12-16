package com.revature.ers.DAO;

import java.util.List;

import com.revature.ers.model.Login;
import com.revature.ers.model.Request;
import com.revature.ers.model.User;

public interface DAO {
	
// Manager DAO Interface method declarations
	
	// Revature ERS Manager DAO read objects
	public List<Request> getCompanyRequestHistory(User manager);
	public List<Request> getPendingRequests(User manager);
	
	// Revature ERS Manager DAO insert objects
	public Request processRequest(User manager, Request request, boolean approved);

// Revature ERS Shared DAO operations - manager and employee
	public User	userLogin(Login loginUser);
	public Request makeRequest(Request request);
	public Request updateRequest(User user, Request request);
	public Request insertReceipt(Request request, int receiptImage);
	public List<Request> getSingleEmployeeHistory(User employee);
}
