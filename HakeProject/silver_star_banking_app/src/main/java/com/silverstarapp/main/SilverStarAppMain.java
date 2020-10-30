package com.silverstarapp.main;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.silverstarapp.exception.BusinessException;
import com.silverstarapp.main.util.PresentationLevelPrinter;
import com.silverstarapp.model.Login;
import com.silverstarapp.model.User;

public class SilverStarAppMain {

	public static void main(String[] args) {
		
		int selection = 0;

		MenuConsoles menu = new MenuConsoles();
		MenuConsoles loginMenu = new MenuConsoles();
		// Top Level Menu Selection
		selection = menu.topLevelMenu();
		System.out.println(selection); 
		switch (selection) {
		// Entering login menu 
			case 1: {
				Login login = loginMenu.loginMenu();
				try {
					if (login.isBusinessValid()) {
						System.out.println("Valid login, passing to business."); 
					}
				} catch (BusinessException e) {
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
				break;
			}
			case 2: {
				User user = menu.newUserMenu();
				if (user.isBusinessValid(user)) {
					System.out.println("Valid user, passing to business level");
				}
			}
			case  3:		
				// User chooses to exit Silver Star app 
				PresentationLevelPrinter.printSilverStarExit();
				break;
			default:
				System.out.println("So sorry! We have encountered issues with your input. "
						+ "\nPlease try again.");
		}
	}
}


