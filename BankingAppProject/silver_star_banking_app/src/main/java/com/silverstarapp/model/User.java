package com.silverstarapp.model;

/* This User class file contains the data required for a Silver
 * Star Banking user. The data for each instance is stored in
 * the database.
 */

public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private long contact;
	private String userName;
	private String password;
	private Address address;
	
	
	public User(int id, String firstName, String lastName, long contact, String userName, String password,
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
	}
	
	// Provided so that test cases can be run comparing objects
	@Override
    public boolean equals(Object obj){
        User x = (User) obj;
        boolean status = false;
        if(this.id == x.id
                && this.firstName.equals(x.firstName) 
                && this.lastName.equals(x.lastName) ){
            status = true;
        }
        return status;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
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

	
}
