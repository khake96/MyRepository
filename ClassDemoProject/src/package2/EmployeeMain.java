package package2;

import package1.Address;

public class EmployeeMain extends Address {

	public static void main(String[] args) {
		Address a1 = new Address(123,"Oak Lane","","Oakland","CA",32780);
		Address.printAddress(a1);	

	}

}
