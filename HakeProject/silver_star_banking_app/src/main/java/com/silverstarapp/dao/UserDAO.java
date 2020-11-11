package com.silverstarapp.dao;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.User;

/* This Silver Star Banking App customer DAO Interface specifies the 
 * requirements for the Database Acquisition Objects required by the
 * users of the Silver Star Bank.
 */

public interface UserDAO {
	
	boolean isUniqueUserName (String userName) throws BusinessException;
	Customer createNewCustomer (User user) throws BusinessException;

}
