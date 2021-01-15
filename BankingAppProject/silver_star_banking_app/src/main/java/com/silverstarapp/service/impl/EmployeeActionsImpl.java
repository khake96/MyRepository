package com.silverstarapp.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import com.silverstarapp.dao.EmployeeDAO;
import com.silverstarapp.dao.impl.EmpoloyeeDaoImpl;
import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.main.SilverStarAppMain;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.AccountHistory;
import com.silverstarapp.model.Employee;
import com.silverstarapp.model.Login;
import com.silverstarapp.service.EmployeeActions;

/* This Service level EmployeeAction class file contains the implementation for a Silver
 * Star Banking employee Interface file. 
 */

public class EmployeeActionsImpl implements EmployeeActions {
	
	private EmployeeDAO employeeDAO = new EmpoloyeeDaoImpl();

	@Override
	public boolean approveApplication(int applicationID, boolean approved) throws BusinessException , SQLException{
		boolean status = false;
		// Perform business logic
		if(applicationID>700000) {
			// approved will create new customer account and delete application. 
			// not approved will simply delete the application.
			status = employeeDAO.approveApplication(applicationID, approved);
		} else SilverStarAppMain.log.warn("Application number: " + applicationID +" is not valid. Please try again.");
		return status;
	}

	@Override
	public AccountHistory viewCustomerAccount(int accountNumber) throws BusinessException, SQLException {
		AccountHistory account = new AccountHistory();
		// Perform business level logic
		if(accountNumber >= 100000 && accountNumber < 500000) {
			account = employeeDAO.viewCustomerAccount(accountNumber);
		} else SilverStarAppMain.log.warn("Customer Account "+accountNumber+" is invalid. Please try again.");
		return account;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AccountApplication> getAllApplications() throws BusinessException, SQLException {
		@SuppressWarnings("rawtypes")
		List<AccountApplication> accountList = new ArrayList();
		// no business logic - pass through request
		accountList = employeeDAO.getAllApplications();
		return accountList;
	}
	
	public Employee setEmployee(int employeeID) throws BusinessException, SQLException {
		Employee employee = new Employee();
		// Perform business logic
		if(employeeID > 1000 && employeeID < 5000) {
			employee = employeeDAO.setEmployee(employeeID);
		} else SilverStarAppMain.log.warn("Error with employee creation request. Please try again.");
		return employee;
	}

	@Override
	public Employee employeeLogin(Login login) throws BusinessException, SQLException {
		Employee employee = null;
		//Perform business logic	
		if (login.getPassword().length() > 0 && login.getUserName().length() > 0) {
			employee = employeeDAO.employeeLogin(login);
		} else SilverStarAppMain.log.warn("This is not a valid employee login or password. Please try again.");
		return employee;
	}

	@Override
	public String viewLogs(File file) throws IOException {
	        StringBuilder sb = new StringBuilder();
	        InputStream in = new FileInputStream(file);
	        try {
		        @SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
		        String line;
		        while ((line = br.readLine()) != null) {
		            sb.append(line + System.lineSeparator());
		        }
	        } catch (FileNotFoundException e) {
	        	SilverStarAppMain.log.debug(e.getMessage());
	        }
	        catch (Exception e) {
	        	SilverStarAppMain.log.debug(e.getMessage());
	        }
	        return sb.toString();
	}
	@Override
	public String formatApplicationList (List<AccountApplication> applicationList) throws BusinessException{
		Formatter fmt = new Formatter();
		fmt.format("%20s %20s %20s %20s %20s\n", "Application ID", "Starting Balance", "Gross Income", "Expenses", "Customer ID");
		NumberFormat usd = NumberFormat.getCurrencyInstance(Locale.US);
		StringBuffer output = new StringBuffer();
		output.append("Account applications: \n"
				+ "-----------------------------------------------------------------------\n");	
		for(AccountApplication a : applicationList) {
				 fmt.format("%20s %20s %20s %20s %20s\n", a.getApplicationID(), 
						 usd.format(a.getStartingBalance()), usd.format(a.getIncomeGross()), usd.format(a.getExpenses()), a.getCustomer());
		}
		output.append(fmt);
		return output.toString();
	}
}
