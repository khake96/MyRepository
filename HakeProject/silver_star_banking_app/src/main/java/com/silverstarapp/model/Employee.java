package com.silverstarapp.model;

import com.silverstarapp.dao.employee.EmployeeDAO;
import com.silverstarapp.dao.impl.employee.EmpoloyeeDaoImpl;
import com.silverstarapp.exception.BusinessException;

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
		// TODO Auto-generated constructor stub
	}
	
//	public Employee setEmployee(int employeeID) throws BusinessException {
//		// pass login to DAO to fill from DB
//		employee = employeeDAO.setEmployee(employeeID);		
//		return employee;
//	}


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
