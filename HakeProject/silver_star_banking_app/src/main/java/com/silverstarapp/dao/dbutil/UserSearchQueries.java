package com.silverstarapp.dao.dbutil;



public class UserSearchQueries {

	/* This UserSearchQueries class file stores all queries accessible
	 * by Silver Star App banking users. Queries are divided into
	 * appropriate sub-headings based on general function.
	 * 
	 */
	
	
	
	public static final String USER_NAME_UNIQUE_CHECK = "SELECT username FROM silver_star_banking.customerlogins WHERE username = ?;";
	public static final String CUSTOMER_TABLE_INSERT = "INSERT INTO silver_star_banking.customers (customer_id, firstname, lastname, contact) "
			+ "VALUES (default,?,?,?);";
	public static final String GET_LATEST_CUSTOMER_ID = "SELECT max(silver_star_banking.customers.customer_id) as customer_id FROM silver_star_banking.customers;";
	public static final String CUSTOMER_LOGIN_TABLE_INSERT = "INSERT INTO silver_star_banking.customerlogins (username, pword, customer_id) "
			+ "VALUES (?,?,?);";
	public static final String ADDRESSES_TABLE_INSERT = "INSERT INTO silver_star_banking.addresses (address_id, houseno, streetname, secondline, city, state, zip) "
			+ "VALUES (default,?,?,?,?,?,?); ";
	public static final String GET_LATEST_ADDRESS_ID = "SELECT max(silver_star_banking.addresses.address_id) as address_id FROM silver_star_banking.addresses;";
	public static final String CUST_ADDRESS_LINK_INSERT = "INSERT INTO silver_star_banking.customeraddresslink (customer_id, address_id) "
			+ "VALUES (?,?); ";
	
	
}
