package com.revature.ers.service;

import java.sql.Blob;

import com.revature.ers.model.Login;
import com.revature.ers.model.Request;
import com.revature.ers.model.User;

public interface BusinessLogicChecks {
	
	public boolean isValidUserName(Login loginUser);
	public boolean isValidPassword(Login loginUser);
	public boolean isValidUser(User user);
	public boolean isManager(User user);
	public boolean isNotOwnRequest(User manager, Request request);
	public boolean isValidRequest(Request request);
	public boolean isValidUpdate(Request request);
	public boolean isValidImageFile(Blob receiptImage);

}
