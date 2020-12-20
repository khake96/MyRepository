package com.revature.ers.model;

public class UserDTO {
	
	public String firstName;
	public String lastName;
	public int userId;

	public UserDTO(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UserDTO(int userId) {
		super();
		this.userId = userId;
	}

	public UserDTO() {
		super();
	}


	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", userId=" + userId + "]";
	}
	
	

}
