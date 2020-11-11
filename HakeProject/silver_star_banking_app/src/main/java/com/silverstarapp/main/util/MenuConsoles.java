package com.silverstarapp.main.util;

import java.util.Scanner;

import com.silverstarapp.main.SilverStarAppMain;
import com.silverstarapp.model.Address;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Employee;
import com.silverstarapp.model.Login;
import com.silverstarapp.model.User;

/* This Silver Star Banking App customer utility provides user input methods
 * for call from the main application of Silver Star Bank.
 */

 public class MenuConsoles {
	
   public Scanner scanner = new Scanner(System.in);
   private UserMenuSelected userMenu = new UserMenuSelected();
     
   public int topLevelMenu() {
    	PresentationLevelPrinter.printRootMenu();
        int selection = 0;
        try {
        	selection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
        	selection = 10;
        }   	
        	SilverStarAppMain.log.debug("User selection: " + selection);
        return selection;
  }

  public   Login loginMenu() {
        PresentationLevelPrinter.printLoginMenu(); 
        Login login = new Login();
        SilverStarAppMain.log.info("Please enter User Name: ");
        String username = scanner.nextLine();
        SilverStarAppMain.log.debug("User name: " + username);
		if(username.equals("")) {
			SilverStarAppMain.log.info("Returning to root menu.");
			return login;
		}
		login.setUserName(username);
		SilverStarAppMain.log.info("\nPlease enter password: ");
		String pword = scanner.nextLine();
		login.setPassword(pword);
		SilverStarAppMain.log.debug("User password: " + pword );
        return login;
    }
     
 public    int employeeMenu (Employee employee) {
	int selection = 10;
    PresentationLevelPrinter.printEmployeeMenu(employee);
    try {
    	selection = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
    	selection = 10;
    }   
    SilverStarAppMain.log.debug("User selection: " + selection );
    return selection;
     }
     
public    int customerMenu (Customer customer) {
	int selection = 10;
    PresentationLevelPrinter.printCustomerMenu(customer);
    try {
    	selection = Integer.parseInt(scanner.nextLine());
    	} catch (NumberFormatException e) {
    		selection = 10;
    	} 
    	SilverStarAppMain.log.debug("User selection: " + selection );
    	return selection;
 }
     
public     int getAccountNumber () {
    	 SilverStarAppMain.log.info("\nPlease enter account number: ");
    	 int accountNumber = Integer.parseInt(scanner.nextLine());
    	 SilverStarAppMain.log.debug("Account number entered: " + accountNumber);
    	 return accountNumber;
     }

public     int getAccountApplicationNumber () {
	SilverStarAppMain.log.info("\nPlease enter account application number: ");
	 int accountNumber = Integer.parseInt(scanner.nextLine());
	 SilverStarAppMain.log.debug("Application account number entered: " + accountNumber);
	 return accountNumber;
}

public     int approveApplication () {
	SilverStarAppMain.log.info("\nPlease enter 0 to deny and 1 to approve: ");
	 int accountNumber = Integer.parseInt(scanner.nextLine());
	 SilverStarAppMain.log.debug("Approval / Disapproval entered: " + accountNumber);
	 return accountNumber;
}

 public    double getDepositAmount () {
	 SilverStarAppMain.log.info("\nPlease enter deposit amount: ");
    	 double depositAmount = Double.parseDouble(scanner.nextLine());
    	 SilverStarAppMain.log.debug("Deposit amount entered: " + depositAmount);
    	 return depositAmount;
     }
 
 public    double getStartingBalance () {
	 SilverStarAppMain.log.info("\nPlease enter intial balance amount: ");
	 double depositAmount = Double.parseDouble(scanner.nextLine());
	 SilverStarAppMain.log.debug("Starting balance entered: " + depositAmount);
	 return depositAmount;
 }
     
public     double getWithdrawAmount () {
	SilverStarAppMain.log.info("\nPlease enter withdraw amount: ");
    	 double withdrawAmount = Double.parseDouble(scanner.nextLine());
    	 return withdrawAmount;
     }
     
 public    double getTransferAmount() {
	 SilverStarAppMain.log.info("\nPlease enter transfer amount: ");
    	 double transferAmount = Double.parseDouble(scanner.nextLine());
    	 SilverStarAppMain.log.debug("Transfer amount entered: " + transferAmount);
    	 return transferAmount;
     }
     
public     double getIncome() {
	SilverStarAppMain.log.info("\nPlease enter gross yearly income: ");
    	 double income = Double.parseDouble(scanner.nextLine());
    	 SilverStarAppMain.log.debug("Income amount entered: " + income);
    	 return income;
    	 }
     
 public    double getExpenses() {
	 SilverStarAppMain.log.info("\nPlease enter yearly expenses: ");
    	 double expenses = Double.parseDouble(scanner.nextLine());
    	 SilverStarAppMain.log.debug("Expenses amount entered: " + expenses);
    	 return expenses;
    	 }

public User getUser() {
	 User user = new User(); 
	 try {
		 SilverStarAppMain.log.info(userMenu.getFirstName());
	 } catch (Exception e) {SilverStarAppMain.log.debug(e.getMessage());}	 
	 String fn = scanner.nextLine();
	 user.setFirstName(fn);
	 SilverStarAppMain.log.debug("First name entered: " + fn);
	 try {
		 SilverStarAppMain.log.info(userMenu.getLastName());
	 } catch (Exception e) {SilverStarAppMain.log.debug(e.getMessage());}
	 String ln = scanner.nextLine();
	 user.setLastName(ln);
	 SilverStarAppMain.log.debug("Last name entered: " + ln);
	 try {
		 SilverStarAppMain.log.info(userMenu.getUserName());
	 } catch (Exception e) {SilverStarAppMain.log.debug(e.getMessage());}	 
	 String un = scanner.nextLine();
	 user.setUserName(un);
	 SilverStarAppMain.log.debug("User name entered: " + un);
	 try {
		 SilverStarAppMain.log.info(userMenu.getPassword());
	 } catch (Exception e) {SilverStarAppMain.log.debug(e.getMessage());}	 
	 String pw = scanner.nextLine();
	 user.setPassword(pw);
	 SilverStarAppMain.log.debug("Password entered: " + pw);
	 try {
		 SilverStarAppMain.log.info(userMenu.getContact());
	 } catch (Exception e) {SilverStarAppMain.log.debug(e.getMessage());}
	 String ct = scanner.nextLine();
	 try {
		 user.setContact(Long.parseLong(ct)); 
	 } catch (Exception e)  {SilverStarAppMain.log.debug(e.getMessage());}
	 SilverStarAppMain.log.debug("Contact entered: " + ct);
	return user;         	 
 }
 

public Address getAddress() {
	 Address address = new Address();
	 try {
		 SilverStarAppMain.log.info(userMenu.getHouseNumber());
	 } catch (Exception e) {SilverStarAppMain.log.debug(e.getMessage());} 
	 int hn = Integer.parseInt(scanner.nextLine());
	 address.setHouseNo(hn);
	 SilverStarAppMain.log.debug("House number entered: " + hn);
	 try {
		 SilverStarAppMain.log.info(userMenu.getStreetName());
	 } catch (Exception e) {SilverStarAppMain.log.debug(e.getMessage());}	 
	 String sn = scanner.nextLine();
	 address.setStreetName(sn); 
	 SilverStarAppMain.log.debug("Street name entered: " + sn);
	 try {
		 SilverStarAppMain.log.info(userMenu.getSecondLine());
	 } catch (Exception e) {SilverStarAppMain.log.debug(e.getMessage());}	 
	 String sl = scanner.nextLine();
	 address.setSecondLine(sl);
	 SilverStarAppMain.log.debug("Second address line entered: " + sl);
	 try {
		 SilverStarAppMain.log.info(userMenu.getCity());
	 } catch (Exception e) {SilverStarAppMain.log.debug(e.getMessage());}	
	 String cty = scanner.nextLine();
	 address.setCity(cty); 
	 SilverStarAppMain.log.debug("City entered: " + cty);
	 try {
		 SilverStarAppMain.log.info(userMenu.getState());
	 } catch (Exception e) {SilverStarAppMain.log.debug(e.getMessage());}	 
	 String st = scanner.nextLine();
	 address.setState(st);
	 SilverStarAppMain.log.debug("State entered: " + st);
	 try {
		 SilverStarAppMain.log.info(userMenu.getZip());
	 } catch (Exception e) {SilverStarAppMain.log.debug(e.getMessage());}	 
	 String zp = scanner.nextLine();
	 address.setZip(zp);
	 SilverStarAppMain.log.debug("Zip entered: " + zp);
	return address;	  
 }
    
}