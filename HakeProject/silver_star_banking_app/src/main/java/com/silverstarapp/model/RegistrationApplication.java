package com.silverstarapp.model;

public class RegistrationApplication {
	
	private int applicationID;
	private User user;

	
	public RegistrationApplication(int applicationID, User user) {
		super();
		this.applicationID = applicationID;
		this.user = user;

	}


	public RegistrationApplication() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getApplicationID() {
		return applicationID;
	}


	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return "Registration Application [Application ID= " + applicationID + ", User= "+ user + "]";
	}
	
	
	

}
