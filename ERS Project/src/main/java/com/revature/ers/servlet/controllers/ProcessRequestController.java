package com.revature.ers.servlet.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.model.Request;
import com.revature.ers.model.User;
import com.revature.ers.service.UserActions;
import com.revature.ers.service.impl.UserActionsImpl;

public class ProcessRequestController {
	
	private UserActions userActions = new UserActionsImpl();
	private ObjectMapper mapper = new ObjectMapper();
	User user = null;

	public void process(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		
		HttpSession ses = req.getSession(false);
		System.out.println("From controller: "+ses.getAttribute("user"));
		user = (User) ses.getAttribute("user");	
		
		if((boolean) ses.getAttribute("loggedIn")) {
			if(req.getMethod().equals("GET")) {
				List<Request> requestList = new ArrayList<Request>();
				requestList = userActions.getPendingRequests(user);
				String json = mapper.writeValueAsString(requestList);
				System.out.println(json);
				res.getWriter().print(json);
			}
		}		
	}
}
