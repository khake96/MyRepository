package com.silverstarapp.dao.dbutil;

public class EmployeeSearchQueries {

//	public boolean applicationApproved(AccountApplication application) throws BusinessException;
//	public Account viewCustomerAccount(Customer customer) throws BusinessException;
//	public log viewLogs() throws BusinessException;
//	public Employee login(Login login) throws BusinessException;
	
	/* This EmployeeSearchQueries class file stores all queries accessible
	 * by Silver Star App banking employees. Queries are divided into
	 * appropriate sub-headings based on general function.
	 */
		
		// Silver Star Banking App customer DB getters
		
		public static final String CHECK_ACCOUNT = "";
		public static final String VIEW_LOGS = "";
		
		// Silver Star Banking App customer DB setters
		
		public static final String APPROVE_ACCNT_APPLICATION = "";

		
		// Silver Star Banking App customer DB complex actions
		
		public static final String TRANSFER_FUNDS = "";
		public static final String APPLY_FOR_ACCOUNT = "";
		public static final String EMPLOYEE_LOGIN="SELECT employee_id FROM silver_star_banking.employeelogins WHERE username = ? and pword = ? ";
		public static final String EMPLOYEE = "SELECT employee_id, firstName, lastName, contact FROM silver_star_banking.employees WHERE employee_id = ?";
	
	
}
