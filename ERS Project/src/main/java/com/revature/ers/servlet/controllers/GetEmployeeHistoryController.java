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

public class GetEmployeeHistoryController {
	
	private UserActions userActions = new UserActionsImpl();
	private ObjectMapper mapper = new ObjectMapper();
	User user = null;

	public void getRequestHistory(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		HttpSession ses = req.getSession(false);
		System.out.println("From controller: "+ses.getAttribute("user"));
		user = (User) ses.getAttribute("user");
		
		if((boolean) ses.getAttribute("loggedIn")) {
			if(req.getMethod().equals("POST")) {
				
				// One Employee was requested rather than all employees
				BufferedReader reader = req.getReader();
				StringBuilder stringBuilder = new StringBuilder();
				String line = reader.readLine();
				
				while(line!=null) {
					stringBuilder.append(line);
					line = reader.readLine();
				}
				
				String body = new String(stringBuilder);
				System.out.println(body);
				Integer employeeId = mapper.readValue(body, Integer.class);
				System.err.println(employeeId);
	
				List<HistoryDTO> requestList = new ArrayList<HistoryDTO>();
				requestList = userActions.getEmployeeRequestHistory(user, employeeId);
				System.out.println("requestList: "+ requestList.toString());
				String json = mapper.writeValueAsString(requestList);
				System.out.println(json);
				res.getWriter().print(json);
				
				if(requestList.size()!=0) {
					res.setStatus(200);
				} else {
					res.setStatus(401);
					res.getWriter().print("Unable to proess request. Please try again.");
				}
			}
		} else {
			res.setStatus(401);
			res.getWriter().print("Invalid credentials. Please login again.");
		}
	} // end method
}

