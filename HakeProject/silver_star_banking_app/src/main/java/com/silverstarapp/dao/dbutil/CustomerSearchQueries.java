package com.silverstarapp.dao.dbutil;

/* This CustomerSearchQueries class file stores all queries accessible
 * by Silver Star App banking customers. Queries are divided into
 * appropriate sub-headings based on general function.
 */

public class CustomerSearchQueries {
	
	// Silver Star Banking App customer DB getters
	
	public static final String CHECK_BALANCE = "SELECT balance FROM silver_star_banking.accounts where account_id = ?; ";
	public static final String VIEW_ALL_ACCOUNTS = "SELECT account_id, balance FROM silver_star_banking.accounts "
			+ "WHERE account_id IN (SELECT account_id FROM silver_star_banking.customeraccountlink " 
			+ "WHERE customer_id = ?);";
	
	// Silver Star Banking App customer DB setters
	
	public static final String WITHDRAW = "UPDATE silver_star_banking.accounts"
			+ "SET balance = balance - ?"
			+ "WHERE account_id = ?;";
	public static final String DEPOSIT = "UPDATE silver_star_banking.accounts"
			+ "SET balance = balance - ?"
			+ "WHERE account_id = ?;";
	
	// Silver Star Banking App customer DB complex actions

	
	public static final String CUSTOMER_LOGIN="SELECT customer_id FROM silver_star_banking.customerlogins WHERE username = ? and pword = ? ";
	public static final String CUSTOMER = "SELECT customer_id, firstName, lastName, contact FROM silver_star_banking.customers WHERE customer_id = ?";
	public static final String TRANSFER_FUNDS = "";
	public static final String APPLY_FOR_ACCOUNT = "";
	

}
