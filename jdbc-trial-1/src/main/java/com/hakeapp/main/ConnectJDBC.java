package com.hakeapp.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectJDBC {

	public static void main(String[] args) {
		
		// create connection outside try-catch so all blocks can access
		Connection connection=null;
		try {
			// Step 1 - Load the Driver
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver load success.");
			
			// Step 2 - Open Connection
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String username = "postgres";
			String password = "skyskin1";
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("Open connection success.");
			
			// Step 3 - Create a statement
			Statement statement = connection.createStatement();
			String sql="SELECT employee_id, employee_name, employee_phone FROM \"EMS\".employee";
			System.out.println("Statement create success.");
			
			// Step 4 - Execute Query
			ResultSet rs = statement.executeQuery(sql);
			System.out.println("Query execute success.");
			
			// Step 5 - Process Results
			while(rs.next()) {
				System.out.print("Employee ID: "+rs.getInt("employee_id")+" ");
				System.out.print("Employee name: "+rs.getString("employee_name")+" ");
				System.out.println("Employee phone: "+rs.getString("employee_phone")+" ");
				
			}
			System.out.println("Result process success.");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			
			try {
				// Step 6 - Close connection 
				connection.close();
				System.out.println("Connection close success.");
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		

	}

}
