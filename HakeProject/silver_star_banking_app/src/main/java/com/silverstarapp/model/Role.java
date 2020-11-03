package com.silverstarapp.model;

import com.silverstarapp.exception.BusinessException;

public class Role {
	
	// Returns a 1 for employee, 2 for customer and 3 for a user
	
	int result = 0;
	
	public int getRole(Login login) throws BusinessException {
		
		// access employee database return
		if (login = employee login) {
			set result = 1;
		} else
		if (login = customer login) {
			set result = 2;
		} else
		if (login = user login) {
			set result = 3;
		}
		return result;
	}

}
