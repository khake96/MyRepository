package com.silverstarapp.model;

/* This Customer class file contains the data required for a Silver
 * Star Banking customer. The data for each instance is stored in
 * the database.
 */

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
	
	// Provided so that test cases can be run comparing customer objects
	@Override
    public boolean equals(Object obj){
        Customer customer = (Customer) obj;
        boolean status = false;
        if(this.id == customer.id
                && this.firstName.equals(customer.firstName) 
                && this.lastName.equals(customer.lastName) ){
            status = true;
        }
        return status;
    }

	public Customer() {
		super();
	}
	
	public Customer setCustomer(Login login) {
		Customer customer = new Customer();
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
