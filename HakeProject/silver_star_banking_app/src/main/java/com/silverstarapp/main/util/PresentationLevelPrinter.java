package com.silverstarapp.main.util;

public class PresentationLevelPrinter {
	
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
		System.out.println();
		System.out.println();
		System.out.println("     Choose Option Below - Please enter digit 1, 2, or 3");
		System.out.println("     ===================================================== \n");
		System.out.println("     1  -  Customer Login");
		System.out.println("     2  -  Employee Login");
		System.out.println("     3  -  Create a Silver Star User Account");
		System.out.println("     4  -  Exit Silver Star Banking Application");
	}
	
	public static void printLoginMenu () {
		System.out.println();
		System.out.println();
		System.out.println("     Please Login:");
		System.out.println("     ===================================================== \n");
		System.out.println("     To return to the root menu please press Enter with no value.");
	}
	
	public static void printNewUserMenu () {
		System.out.println();
		System.out.println();
		System.out.println("     Please Enter all required data. Follow entry with *Enter*");
		System.out.println("     ============================================================= \n");
		System.out.println("     1) First Name (required): ");
	}
	
	public static void printEmployeeMenu () {
		System.out.println();
		System.out.println();
		System.out.println("     Choose Option Below - Please enter digit 1, 2, or 3");
		System.out.println("     ===================================================== \n");
		System.out.println("     1  -  View Account Applications");
		System.out.println("     2  -  View Customer Account");
		System.out.println("     3  -  View Log Files");
		System.out.println("     /nEnter 0 to return to main menu.");
		
	}
	
	public static void printCustomerMenu () {
		System.out.println();
		System.out.println();
		System.out.println("     Choose Option Below - Please enter single digit 1-5 ");
		System.out.println("     ===================================================== \n");
		System.out.println("     1  -  View Account Balance");
		System.out.println("     2  -  Deposit Funds");
		System.out.println("     3  -  Withdraw Funds");
		System.out.println("     4  -  Transfer Funds");
		System.out.println("     5  -  Apply for a new Account");
		System.out.println("     /nEnter 0 to return to main menu.");
	}	
	
	
}
