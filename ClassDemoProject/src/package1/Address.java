package package1;

public class Address {
	
	private int houseNum;
	private String streetName1;
	private String streetName2;
	private String city;
	private String state;
	private int zip;
	
	public Address(int houseNum, String streetName1, String streetName2, String city, String state, int zip) {

		this.houseNum = houseNum;
		this.streetName1 = streetName1;
		this.streetName2 = streetName2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public int getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(int houseNum) {
		this.houseNum = houseNum;
	}

	public String getStreetName1() {
		return streetName1;
	}

	public void setStreetName1(String streetName1) {
		this.streetName1 = streetName1;
	}

	public String getStreetName2() {
		return streetName2;
	}

	public void setStreetName2(String streetName2) {
		this.streetName2 = streetName2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	protected static void printAddress(Address a1) {
		System.out.println(a1.getHouseNum() + " " + a1.getStreetName1());
		if(a1.getStreetName2()!="") {
			System.out.println(a1.getStreetName2());
		}
		System.out.println(a1.getCity() + ", " + a1.getState() + " " + a1.getZip());

	}
	
	

}
