package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import com.revature.ers.model.Login;
import com.revature.ers.model.Request;
import com.revature.ers.model.User;
import com.revature.ers.service.impl.BusinessLogicChecksImpl;

public class ServiceTest {
	
		BusinessLogicChecksImpl userActions = new BusinessLogicChecksImpl();
		Timestamp ts = new Timestamp(0);
		
		
		// Passing Test case values
		Login validLogin = new Login("JennyTilson", "1595EIojie!!ko");
		User validManager = new User(124, "Sally", "Johnson", 2);
		User validManager2 = new User(130, "Sally", "Johnson", 2);
		User validEmployee = new User(103,"Johnny", "Boorsma", 1);
		Request validRequest = new Request(1, 100.57, ts, ts, "This was for a hotel stay in New York in July.", null, 101, 102, 2, 3);
		
		// Invalid Test case values
		Login loginNoUserName = new Login("", "wokpeowj3902;odpwk");
		Login loginShortUserName = new Login("JorgeSt", "2093209032");
		Login loginInvalidSymbolUserName = new Login("Jorgen15{","12345678");
		Login loginShortPassword = new Login("SamuelJorgan", "15@/o7");
		Login loginLongPassword = new Login("SamuelJorgan", "123456789012345678901234567890123456789012345678901");
		Login loginMultipleInvalid = new Login("Samuel", "123456789012345678901234567890123456789012345678901");
		User managerInvalidRole = new User(101, "Tim", "Allen", 1);
		User userNoFirstName = new User(01,"", "Boorsma",1);
		User userNoLastName = new User(101,"Johnny", "",2);
		User userBadRole = new User(70000,"Johnny", "Boorsma", 496);	
		User userInvalidUserId = new User(1,"", "Boorsma",1);	
		Request requestNegative = new Request(1, -0.01, ts, ts, "This was for a hotel stay in New York in July.", null, 101, 102, 1, 3);
		Request requestTooMuch = new Request(1, 10000.01, ts, ts, "This was for a hotel stay in New York in July.", null, 101, 102, 1, 3);
		Request requestShortDescription = new Request(1, 100.57, ts, ts, "something", null, 101, 102, 1, 3);
		Request requestLongDescription = new Request(1, 100.57, ts, ts, "This was for a hotel stay in New York in July.This was for a hotel stay in "
				+ "New York in July.This was for a hotel stay in New York in July.This was for a hotel stay in New York in July.This was for a hotel "
				+ "stay in New York in July.This was for a hotel stay in New York in July.This was for a hotel stay in New York in July.This was for "
				+ "a hotel stay in New York in July.This was for a hotel stay in New York in July.This was for a hotel stay in New York in July.This "
				+ "was for a hotel stay in New York in July.This was for a hotel stay in New York in July.This was for a hotel stay in New York in "
				+ "July.This was for a hotel stay in New York in July.This was for a hotel stay in New York in July.", null, 101, 102, 1, 3);
		Request requestInvalidType = new Request(1, 100.57, ts, ts, "This was for a hotel stay in New York in July.", null, 101, 102, 101, 5);
		Request requestInvalidAuthorLow = new Request(1, 100.57, ts, ts, "This was for a hotel stay in New York in July.", null, 101, 102, 1, 0);
		Request requestInvalidAuthorHigh = new Request(1, 100.57, ts, ts, "This was for a hotel stay in New York in July.", null, 101, 102, 1, 5);			

		// Valid Test Cases
		// Check valid username
		@Test
		public void testBusinessLogicChecksImplUserNameValid() {
			boolean result = userActions.isValidUserName(validLogin);
			assertTrue(result);
		}
		
		// Check valid password
		@Test
		public void testBusinessLogicChecksImplPasswordValid() {
			boolean result = userActions.isValidPassword(validLogin);
			assertTrue(result);
		}
		
		// Check valid manager
		@Test
		public void testBusinessLogicChecksImplIsManagerValid() {
			boolean result = userActions.isManager(validManager);
			assertTrue(result);
		}
		
		// Check valid request
		@Test
		public void testBusinessLogicChecksImplIsRequestValid() {
			boolean result = userActions.isValidRequest(validRequest);
			assertTrue(result);
		}
		
		// Check user is valid
		@Test
		public void testBusinessLogicChecksImplIsValidUser() {
			boolean result = userActions.isValidUser(validEmployee);
			assertTrue(result);
		}
		
		// Check is not own request
		@Test
		public void testBusinessLogicChecksImplIsOwnRequest() {
			boolean result = userActions.isNotOwnRequest(validManager, validRequest);
			assertTrue(result);
		}
		
		
		// Invalid Test Cases                    //////////////////////////////////////////
		// Check no user name case
		@Test
		public void testBusinessLogicChecksImplNoUserName() {
			boolean result = userActions.isValidUserName(loginNoUserName);
			assertFalse(result);
		}
		
		// Check short user name case
		@Test
		public void testBusinessLogicChecksImplShortUserName() {
			boolean result = userActions.isValidUserName(loginShortUserName);
			assertFalse(result);
		}
		
		// Check invalid symbol username
		@Test
		public void testBusinessLogicChecksImplInvalidSymbolUserName() {
			boolean result = userActions.isValidUserName(loginInvalidSymbolUserName);
			assertFalse(result);
		}
		
		// Check short password
		@Test
		public void testBusinessLogicChecksImplShortPassword() {
			boolean result = userActions.isValidPassword(loginShortPassword);
			assertFalse(result);
		}
		
		// Check long password
		@Test
		public void testBusinessLogicChecksImplLongPassword() {
			boolean result = userActions.isValidPassword(loginLongPassword);
			assertFalse(result);
		}
		
		// Check multiple invalid inputs login
		@Test
		public void testBusinessLogicChecksImplLoginMultipleInvalid() {
			boolean result = userActions.isValidPassword(loginMultipleInvalid);
			assertFalse(result);
		}
		
		// Check user no first name
		@Test
		public void testBusinessLogicChecksImplUserNoFirstName() {
			boolean result = userActions.isValidUser(userNoFirstName);
			assertFalse(result);
		}
		
		// Check user no last name
		@Test
		public void testBusinessLogicChecksImplUserNoLastName() {
			boolean result = userActions.isValidUser(userNoLastName);
			assertFalse(result);
		}
		
		// Check user bad role
		@Test
		public void testBusinessLogicChecksImplUserBadRole() {
			boolean result = userActions.isValidUser(userBadRole);
			assertFalse(result);
		}

		// Check user invalid ID
		@Test
		public void testBusinessLogicChecksImplUserInvalidRole() {
			boolean result = userActions.isValidUser(userInvalidUserId);
			assertFalse(result);
		}
		
		// Check request negative balance
		@Test
		public void testBusinessLogicChecksImplRequestNegativeBalance() {
			boolean result = userActions.isValidRequest(requestNegative);
			assertFalse(result);
		}
		
		// Check request too great balance
		@Test
		public void testBusinessLogicChecksImplRequestTooGreatBalance() {
			boolean result = userActions.isValidRequest(requestTooMuch);
			assertFalse(result);
		}
		
		// Check request Short Description
		@Test
		public void testBusinessLogicChecksImplRequestShortDescription() {
			boolean result = userActions.isValidRequest(requestShortDescription);
			assertFalse(result);
		}
		
		// Check request Long Description
		@Test
		public void testBusinessLogicChecksImplRequestLongDescrtiption() {
			boolean result = userActions.isValidRequest(requestLongDescription);
			assertFalse(result);
		}
		
		// Check request invalid type
		@Test
		public void testBusinessLogicChecksImplRequestInvalidType() {
			boolean result = userActions.isValidRequest(requestInvalidType);
			assertFalse(result);
		}
		
		// Check request InvalidAurthorLow
		@Test
		public void testBusinessLogicChecksImplRequestInvalidAuthorLow() {
			boolean result = userActions.isValidRequest(requestInvalidAuthorLow);
			assertFalse(result);
		}
		
		// Check request Invalid Author High
		@Test
		public void testBusinessLogicChecksImplRequestInvalidAuthorHigh() {
			boolean result = userActions.isValidRequest(requestInvalidAuthorHigh);
			assertFalse(result);
		}
		
		// Check is own request rejected 
		@Test
		public void testBusinessLogicChecksImplIsNotOwnRequest() {
			boolean result = userActions.isNotOwnRequest(validManager2, validRequest);
			assertTrue(result);
		}

}
