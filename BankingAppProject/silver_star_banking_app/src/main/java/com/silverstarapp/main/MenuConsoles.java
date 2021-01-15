package com.silverstarapp.main;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

import com.silverstarapp.main.util.PresentationLevelPrinter;
import com.silverstarapp.model.Address;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Employee;
import com.silverstarapp.model.Login;
import com.silverstarapp.model.User;

 class MenuConsoles {
	
     Scanner scanner = new Scanner(System.in);
     
   int topLevelMenu() {
    	PresentationLevelPrinter.printSilverStarLogo();
    	PresentationLevelPrinter.printRootMenu();
        int selection = 0;
        	selection = Integer.parseInt(scanner.nextLine());
        return selection;
  }

     Login loginMenu() {
        PresentationLevelPrinter.printLoginMenu(); 
        Login login = new Login();
        System.out.println("Please enter User Name: ");
        String username = scanner.nextLine();
		if(username.equals("")) {
			System.out.println("Returning to root menu.");
			return login;
		}
		login.setUserName(username);
		System.out.println("\nPlease enter password: ");
		login.setPassword(scanner.nextLine().toCharArray());
        return login;
    }

     User newUserMenu() {
         PresentationLevelPrinter.printNewUserMenu(); 
         User user = new User();  
         Address address = new Address();
         
         user.setFirstName(scanner.nextLine());
         System.out.println("    2) Last Name (required): ");
         user.setLastName(scanner.nextLine());
         System.out.println("    3) Silver Star Username (required): ");
         user.setUserName(scanner.nextLine());
         System.out.println("    4) Silver Star Password (required): ");
         user.setPassword(scanner.nextLine().toCharArray());
         System.out.println("    5) Phone contact eg. +11231234567 (optional): ");
         user.setContact(Long.parseLong(scanner.nextLine()));         
         System.out.println("    6) House Number (required): ");
         address.setHouseNo(Integer.parseInt(scanner.nextLine()));
         System.out.println("    7) Street Name (required): ");
         address.setStreetName(scanner.nextLine());   
         System.out.println("    8) Second Address Line (optional): ");
         address.setSecondLine(scanner.nextLine());
         System.out.println("    9) City (required): ");
         address.setCity(scanner.nextLine()); 
         System.out.println("    10) State eg. TN (required): ");
         address.setState(scanner.nextLine()); 
         System.out.println("    11) ZIP code (required): ");
         address.setZip(scanner.nextLine());
         user.setAddress(address);
         return user; // pass to business layer to create new customer

     }
     
     int employeeMenu () {
    	 PresentationLevelPrinter.printEmployeeMenu();
    	 int selection = Integer.parseInt(scanner.nextLine());
    	 return selection;
     }
     
     int customerMenu () {
    	 PresentationLevelPrinter.printCustomerMenu();
    	 int selection = Integer.parseInt(scanner.nextLine());
    	 return selection;
     }
     
     int getAccountNumber () {
    	 System.out.println("\nPlease enter account number: ");
    	 int accountNumber = Integer.parseInt(scanner.nextLine());
    	 return accountNumber;
     }
     
     double getDepositAmount () {
    	 System.out.println("\nPlease enter deposit amount: ");
    	 double depositAmount = Double.parseDouble(scanner.nextLine());
    	 return depositAmount;
     }
     
     double getWithdrawAmount () {
    	 System.out.println("\nPlease enter withdraw amount: ");
    	 double withdrawAmount = Double.parseDouble(scanner.nextLine());
    	 return withdrawAmount;
     }
     
     double getTransferAmount() {
    	 System.out.println("\nPlease enter transfer amount: ");
    	 double transferAmount = Double.parseDouble(scanner.nextLine());
    	 return transferAmount;
     }
     
     double getIncome() {
    	 System.out.println("\nPlease enter gross yearly income: ");
    	 double income = Double.parseDouble(scanner.nextLine());
    	 return income;
    	 }
     
     double getExpenses() {
    	 System.out.println("\nPlease enter yearly expenses: ");
    	 double expenses = Double.parseDouble(scanner.nextLine());
    	 return expenses;
    	 }
    
}