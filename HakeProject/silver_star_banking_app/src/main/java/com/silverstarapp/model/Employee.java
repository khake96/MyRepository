package com.silverstarapp.model;

import com.silverstarapp.dao.EmployeeDAO;
import com.silverstarapp.dao.impl.EmpoloyeeDaoImpl;

/* This Employee class file contains the data required for a Silver
 * Star Banking employee. The data for each instance is stored in
 * the database.
 */

public class Employee {
	
	EmployeeDAO employeeDAO = new EmpoloyeeDaoImpl();

	
	private int id;
	private String firstName;
	private String lastName;
	private String contact;
	
	public Employee (int id, String firstName, String lastName, String contact) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
	}
	
	public Employee() {
		super();	
	}
	
	// Provided so that test cases can be run comparing objects
	@Override
    public boolean equals(Object obj){
        Employee x = (Employee) obj;
        boolean status = false;
        if(this.id == x.id
                && this.firstName.equals(x.firstName) 
                && this.lastName.equals(x.lastName) ){
            status = true;
        }
        return status;
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

	public void setId(int id) {
		this.id = id;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Employee [Employee ID = " + id + ", First Name = "
	    + firstName + " Last Name = " + lastName + " Contact = " 
		+ contact + "]";
	}
	
	

}
