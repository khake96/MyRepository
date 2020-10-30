package com.silverstarapp.model;

import org.postgresql.util.PGTimestamp;

public class AccountApplication {
	
	private int applicationID;
	private Customer customer;
	private double startingBalance;
	private PGTimestamp ts = new PGTimestamp(applicationID, null);
}
