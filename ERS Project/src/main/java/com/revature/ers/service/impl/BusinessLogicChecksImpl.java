package com.revature.ers.service.impl;

import java.sql.Blob;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.ers.model.Login;
import com.revature.ers.model.Request;
import com.revature.ers.model.User;
import com.revature.ers.service.BusinessLogicChecks;

public class BusinessLogicChecksImpl implements BusinessLogicChecks {

//	Business requirements: userName is alpha-numeric, min length 8 chars, max length 50 chars

	
	@Override
	public boolean isValidUserName(Login loginUser){
		boolean userNameIsValid = false;
		String userName = null;
		try {
			userName = loginUser.getUserName();			
		} catch (NullPointerException e) {
			com.revature.ers.model.RevatureErsMain.log.debug("Business Logic check error with login: "+ loginUser.toString() + ".");
		} catch (Exception e) {
			com.revature.ers.model.RevatureErsMain.log.debug("Business Logic check error with login: "+ loginUser.toString() + ".");
		}

		String regex = "[\\w]{8,50}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userName);
		boolean matchFound = matcher.matches();
		if (matchFound && userName.length()>7 && userName.length()<50) {
			userNameIsValid = true;	
		}
		return userNameIsValid;
	}
	
	// Business requirements: password is alpha-numeric with symbols allowed, min length 7 chars, max length 50 chars

	@Override
	public boolean isValidPassword(Login loginUser) {
		boolean passwordIsValid = false;
		String userPassword = loginUser.getPassword();
		String regex = "[\\w\\W]{7,50}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userPassword);
		boolean matchFound = matcher.matches();
		if(matchFound) {
			passwordIsValid = true;
		}			
		return passwordIsValid;
	}
	
	// Business requirements: the user returned from the DB has a userRole_id = 2 (is a manager)

	@Override
	public boolean isManager(User user) {
		boolean isManager = false;
		try {
			if(user.getRole() == 2) {
				isManager =  true;
			} else isManager = false;
		} catch (NullPointerException e) {
			com.revature.ers.model.RevatureErsMain.log.debug("Business Logic check error with user: "+ user.toString() + ".");
		}
		return isManager;
	}
	
	// Business requirements: 
		// Reimbursement amount is greater than 0 and less than $10,000.00
		// Reimbursement description is 10-500 characters long.
		// Reimbursement type: is int between 1 and 4
		// Reimbursement author: is int in the range of 101-9999 (valid user IDs)

	@Override
	public boolean isValidRequest(Request request) {
		boolean isValidRequest = false;
		if(request.getReimbAmount()>0 && request.getReimbAmount()<10000) {
			if(request.getReimbDescription().length()>9 && request.getReimbDescription().length()<501) {
				if(request.getReimbTypeId() > 0 && request.getReimbTypeId() <= 4) {
					if(request.getReimbAuthor()>100 && request.getReimbAuthor()<10000) {
						isValidRequest = true;
					}
				}
			}
		}		
		return isValidRequest;
	}

	// Business requirements: This can use the same check-points as isValidRequest.
	
	@Override
	public boolean isValidUpdate(Request request) {
		boolean isValidUpdate = isValidRequest(request);
		return isValidUpdate;
	}
	
	// Business requirements: TBD

	@Override
	public boolean isValidImageFile(Blob receiptImage) {
		return false;
	}

	// Business requirements: 
	//		99 < User ID < 10001
	// 		0 < First Name length < 50 letters and blank space only
	//		0 < Last Name length < 50 letters and blank space only
	// 		0 < user role id < 3
	@Override
	public boolean isValidUser(User user) {
		boolean userIsValid = false;
		int userId = user.getiD();
		String userFirstName = user.getFirstName();
		String userLastName = user.getLastName();
		int userRole = user.getRole();
		String regex = "[\\s\\w]{1,50}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userFirstName);
		boolean matchFound = matcher.matches();
		if(matchFound) {
			matcher = pattern.matcher(userLastName);
			matchFound = matcher.matches();
			if(matchFound) {
				if(userId < 10000 && userId > 99) {
					if(userRole > 0 && userRole < 3) {
						userIsValid = true;
					}
				}
			}
		}			
		return userIsValid;
	}

	@Override
	public boolean isNotOwnRequest(User manager, Request request) {
		boolean isNotOwnRequest = false;
		try {
			if(manager.getiD()!=request.getReimbAuthor()) {
				isNotOwnRequest = true;
			}
		} catch (NullPointerException e) {
			com.revature.ers.model.RevatureErsMain.log.debug("Business Logic check error with login: "+ request.toString() + ".");
		} catch (Exception e) {
			com.revature.ers.model.RevatureErsMain.log.debug("Business Logic check error with login: "+ request.toString() + ".");
		}
		return isNotOwnRequest;
	}

}
