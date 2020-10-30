package com.silverstarapp.model;

import com.silverstarapp.exception.BusinessException;

public class Login {
	
	private String userName;
	private char[] password;
	
	
	
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Login(String userName, char[] password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	
	public boolean isBusinessValid() throws BusinessException {
		boolean result = false;
		if(userName!=null && password.length>0)
			result = true;	
	return result;
	}

}
