package com.revature.ers.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.revature.ers.DAO.DAO;
import com.revature.ers.DAO.impl.DaoImpl;
import com.revature.ers.model.HistoryDTO;
import com.revature.ers.model.Login;
import com.revature.ers.model.PendingDTO;
import com.revature.ers.model.ProcessRequestDTO;
import com.revature.ers.model.Request;
import com.revature.ers.model.RequestDTO;
import com.revature.ers.model.User;
import com.revature.ers.service.BusinessLogicChecks;
import com.revature.ers.service.UserActions;

public class UserActionsImpl implements UserActions {
	
	BusinessLogicChecks logicChecks = new BusinessLogicChecksImpl();
	DAO actions = new DaoImpl();
	
// Manager DAO Interface method declarations
	
	// Revature ERS Manager DAO read objects
	@Override
	public List<HistoryDTO> getCompanyRequestHistory(User manager) {
		@SuppressWarnings({ })
		List<HistoryDTO> requestList = new ArrayList<HistoryDTO>();
		if(logicChecks.isManager(manager) ) {
			requestList = actions.getCompanyRequestHistory(manager);
		}
		return requestList;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<PendingDTO> getPendingRequests(User manager) {
		@SuppressWarnings("unchecked")
		List<PendingDTO> requestList = new ArrayList();
		if(logicChecks.isManager(manager)) {
			requestList = actions.getPendingRequests(manager);
		}
		return requestList;
	}
		
	// Revature ERS Manager DAO insert objects
	@Override
	public int processRequest(ArrayList<ProcessRequestDTO> processList, User manager) {
		int changeCount = 0;
		if(logicChecks.isManager(manager)) {
			changeCount = actions.processRequest(processList, manager);
		} else {
			System.out.println("User is not a manager." + manager.toString());
		}
		return changeCount;
	}

// Revature ERS Shared DAO operations - manager and employee
	@Override
	public User	userLogin(Login loginUser) {
		User user = null;
		if(logicChecks.isValidUserName(loginUser) 
//				&&
//				logicChecks.isValidPassword(loginUser)
				) {
			user = actions.userLogin(loginUser);
		} else {
			System.out.println("Invalid login business criteria." + loginUser.toString());
		}
	return user;
	}
	
	@Override
	public Request makeRequest(RequestDTO request) {
		Request requestObject = new Request(request);
		Request returnRequest = null;
		if(logicChecks.isValidRequest(requestObject)) {
			returnRequest = actions.makeRequest(requestObject);
		} else {
			System.out.println("Invalid request business criteria." + request.toString());
		}
		return returnRequest;
	}
	
	@Override
	public Request updateRequest(User user, Request request) {
		return null;
	}
	@Override
	public Request insertReceipt(Request request, int receiptImage) {
		return null;
	}
	@Override
	public List<Request> getSingleEmployeeHistory(User employee) {
		List<Request> statusList = new ArrayList<Request>();
	
		if(logicChecks.isValidUser(employee)) {
			statusList = actions.getSingleEmployeeHistory(employee);
		} else {
			System.out.println("Invalid user. Cannot complete request.");
			System.out.println("User: " + employee.toString());
		}
		return statusList;
	}

}


