package com.silverstarapp.main;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.main.util.PresentationLevelPrinter;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Employee;
import com.silverstarapp.model.Login;
import com.silverstarapp.model.User;
import com.silverstarapp.service.CustomerActions;
import com.silverstarapp.service.EmployeeActions;
import com.silverstarapp.service.impl.CustomerActionsImpl;
import com.silverstarapp.service.impl.EmployeeActionsImpl;

public class SilverStarAppMain {

	public static void main(String[] args) {
		
		int selection = 0;
		MenuConsoles menu = new MenuConsoles();
		Login login = new Login();
		User user = new User();
		EmployeeActions employeeActions = new EmployeeActionsImpl();
		CustomerActions customerActions = new CustomerActionsImpl();
		Employee employee = new Employee();
		Customer customer = new Customer();
	
		do {
			try {
			selection = menu.topLevelMenu();
			} 
			catch (NumberFormatException e) {
				System.out.println("Please enter digits 1, 2, 3, or 4. Thankyou!");
			}
			switch (selection) {	
				case 1: { // Entering Customer login menu 
					login = menu.loginMenu();
					int customerID = -1;
					try {
						if (login.isBusinessValid()) {
							try {
								customerID = customerActions.customerLogin(login);
							} catch (BusinessException e) {}	
						}
						if (customerID > 0 && customerID < 1000) {
						// Login is Customer
							int customerSelection = -1;
							int accountNumber = 0;
							int depositAccountNumber = 0;
							double movingMoney = 0;
							Account activeAccount = new Account();
							Account withdrawAccount = new Account();
							double openingBalance = 0;
							double income = 0;
							double expenses = 0;	
									do {
										try {
										customer = customer.setCustomer(login);
										customerSelection = menu.customerMenu();
										switch (customerSelection) {
										case 1: { //View Account Balance
											accountNumber = menu.getAccountNumber();
											activeAccount = activeAccount.getAccount(accountNumber);
											System.out.println(activeAccount.toString());
											break;
										}
										case 2: { //Deposit Funds
											depositAccountNumber = menu.getAccountNumber();
											movingMoney = menu.getDepositAmount();
											// Pass as a deposit
											break;
										}
										case 3: { // Withdraw Funds
											accountNumber = menu.getAccountNumber();
											movingMoney = menu.getWithdrawAmount();
											// Pass as a withdrawal
											break;
										}
										case 4: { // Transfer Funds
											System.out.println("Account for withdrawal: ");
											accountNumber = menu.getAccountNumber();
											System.out.println("Account for deposit: ");
											depositAccountNumber = menu.getAccountNumber();
											movingMoney = menu.getTransferAmount();
											// Pass as a transfer
											break;
										}
										case 5: { // Apply for a new Account
											openingBalance = menu.getDepositAmount();
											income = menu.getIncome();
											expenses = menu.getExpenses();
											// Pass as an application
											break;
										} // end case
										} // end switch
									} catch (NumberFormatException e) {}
									} while (customerSelection != 0); 	
								} // End customer menu				
								else System.out.println("Not a valid customer login for " + customer.toString());
						} catch (BusinessException e) {
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
						break;
				}
				case 2: { // Entering Employee Login
					login = menu.loginMenu();
					int employeeID = -1;
					int employeeSelection = -1;
					int accountNumber = 0;
					Account customerAccount = new Account();
					try {
						try {
							employeeID = employeeActions.employeeLogin(login);
						} catch (BusinessException e){}		
						if (employeeID >= 1000 && employeeID < 5000) {
							do {
								try { // Login is Employee
									employeeSelection = menu.employeeMenu();
									switch(employeeSelection) {
									case 1: { // View Account Applications
										
										break;	
									}
									case 2: { // View customer account
										System.out.println("Please enter account number:");
										accountNumber = menu.getAccountNumber();
										customerAccount = customerAccount.getAccount(accountNumber);
										System.out.println(customerAccount.toString());
										break;
									}
									case 3: { // View log files
										
										break;
									}
									}
								} catch (NumberFormatException e) {}
							} while (employeeSelection != 0);
						} else System.out.println("Login is not valid for "+ employee.toString());
					} catch (NumberFormatException e) {}
					break;
				} // end case 2				
				case 3: { // Entering new user menu
					user = menu.newUserMenu();
					if (user.isBusinessValid(user)) {
						System.out.println("Welcome to Silver Star Banking! You are now"
								+ "a customer! Please log in again to apply for an account!");
					} 
					break;
				}
			case  4: {		
				// User chooses to exit Silver Star app 
				PresentationLevelPrinter.printSilverStarExit();
				menu.scanner.close();
				break;
			}
			default:
				System.out.println("So sorry! We have encountered issues with your input. "
						+ "\nPlease try again.");

			} // end of top level switch statement
		} while (selection!=4);
	} // end of static main void
} // end of class


