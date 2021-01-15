package com.silverstarapp.main.util;

import com.silverstarapp.main.SilverStarAppMain;

/* This Presentation printer class stores methods which are
 * called by Silver Star App to print output to the user. 
 */

import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Employee;

public class PresentationLevelPrinter {
	
	// systom.out.println is used for the purely decorative logo printers. Other
	// outputs should be send to the appropriate logj4 appender.
	
	public static void  printSilverStarLogo() {
		System.out.println("         /\\ ");
		System.out.println("        /  \\");
		System.out.println("    /--      --\\              Welcome to the Silver Star Banking App!");
		System.out.println("   <            >                         Version 1.0");
		System.out.println("    \\__     __/");
		System.out.println("        \\  /");
		System.out.println("         \\/");
		System.out.println("\n                          ----------------------------------------------");
	}
	
	public static void  printSilverStarExit() {
		System.out.println("         /\\ ");
		System.out.println("        /  \\");
		System.out.println("    /--      --\\          Thanks for using the Silver Star Banking App!");
		System.out.println("   <            >                         Version 1.0");
		System.out.println("    \\__     __/");
		System.out.println("        \\  /");
		System.out.println("         \\/");
		System.out.println("\n                          ----------------------------------------------");
	}
	
	public static void printRootMenu() {
		SilverStarAppMain.log.info("");
		SilverStarAppMain.log.info("");
		SilverStarAppMain.log.info("     Choose Option Below - Please enter digit 1, 2, or 3");
		SilverStarAppMain.log.info("     ===================================================== \n");
		SilverStarAppMain.log.info("     1  -  Customer Login");
		SilverStarAppMain.log.info("     2  -  Employee Login");
		SilverStarAppMain.log.info("     3  -  Create a Silver Star User Account");
		SilverStarAppMain.log.info("     \nEnter 0 to exit the app.");
	}
	
	public static void printLoginMenu () {
		SilverStarAppMain.log.info("");
		SilverStarAppMain.log.info("");
		SilverStarAppMain.log.info("     Please Login:");
		SilverStarAppMain.log.info("     ===================================================== \n");
		SilverStarAppMain.log.info("     To return to the root menu please press Enter with no value.");
	}
	
	public static void printEmployeeMenu (Employee employee) {
		SilverStarAppMain.log.info("");
		SilverStarAppMain.log.info("");
		SilverStarAppMain.log.info("     Welcome " + employee.getFirstName());
		SilverStarAppMain.log.info("     Choose Option Below - Please enter digit 1, 2, or 3");
		SilverStarAppMain.log.info("     ===================================================== \n");
		SilverStarAppMain.log.info("     1  -  View Account Applications");
		SilverStarAppMain.log.info("     2  -  Approve Account Application");
		SilverStarAppMain.log.info("     3  -  View Customer Account");
		SilverStarAppMain.log.info("     \nEnter 0 to return to main menu.");
		
	}
	
	public static void printCustomerMenu (Customer customer) {
		SilverStarAppMain.log.info("");
		SilverStarAppMain.log.info("");
		SilverStarAppMain.log.info("     Welcome " + customer.getFirstName());
		SilverStarAppMain.log.info("     Choose Option Below - Please enter single digit 1-5 ");
		SilverStarAppMain.log.info("     ===================================================== \n");
		SilverStarAppMain.log.info("     1  -  View Account Balance");
		SilverStarAppMain.log.info("     2  -  Deposit Funds");
		SilverStarAppMain.log.info("     3  -  Withdraw Funds");
		SilverStarAppMain.log.info("     4  -  Transfer Funds");
		SilverStarAppMain.log.info("     5  -  Apply for a new Account");
		SilverStarAppMain.log.info("     \nEnter 0 to return to main menu.");
	}	
	
	
}
