package com.silverstarapp.dao.dbutil;

public class EmployeeSearchQueries {
	
	/* This EmployeeSearchQueries class file stores all queries accessible
	 * by Silver Star App banking employees. Queries are divided into
	 * appropriate sub-headings based on general function.
	 * 
	 * Possible improvements: use a stored procedure for the application approval process
	 * Use a date stamp to get the newest created account number for application approval process
	 */
		
		// Silver Star Banking App employee DB getters

		public static final String VIEW_ALL_ACCOUNT_APPLICATIONS = "select account_appl_id, customer_id, startingbalance, incomegross, expenses "
				+ "from silver_star_banking.accountapplications "
				+ "order by startingbalance desc;";	
		public static final String VIEW_CUSTOMER_ACCOUNT = "SELECT balance, approveddate, lastmodifieddate FROM silver_star_banking.accounts where account_id = ?; ";
		public static final String EMPLOYEE_LOGIN="SELECT employee_id FROM silver_star_banking.employeelogins WHERE username = ? and pword = ?; ";
		public static final String EMPLOYEE = "SELECT employee_id, firstName, lastName, contact FROM silver_star_banking.employees WHERE employee_id = ?;";
		public static final String VIEW_ACCOUNT_HISTORY = "SELECT transaction_value, balance, transaction_time from silver_star_banking.accounthistory " +
				"WHERE account_id = ?;";
			
		// Silver Star Banking App employee DB other actions
		
		// Insert approved application info into new customer accounts
		// Get necessary data from the application
		public static final String GET_APPLICATION_DATA = "SELECT startingBalance, customer_id FROM silver_star_banking.accountapplications "
				+ "where account_appl_id = ?;";
		// Insert data into the accounts table
		public static final String CREATE_NEW_ACCOUNT = "INSERT INTO silver_star_banking.accounts(account_id, balance, approveddate, lastmodifieddate) "
				+ "VALUES (default, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);";
		// Get the new account number
		public static final String GET_LATEST_ACCOUNT_CREATED = "SELECT max(silver_star_banking.accounts.account_id) as account_id FROM silver_star_banking.accounts;";
		// Insert data into customeraccount association table
		public static final String CREATE_NEW_USER_ACCOUNT_ASSOCIATION = "INSERT INTO silver_star_banking.customeraccountlink (customer_id, account_id) "
				+ "VALUES (?, ?);";
		// Insert data into the accounthistory table for the new account
		public static final String CREATE_NEW_ACCOUNT_HISORY_ASSOCIATION = "INSERT INTO silver_star_banking.accounthistory (transaction_id,account_id,transaction_value, "
				+ "transaction_time, balance) "
				+ "VALUES (default, ?, ?, now(), ?);";
		// Delete approved / rejected application from application table
		public static final String DELETE_ACCNT_APPLICATION = "DELETE FROM silver_star_banking.accountapplications "
				+ "WHERE account_appl_id = ?;";
}
