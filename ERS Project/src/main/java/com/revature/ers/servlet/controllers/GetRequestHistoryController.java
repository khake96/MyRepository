package com.revature.ers.servlet.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.model.HistoryDTO;
import com.revature.ers.model.User;
import com.revature.ers.service.UserActions;
import com.revature.ers.service.impl.UserActionsImpl;

public class GetRequestHistoryController {
	
	private UserActions userActions = new UserActionsImpl();
	private ObjectMapper mapper = new ObjectMapper();
	User user = null;
	User sentUser = null;
	Integer employeeId = 0;

	public void getRequestHistory(HttpServletRequest req, HttpServletResponse response) 
				throws IOException {

		HttpSession ses = req.getSession(false);
		com.revature.ers.model.RevatureErsMain.log.debug("From controller: "+ses.getAttribute("user"));
		user = (User) ses.getAttribute("user");
		
		if((boolean) ses.getAttribute("loggedIn")) {
			if(req.getMethod().equals("GET")) {
				try {
					List<HistoryDTO> requestList = new ArrayList<HistoryDTO>();
					requestList = userActions.getCompanyRequestHistory(user);
					response.setStatus(200);
					//System.out.println("response: "+response);
					String json = mapper.writeValueAsString(requestList);
					response.getWriter().print(json);
					//System.out.println(json);
				}
				catch(IndexOutOfBoundsException e) {
					response.setStatus(401);
					response.getWriter().print("Could not validate user, please try again.");
				}
				catch(IllegalStateException e) {
					response.setStatus(401);
					response.getWriter().print("System error, please try again.");
					com.revature.ers.model.RevatureErsMain.log.debug(e.getMessage());
				}

			} else {
				// One Employee was requested rather than all employees
				String regex = "[\\s\\S]{8,50}";
				BufferedReader reader = req.getReader();
				StringBuilder stringBuilder = new StringBuilder();
				String line = reader.readLine();
				response.setStatus(200);
				while(line!=null) {
					stringBuilder.append(line);
					line = reader.readLine();
				}			
				String body = new String(stringBuilder);
				if(body.matches(regex)) {
					String userName = mapper.readValue(body, String.class);
					sentUser = userActions.getEmployeeByUserName(userName);
					employeeId = Integer.valueOf(sentUser.getiD());
				} else {
					employeeId = mapper.readValue(body, Integer.class);
				}
				List<HistoryDTO> requestList = new ArrayList<HistoryDTO>();
				requestList = userActions.getEmployeeRequestHistory(user, employeeId);
				String json = mapper.writeValueAsString(requestList);
				response.getWriter().print(json);
				
				if(requestList.size()!=0) {
					response.setStatus(200);
				} else {
					response.setStatus(401);
					response.getWriter().print("Unable to proess request. Please try again.");
				}
			}
		} else {
			response.setStatus(401);
			response.getWriter().print("Invalid credentials. Please login again.");
		}
	} // end method
} // end class
