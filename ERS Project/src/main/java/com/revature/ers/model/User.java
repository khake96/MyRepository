package com.revature.ers.model;

public class User {
	
	private int iD;
	private String firstName;
	private String lastName;
	private int role;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + iD;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + role;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (iD != other.iD)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (role != other.role)
			return false;
		return true;
	}
	
	public User() {
		super();
	}

	public User(int iD, String firstName, String lastName, int role) {
		super();
		this.iD = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	
	public User(String firstName, String lastName, int role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [iD=" + iD + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + "]";
	}
	

}
