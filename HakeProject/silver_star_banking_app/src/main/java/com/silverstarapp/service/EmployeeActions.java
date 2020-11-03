package com.silverstarapp.service;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Employee;
import com.silverstarapp.model.Login;

public interface EmployeeActions {
	
	public boolean applicationApproved(AccountApplication application) throws BusinessException;
	public Account viewCustomerAccount(Customer customer) throws BusinessException;
//	public log viewLogs() throws BusinessException;
	public int employeeLogin(Login login) throws BusinessException;
	

}
