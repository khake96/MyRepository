package com.revature.ers.model;

public class LoginDTO {
	
	public Login login;
	public String userName;
	public String password;
	
	public LoginDTO(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		this.login = new Login(userName, password);
	}

	public LoginDTO() {
		super();
	}

	@Override
	public String toString() {
		return "LoginDTO [login=" + login + ", userName=" + userName + ", password=" + password + "]";
	}
	
	



}
