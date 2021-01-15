package com.silverstarapp.main.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/* This executable util class file stores constants and methods
 * used by the executable layer of the Silver Star App. 
 */

public class MainUtilProperties {
	
	public static final String FILE="C:\\Users\\kevin\\Documents\\Revature Training\\STS Workspaces\\Workspace"
			+ "\\myFirstProject\\silver_star_banking_app\\SilverStarBankingLogs.log";
	
	public final Date getTimeStamp() {
		Date d=new Date();	
		/*
		 * yyyy - year in 4 digit  , yy - last 2 digits of year
		 * MM - month in 2 digits, MMM - Shortname of the month, MMMM - Full month name
		 * dd - date
		 * hh - 12hr
		 * HH - 24hr
		 * mm - minutes
		 * ss - seconds
		 * a - am/pm
		 * z/Z - zone
		 * EEE - short name of the day, EEEE - full name of the day
		 */	
		SimpleDateFormat sdf=new SimpleDateFormat("EEEE dd.MM.yyyy hh:mm:ss z");
		sdf.format(d); //format takes the date obj and prints the formatted date as String		
		return d;	
	}
	
	public final Date getTimeStampShort() {
		Date d=new Date();	
		/*
		 * yyyy - year in 4 digit  , yy - last 2 digits of year
		 * MM - month in 2 digits, MMM - Shortname of the month, MMMM - Full month name
		 * dd - date
		 * hh - 12hr
		 * HH - 24hr
		 * mm - minutes
		 * ss - seconds
		 * a - am/pm
		 * z/Z - zone
		 * EEE - short name of the day, EEEE - full name of the day
		 */	
		SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yy hh:mm:ss ");
		sdf.format(d); //format takes the date obj and prints the formatted date as String		
		return d;	
	}
}
