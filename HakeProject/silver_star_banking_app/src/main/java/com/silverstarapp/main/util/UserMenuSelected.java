package com.silverstarapp.main.util;

/* This User Menu app is stores some of the lengthy menu options
 * required for the Users of the Silver Star Banking app who are 
 * not customers or employees.
 */

public class UserMenuSelected {

	private String firstName = 		"\n\n     Please Enter all required data. Follow entry with *Enter*\n"
			+ 						"     ============================================================= \n\n"
			+ 						"    1) First Name (required): ";
	private String lastName = 		"    2) Last Name (required): ";
	private String userName =		"    3) Silver Star Username (required): ";
	private String password =		"    4) Silver Star Password (required): ";
	private String contact =		"    5) Phone contact eg. 1231234567 (optional): ";     
	private String houseNumber =  	"    6) House Number (required): ";      
	private String streetName = 	"    7) Street Name (required): ";       
	private String secondLine =		"    8) Second Address Line (optional): ";
	private String city = 			"    9) City (required): ";
	private String state = 			"    10) State eg. TN (required): ";       
	private String zip = 			"    11) ZIP code (required): ";     

public UserMenuSelected() {
	super();
	firstName = 		"\n\n     Please Enter all required data. Follow entry with *Enter*\n"
			+ 						"     ============================================================= \n\n"
			+ 						"    1) First Name (required): ";
	lastName = 		"    2) Last Name (required): ";
	userName =		"    3) Silver Star Username (required): ";
	password =		"    4) Silver Star Password (required): ";
	contact =		"    5) Phone contact eg. 1231234567 (optional): ";     
	houseNumber =  	"    6) House Number (required): ";      
	streetName = 	"    7) Street Name (required): ";       
	secondLine =		"    8) Second Address Line (optional): ";
	city = 			"    9) City (required): ";
	state = 			"    10) State eg. TN (required): ";       
	zip = 			"    11) ZIP code (required): ";  
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

public String getContact() {
	return contact;
}

public void setContact(String contact) {
	this.contact = contact;
}

public String getHouseNumber() {
	return houseNumber;
}

public void setHouseNumber(String houseNumber) {
	this.houseNumber = houseNumber;
}

public String getStreetName() {
	return streetName;
}

public void setStreetName(String streetName) {
	this.streetName = streetName;
}

public String getSecondLine() {
	return secondLine;
}

public void setSecondLine(String secondLine) {
	this.secondLine = secondLine;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

public String getZip() {
	return zip;
}

public void setZip(String zip) {
	this.zip = zip;
}

}
