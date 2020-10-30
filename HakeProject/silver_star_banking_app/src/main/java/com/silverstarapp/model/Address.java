package com.silverstarapp.model;

public class Address {
	
	private int houseNo;
	private String streetName;
	private String secondLine;
	private String city;
	private String state;
	private String zip;

	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Address(int houseNo, String streetName, String secondLine, String city, String state, String zip) {
		super();
		this.houseNo = houseNo;
		this.streetName = streetName;
		this.secondLine = secondLine;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}


	public int getHouseNo() {
		return houseNo;
	}


	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
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


	@Override
	public String toString() {
		return houseNo + streetName + "\n "
				+ secondLine + "/n " +
				city + ", " + state + ", " + zip;
	}
	
	
	

}
