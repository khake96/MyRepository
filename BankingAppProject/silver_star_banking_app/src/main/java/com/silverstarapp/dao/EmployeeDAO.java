package com.silverstarapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.AccountHistory;
import com.silverstarapp.model.Employee;
import com.silverstarapp.model.Login;

/* This Silver Star Banking App employee DAO Interface specifies the 
 * requirements for the Database Acquisition Objects required by the
 * employees of the Silver Star Bank.
 */

public interface EmployeeDAO {
	
	// Silver Star Banking Employee DAO reading objects
	
	public AccountHistory viewCustomerAccount(int accountNumber) throws BusinessException, SQLException;
    public List<AccountApplication> getAllApplications() throws BusinessException, SQLException;
    public boolean approveApplication(int applicationID, boolean approved) throws BusinessException, SQLException;
    public Employee employeeLogin(Login login) throws BusinessException, SQLException;
    public Employee setEmployee(int employeeID) throws BusinessException, SQLException;
}
