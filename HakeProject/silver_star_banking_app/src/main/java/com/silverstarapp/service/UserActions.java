package com.silverstarapp.service;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.RegistrationApplication;
import com.silverstarapp.model.User;

public interface UserActions {
	
	public RegistrationApplication newRegistrationApplication(User user) throws BusinessException;
	public RegistrationApplication viewRegistrationApplicationStatus(RegistrationApplication application) throws BusinessException;
	public boolean login(String username, char[] password) throws BusinessException;
	// no need to login (auto-creates a customer account)
}
