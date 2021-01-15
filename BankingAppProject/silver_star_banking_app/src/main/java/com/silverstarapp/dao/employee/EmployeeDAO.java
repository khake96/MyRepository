package com.silverstarapp.dao.employee;

import java.util.List;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Employee;
import com.silverstarapp.model.Login;

/* This Silver Star Banking App employee DAO Interface specifies the 
 * requirements for the Database Acquisition Objects required by the
 * employees of the Silver Star Bank.
 */

public interface EmployeeDAO {
	
	// Silver Star Banking Employee DAO reading objects
	
	public List<Account> viewAllAccounts() throws BusinessException;
	public Account viewCustomerAccount(Customer customer) throws BusinessException;
    public AccountApplication getNextApplication() throws BusinessException;
    public List<AccountApplication> getAllApplications() throws BusinessException;
    public int employeeLogin(Login login) throws BusinessException;
    public Employee setEmployee(int employeeID) throws BusinessException;
//    public log viewCustomerLog(Customer customer) throws BusinessException;
    
}
