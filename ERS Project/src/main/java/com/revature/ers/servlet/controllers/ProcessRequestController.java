package com.revature.ers.servlet.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.model.ProcessRequestDTO;
import com.revature.ers.model.User;
import com.revature.ers.service.UserActions;
import com.revature.ers.service.impl.UserActionsImpl;

public class ProcessRequestController {
	
	private UserActions userActions = new UserActionsImpl();
	private ObjectMapper mapper = new ObjectMapper();
	User user = null;
	private int returnCount = 0;

	public void process(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		
		HttpSession ses = req.getSession(false);
		
		if((boolean) ses.getAttribute("loggedIn")) {
			
			if(req.getMethod().equals("POST")) {
				BufferedReader reader = req.getReader();
				StringBuilder stringBuilder = new StringBuilder();
				String line = reader.readLine();
				
				while(line!=null) {
					stringBuilder.append(line);
					line = reader.readLine();
				}
				
				String body = new String(stringBuilder);
				user = (User) ses.getAttribute("user");
				//System.out.println(body);
				ArrayList<ProcessRequestDTO> processArray = mapper.readValue(body, new TypeReference<ArrayList<ProcessRequestDTO>>(){});
				returnCount = userActions.processRequest(processArray, user);
				res.getWriter().print(returnCount);
				
				if(returnCount!=0) {
					res.setStatus(200);
				} else {
					res.setStatus(401);
					res.getWriter().print("Unable to process request.");
				}
			} // End of verb check 
		}else {
			res.setStatus(401);
			res.getWriter().print("Invalid credentials.");
		} 		
	} // End method
} // End class
