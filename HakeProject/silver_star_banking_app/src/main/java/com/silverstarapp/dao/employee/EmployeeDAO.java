package com.silverstarapp.dao.employee;

import java.util.List;

/* This Silver Star Banking App employee DAO Interface specifies the 
 * requirements for the Database Acquisition Objects required by the
 * employees of the Silver Star Bank.
 */

public interface EmployeeDAO {
	
	// Silver Star Banking Employee DAO reading objects
	
	public List<Account> viewAllAccounts() throws BusinessException;
	public Account viewCustomerAccount(Customer customer) throws BusinessException;
    public Application getNextApplication() throws BusinessException;
    public List<Application> getAllApplications() throws BusinessException;
    public log viewCustomerLog(Customer customer) throws BusinessException;
    
}
