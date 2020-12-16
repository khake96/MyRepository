package com.revature.ers.servlet.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController {
	
	public void logout(HttpServletRequest req, HttpServletResponse res) 
			throws IOException {
	
		if(req.getMethod().equals("POST")) {	
			HttpSession ses = req.getSession();
			ses.setAttribute("loggedIn", false);
			ses.invalidate();
			res.setStatus(200);	
		}

	}
}
