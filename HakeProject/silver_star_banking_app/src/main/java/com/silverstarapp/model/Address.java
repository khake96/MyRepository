package com.silverstarapp.model;

/* This Address class file contains the data required for a Silver
 * Star Banking customer address. The data for each transaction is stored in
 * the database.
 */

public class Address {
	
	private int houseNo;
	private String streetName;
	private String secondLine;
	private String city;
	private String state;
	private String zip;

	
	public Address() {
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
	
	// Provided so that test cases can be run comparing objects
	@Override
    public boolean equals(Object obj){
        Address x = (Address) obj;
        boolean status = false;
        if(this.houseNo == x.houseNo
                && this.streetName.equals(x.streetName) 
                && this.city.equals(x.city) ){
            status = true;
        }
        return status;
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
		return houseNo +" "+ streetName + "\n "
				+ secondLine + "\n " +
				city + ", " + state + ", " + zip;
	}
	
	
	

}
