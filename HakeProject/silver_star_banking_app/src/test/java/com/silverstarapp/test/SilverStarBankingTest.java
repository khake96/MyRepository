package com.silverstarapp.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import com.silverstarapp.main.util.MenuConsoles;
import com.silverstarapp.model.Address;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Login;
import com.silverstarapp.model.User;
import com.silverstarapp.service.impl.CustomerActionsImpl;
import com.silverstarapp.service.impl.EmployeeActionsImpl;
import com.silverstarapp.service.impl.UserActionsImpl;

public class SilverStarBankingTest {

	public static Scanner scanner = new Scanner(System.in);
	int selection = 0;
	MenuConsoles menu = new MenuConsoles();
	Login login = new Login("Jenny", "1595EIojie!!ko");
	Login loginNoUserName = new Login("", "wokpeowj3902;odpwk");
	EmployeeActionsImpl employeeActions = new EmployeeActionsImpl();
	CustomerActionsImpl customerActions = new CustomerActionsImpl();
	UserActionsImpl userActions = new UserActionsImpl();
	NumberFormat usd = NumberFormat.getCurrencyInstance(Locale.US);
	Customer customer = new Customer(1, "Sally", "Johnson", "5468799852");
	Address address = new Address(250, "Rodeo Drive", "", "Arlington", "TN","56984-8956");
	User user = new User(1,"Johnny", "Boorsma",4896578956L, "Johnny","password", address);
	User user2 = new User(1,"Johnny", "Boorsma",496578956L, "Johnny","password", address);
	User user3 = new User(1,"", "Boorsma",496578956L, "Johnny","password", address);
	User user4 = new User(1,"Johnny", "",496578956L, "Johnny","password", address);
	User user5 = new User(70000,"Johnny", "Boorsma",496578956L, "Johnny","password", address);
	
		@Test
		public void testTopLevelMenu() {
			System.out.println("Enter 1");
			selection = menu.topLevelMenu();
			assertEquals(selection, 1);
		}
		
		@Test
		public void testTopLevelMenuNull() {
			System.out.println("Hit Enter with no value: ");
			selection = menu.topLevelMenu();
			assertEquals(selection, 10);
		}
		
		@Test
		public void testTopLevelMenuString() {
			System.out.println("Enter a string value: ");
			selection = menu.topLevelMenu();
			assertEquals(selection, 10);
		}
		
		// customer actions login is business valid
		@Test
		public void testCustomerLoginIsBusinessValid() {
			boolean result = customerActions.loginBusinessValid(login);
			assertTrue(result);
		}
		
		// customer actions login is not business valid
		@Test
		public void testCustomerLoginIsBusinessValidNoUserName() {
			boolean result = customerActions.loginBusinessValid(loginNoUserName);
			assertFalse(result);
		}
		
		// customer actions account application is business valid
		@Test
		public void testCustomerAccountApplicationBusinessValid() {
			boolean result = customerActions.applicationIsBusinessValid(1, 560.04, 58000, 8900);
			assertTrue(result);
		}
		
		// customer actions account application is rejected for wrong income/expense ratio
		@Test
		public void testCustomerAccountApplicationBusinessValidInvalidIncome() {
			boolean result = customerActions.applicationIsBusinessValid(5, 560.04, 800, 8900);
			assertFalse(result);
		}
		
		// customer actions account application is rejected for wrong income/expense ratio
		@Test
		public void testCustomerAccountApplicationBusinessValidLowStartingBalance() {
			boolean result = customerActions.applicationIsBusinessValid(5, 499, 800000, 8900);
			assertFalse(result);
		}
		
		// user actions registrationApplicationIsBusinessValid (User user)
		@Test
		public void testUserActionsApplicationBusinessValid() {
			boolean result = userActions.registrationApplicationIsBusinessValid(user);
			assertTrue(result);
		}
	
		// user actions registrationApplication rejects illegal contact
		@Test
		public void testUserActionsApplicationIllegalContact() {
			boolean result = userActions.registrationApplicationIsBusinessValid(user2);
			assertFalse(result);
		}	
		
		// user actions registrationApplication rejects illegal first name
		@Test
		public void testUserActionsApplicationIllegalFirstName() {
			boolean result = userActions.registrationApplicationIsBusinessValid(user3);
			assertFalse(result);
		}	
		
		// user actions registrationApplication rejects illegal last name
		@Test
		public void testUserActionsApplicationIllegalLastName() {
			boolean result = userActions.registrationApplicationIsBusinessValid(user4);
			assertFalse(result);
		}	
		
		// user actions registrationApplication rejects illegal Id
		@Test
		public void testUserActionsApplicationIllegalID() {
			boolean result = userActions.registrationApplicationIsBusinessValid(user5);
			assertFalse(result);
		}
	
	@AfterAll
	static void closeConnection() throws SQLException {
		scanner.close();
	}
}
	

