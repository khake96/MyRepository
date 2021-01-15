package com.silverstarapp.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.AccountHistory;
import com.silverstarapp.model.Employee;
import com.silverstarapp.model.Login;

/* This Service level EmployeeAction interface file contains the requirements for a Silver
 * Star Banking employee useage. The implementation is found in the corresponding Impl class.
 */

public interface EmployeeActions {
	
	public boolean approveApplication(int applicationID, boolean approved) throws BusinessException, SQLException;
	public AccountHistory viewCustomerAccount(int accountNumber) throws BusinessException, SQLException;
	public List<AccountApplication> getAllApplications() throws BusinessException, SQLException;
	public Employee employeeLogin(Login login) throws BusinessException, SQLException;
	public String viewLogs(File file) throws IOException;
	public String formatApplicationList (List<AccountApplication> applicationList) throws BusinessException;

}
