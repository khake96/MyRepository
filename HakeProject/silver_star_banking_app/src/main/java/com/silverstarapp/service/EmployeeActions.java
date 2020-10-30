package com.silverstarapp.service;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.Application;
import com.silverstarapp.model.Customer;

public interface EmployeeActions {
	
	public boolean applicationApproved(Application application) throws BusinessException;
	public Account viewCustomerAccount(Customer customer) throws BusinessException;
	public log viewLogs() throws BusinessException;
	public boolean login(String username, char[] password) throws BusinessException;
	

}
