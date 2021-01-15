package com.silverstarapp.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.silverstarapp.dao.UserDAO;
import com.silverstarapp.dao.impl.UserDaoImpl;
import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.main.SilverStarAppMain;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.User;
import com.silverstarapp.service.UserActions;

/* This Service level UserAction class file contains the implementation for a Silver
 * Star Banking user Interface file. 
 */

public class UserActionsImpl implements UserActions {

	@Override
	public Customer newRegistrationApplication(User user) throws BusinessException {
		UserActionsImpl ownMethodCaller = new UserActionsImpl();
		UserDAO userActions = new UserDaoImpl();
		Customer customer = null;
		// Perform business level logic on user input to ensure a valid Silver Star Banking customer may be created
			if(userActions.isUniqueUserName(user.getUserName())) {
				boolean validInput = userActions.isUniqueUserName(user.getUserName());;
				if(validInput  && ownMethodCaller.registrationApplicationIsBusinessValid(user)) {
					customer = userActions.createNewCustomer(user);
				} else SilverStarAppMain.log.warn("User name is not unique. Please try again.");
			}
		return customer;
	}
	
	public boolean registrationApplicationIsBusinessValid (User user) {
		boolean result = false;
		String pattern = "[1-9][0-9]{9}";
		Pattern regex = Pattern.compile(pattern);
		if (user.getAddress().getState().equals("TN")) {
			Matcher matcher = regex.matcher(String.valueOf(user.getContact())); 
			if (matcher.find())  { // Check valid input format for contact info
				if (!user.getFirstName().isEmpty() && !user.getLastName().isEmpty()) {	
							result = true;				
				} else {
					SilverStarAppMain.log.warn("Please enter first and last names.");
				}
			} else {
				SilverStarAppMain.log.warn("Please enter contact in format 1231234567");
			}
		} else {
			SilverStarAppMain.log.warn("Silver Star Banking currently availble only in the state of Tennessee. Thanks for your interest.");
		}
		return result;
	}
	
	public UserActionsImpl() {
		super();
	}

}
