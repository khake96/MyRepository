package com.silverstarapp.model;

import java.util.Arrays;
import java.util.List;

public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private String contact;
	
	
	public Customer(int id, String firstName, String lastName, String contact) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer setCustomer(Login login) {
		Customer customer = new Customer();
		// pass login to DAO to get customer
		return customer;
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


	public String getContact() {
		return contact;
	}


	public void setContact(String string) {
		this.contact = string;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Customer [Customer ID= " + id + ", First Name= " + firstName + ", Last Name= " + lastName 
				+ ", Contact= " + contact + "]";
	} 
}
