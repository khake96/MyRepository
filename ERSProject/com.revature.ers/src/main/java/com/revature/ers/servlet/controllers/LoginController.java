package com.revature.ers.servlet.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.ers.model.Login;
import com.revature.ers.model.User;
import com.revature.ers.service.UserActions;
import com.revature.ers.service.impl.UserActionsImpl;

public class LoginController {
	
	private UserActions userActions = new UserActionsImpl();
	private ObjectMapper mapper = new ObjectMapper();
	private User user = new User();

	public void login(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
		if(req.getMethod().equals("POST")) {
			BufferedReader reader = req.getReader();
			StringBuilder stringBuilder = new StringBuilder();
			String line = reader.readLine();
			
			while(line!=null) {
				stringBuilder.append(line);
				line = reader.readLine();
			}
			
			String body = new String(stringBuilder);
			System.out.println(body);
			Login login = mapper.readValue(body, Login.class);
			System.out.println(login.toString());
			user = userActions.userLogin(login);
			String json = mapper.writeValueAsString(user);
			System.out.println(json);
			res.getWriter().print(json);
			
			if(user.getiD() != 0) {
				HttpSession ses = req.getSession();
				ses.setAttribute("user", user);
				ses.setAttribute("loggedIn", true);			
				res.setStatus(200);						
			} 
			else {
				HttpSession ses = req.getSession(false);
				if(ses != null) {
					ses.invalidate();
				}
				res.setStatus(401);
				res.getWriter().print("Login failed");	
			}
		}	

	}
}
