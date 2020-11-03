package com.silverstarapp.service.impl;

import java.util.List;

import org.postgresql.util.PGTimestamp;

import com.silverstarapp.dao.customer.CustomerDAO;
import com.silverstarapp.dao.impl.customer.CustomerDaoImpl;
import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Account;
import com.silverstarapp.model.AccountApplication;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.Login;
import com.silverstarapp.service.CustomerActions;

public class CustomerActionsImpl implements CustomerActions {
	
	private CustomerDAO customerDAO = new CustomerDaoImpl();

	@Override
	public int customerLogin(Login login) throws BusinessException {
			int customerID = -1;
			customerID = customerDAO.customerLogin(login);
			if (customerID < 0) {
				System.out.println("Not a valid login. From CustomerActionImpl");
			}
			return customerID;
	}

	@Override
	public AccountApplication accountApplication(Customer customer, double startingBalance, PGTimestamp ts)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> transferFunds(int sourceID, int destinationID, double amount) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account deposit(int accountID, double deposit) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account withdraw(int accountID, double withdrawal) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account viewBalance(int accountID) throws BusinessException {
		Account account = new Account();
		
		return null;
	}


}
