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
		System.out.println("     1  -  Login (existing Silver Star Customer)");
		System.out.println("     2  -  Create a Silver Star User Account");
		System.out.println("     3  -  Exit Silver Star Banking Application");
	}
	
	public static void printLoginMenu () {
		System.out.println();
		System.out.println();
		System.out.println("     Please Login: Username *Enter* Password *Enter*");
		System.out.println("     ===================================================== \n");
		System.out.println("     Enter Username first and press Enter.");
		System.out.println("     Next enter Password and press Enter.\n");	
		System.out.println("     To return to the root menu please press Enter twice with no values.");
	}
	
	public static void printNewUserMenu () {
		System.out.println();
		System.out.println();
		System.out.println("     Please Enter all required data. Follow each item with *Enter*");
		System.out.println("     ============================================================= \n");
		System.out.println("     1) First Name (required): ");
	}
	
	
}
