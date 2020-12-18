package com.revature.ers.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.ers.model.HistoryDTO;
import com.revature.ers.model.Login;
import com.revature.ers.model.PendingDTO;
import com.revature.ers.model.ProcessRequestDTO;
import com.revature.ers.model.Request;
import com.revature.ers.model.RequestDTO;
import com.revature.ers.model.User;

public interface UserActions {
	
	// Manager DAO Interface method declarations
	
		// Revature ERS Manager DAO read objects
		public List<HistoryDTO> getCompanyRequestHistory(User manager);
		public List<HistoryDTO> getCompanyRequestHistory(User user, Integer employeeId);
		public List<PendingDTO> getPendingRequests(User manager);
		
		// Revature ERS Manager DAO insert objects
		public int processRequest(ArrayList<ProcessRequestDTO> processList, User manager);

	// Revature ERS Shared DAO operations - manager and employee
		public User	userLogin(Login loginUser);
		public Request makeRequest(RequestDTO request);
		public Request updateRequest(User user, Request request);
		public Request insertReceipt(Request request, int receiptImage);
		public List<Request> getSingleEmployeeHistory(User employee);
		

}
		


