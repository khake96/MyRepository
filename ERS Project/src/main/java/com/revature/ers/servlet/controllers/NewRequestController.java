package com.revature.ers.servlet.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.model.Request;
import com.revature.ers.model.RequestDTO;
import com.revature.ers.service.UserActions;
import com.revature.ers.service.impl.UserActionsImpl;

public class NewRequestController {
	
	private UserActions userActions = new UserActionsImpl();
	private ObjectMapper mapper = new ObjectMapper();
	private RequestDTO requestDTO = null;

	public void request(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		
		HttpSession ses = req.getSession(false);
		System.out.println(ses.getAttribute("user"));
		
		if((boolean) ses.getAttribute("loggedIn")) {
			
			if(req.getMethod().equals("POST")) {
				BufferedReader reader = req.getReader();
				StringBuilder stringBuilder = new StringBuilder();
				String line = reader.readLine();
//				System.out.println(line);
				
				while(line!=null) {
					stringBuilder.append(line);
					line = reader.readLine();
				}	
				
				String body = new String(stringBuilder);
				System.out.println(body);
				requestDTO = mapper.readValue(body, RequestDTO.class);
				Request returnRequest = userActions.makeRequest(requestDTO);
				String json = mapper.writeValueAsString(returnRequest);
				System.out.println(json);
				res.getWriter().print(json);
				
				if(returnRequest.getReimbId() != 0) {		
					res.setStatus(200);					
				} else {
					res.setStatus(401);
					res.getWriter().print("Unable to process request.");	
				}	
			} // end first if statement checking login valid	
		} else {
			res.setStatus(401);
			res.getWriter().print("Invalid credentials.");				
		}
	} // end method
} // end class
