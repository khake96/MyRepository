package com.silverstarapp.model;

import java.util.Arrays;

public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private long contact;
	private String userName;
	private char[] password;
	private Address address;
	
	
	public User(int id, String firstName, String lastName, long contact, String userName, char[] password,
			Address address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
		this.userName = userName;
		this.password = password;
		this.address = address;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public long getContact() {
		return contact;
	}


	public void setContact(long contact) {
		this.contact = contact;
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


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "User [User ID= " + id + ", First Name= " + firstName + ", Last Name= " + lastName + ", Contact= " + contact
				+ ", User Name= " + userName + ", Password= *protected*" + ", Address=" + address + "]";
	}
	
	public boolean isBusinessValid(User user) {
		boolean result = false;
		if(!user.userName.equals(null)||user.password.length!=0);
		result = true;
		return result;
	}

	
}
