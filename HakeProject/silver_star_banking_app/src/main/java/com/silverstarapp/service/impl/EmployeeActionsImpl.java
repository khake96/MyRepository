package com.silverstarapp.service.impl;

import com.silverstarapp.dao.employee.EmployeeDAO;
import com.silverstarapp.dao.impl.employee.EmpoloyeeDaoImpl;
import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Login;
import com.silverstarapp.service.EmployeeActions;

public class EmployeeActionsImpl implements EmployeeActions {
	
	private EmployeeDAO employeeDAO = new EmpoloyeeDaoImpl();

	@Override
	public boolean applicationApproved(AccountApplication application) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account viewCustomerAccount(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int employeeLogin(Login login) throws BusinessException {
		int employeeID = 0;
		employeeID = employeeDAO.employeeLogin(login);
		if (employeeID < 0) {
			System.out.println("Not a valid login. From EmployeeActionImpl");
		}
		return employeeID;
	}

}
