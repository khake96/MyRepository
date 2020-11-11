package com.silverstarapp.service;

/* This Service level UserAction interface file contains the requirements for a Silver
 * Star Banking user useage. The implementation is found in the corresponding Impl class.
 */

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.model.Customer;
import com.silverstarapp.model.User;

public interface UserActions {
	public Customer newRegistrationApplication(User user) throws BusinessException;
}
