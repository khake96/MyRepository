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
		} 	else com.revature.ers.model.RevatureErsMain.log.debug("Failed business logic company history request at manager: "+manager);

		return requestList;
	}
	
	@Override
	public List<HistoryDTO> getEmployeeRequestHistory(User user, Integer employeeId) {
		@SuppressWarnings({ })
		List<HistoryDTO> requestList = new ArrayList<HistoryDTO>();
		if(logicChecks.isManager(user) ) {
			requestList = actions.getEmployeeRequestHistory(user, employeeId);
		} else com.revature.ers.model.RevatureErsMain.log.debug("Failed business logic get employee history at user: "+user +"or at employeeId: "+employeeId);
		return requestList;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<PendingDTO> getPendingRequests(User manager) {
		@SuppressWarnings("unchecked")
		List<PendingDTO> requestList = new ArrayList();
		if(logicChecks.isManager(manager)) {
			requestList = actions.getPendingRequests(manager);
		} else com.revature.ers.model.RevatureErsMain.log.debug("Failed business logic getPendingRequests at manager: "+manager);
		return requestList;
	}
		
	// Revature ERS Manager DAO insert objects
	@Override
	public int processRequest(ArrayList<ProcessRequestDTO> processList, User manager) {
		int changeCount = 0;
		if(logicChecks.isManager(manager)) {
			changeCount = actions.processRequest(processList, manager);
		} else {
			com.revature.ers.model.RevatureErsMain.log.debug("Failed business logic processRequest: "+manager +" or process Request list.");
		}
		return changeCount;
	}

// Revature ERS Shared DAO operations - manager and employee
	@Override
	public User	userLogin(Login loginUser) {
		User user = null;
		String password = loginUser.getPassword();
		String userName = loginUser.getUserName();
		if(logicChecks.isValidUserName(loginUser) 
				&&
				logicChecks.isValidPassword(loginUser)
				) {
				String encrypted = Utils.encrypt(password, userName);
				loginUser.setPassword(encrypted);
			user = actions.userLogin(loginUser);
		} else {
			com.revature.ers.model.RevatureErsMain.log.debug("Failed business logic userLogin at login: "+loginUser);
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
			com.revature.ers.model.RevatureErsMain.log.debug("Failed business logic makeRequest with request: "+request);
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
	
		try {
			if(logicChecks.isValidUser(employee)) {
				statusList = actions.getSingleEmployeeHistory(employee);
			} else {
				com.revature.ers.model.RevatureErsMain.log.debug("Failed business logic singleEmployeeHistory at employee: "+employee);
			}
		} catch (NullPointerException e){
			com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
		}
		return statusList;
	}
	

	@Override
	public User getEmployeeByUserName(String userName) {
		User user = null;
		Login login = new Login(userName,"password");
		if(logicChecks.isValidUserName(login)) {
			user = actions.getUserByUserName(userName);
		} else com.revature.ers.model.RevatureErsMain.log.debug("Failed business logic getEmployeeByUSerName at userName: "+userName);
		return user;
	}

}


