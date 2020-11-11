package com.silverstarapp.dao.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.main.SilverStarAppMain;

/* This DbUtil class file stores all queries accessible
 * by Silver Star App banking users, employees and customers.
 * Queries are divided into appropriate sub-headings based 
 * on general function.
 */

public class DbUtil {

	
	@SuppressWarnings("unchecked")
	public boolean isCustAccount(int questionAccountID, int customerID) throws BusinessException {
		
		// check that debit account belongs to the customer
		@SuppressWarnings("rawtypes")
		List<Integer> accountIdList = new ArrayList();
		boolean isCustAccount = false;
		try (Connection connection = PostgresSqlConnection.getConnection()){
			String sql = CustomerSearchQueries.ACCOUNT_VALIDATION;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerID);
			ResultSet resultSet = preparedStatement.executeQuery();
			// populate list with all accounts owned by customer			
			while(resultSet.next()) {
				Integer ownedAccountId = new Integer(resultSet.getInt("account_id"));
				accountIdList.add(ownedAccountId); 	
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new BusinessException("Invalid customer ID!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException("SQL Invalid customer ID!");	
		}
		// check that the passed account is in the customer's account list
		for(int i:accountIdList) {
			if(i==questionAccountID) 
				isCustAccount = true; 
			    SilverStarAppMain.log.debug("Is customer account evaluated TRUE.");
		}
		SilverStarAppMain.log.debug("Is customer account evaluated FALSE.");
		return isCustAccount;
	}
}
