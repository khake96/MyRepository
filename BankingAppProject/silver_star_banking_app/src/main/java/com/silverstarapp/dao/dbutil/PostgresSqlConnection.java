package com.silverstarapp.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* This Silver Star Banking Aoo SQL Connection class provides 
 * static access to the DB connection to the DB to be reused
 * throughout the Silver Star Banking App.
 */

public class PostgresSqlConnection {
	
	private static Connection connection;
	
	private PostgresSqlConnection() {
		
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DbUtilProperties.DRIVER);
		String url = DbUtilProperties.URL;
		String username = System.getenv("postgresUserName");
		String password = System.getenv("postgresPassword");
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

}
