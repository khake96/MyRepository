package com.silverstarapp.main;

import java.io.Console;
import java.util.Scanner;

import com.silverstarapp.main.util.PresentationLevelPrinter;
import com.silverstarapp.model.Address;
import com.silverstarapp.model.Login;
import com.silverstarapp.model.User;

 class MenuConsoles {


   int topLevelMenu() {
    	PresentationLevelPrinter.printSilverStarLogo();
    	PresentationLevelPrinter.printRootMenu();
        int selection = 0;
        Scanner scanner = new Scanner(System.in);
        	selection = Integer.parseInt(scanner.nextLine());
        	scanner.close();
        return selection;
  }

     Login loginMenu() {

        PresentationLevelPrinter.printLoginMenu(); 
        Login login = new Login();
        Scanner scanner1 = new Scanner(System.in);       
        String username = scanner1.nextLine();
        System.out.println(username);
        // returns login object with null values if user desires to return to top menu
		if(username.equals("")) {
			return login;
		} 
		login.setUserName(username);
		login.setPassword(scanner1.nextLine().toCharArray());	
        return login;
    }
    
     User newUserMenu() {
         PresentationLevelPrinter.printNewUserMenu(); 
         User user = new User();  
         Address address = new Address();
         Console scanner = null;
         String password = null;
         
         user.setFirstName(scanner.readLine());
         System.out.println("    2) Last Name (required): ");
         user.setLastName(scanner.readLine());
         System.out.println("    3) Silver Star Username (required): ");
         user.setUserName(scanner.readLine());
         System.out.println("    4) Silver Star Password (required): ");
         user.setPassword(scanner.readPassword());
         System.out.println("    5) Phone contact eg. +11231234567 (optional): ");
         user.setContact(Long.parseLong(scanner.readLine()));         
         System.out.println("    6) House Number (required): ");
         address.setHouseNo(Integer.parseInt(scanner.readLine()));
         System.out.println("    7) Street Name (required): ");
         address.setStreetName(scanner.readLine());   
         System.out.println("    8) Second Address Line (optional): ");
         address.setSecondLine(scanner.readLine());
         System.out.println("    9) City (required): ");
         address.setCity(scanner.readLine()); 
         System.out.println("    10) State eg. TN (required): ");
         address.setState(scanner.readLine()); 
         System.out.println("    11) ZIP code (required): ");
         address.setZip(scanner.readLine());
         user.setAddress(address);
         return user; 

     }
    
}