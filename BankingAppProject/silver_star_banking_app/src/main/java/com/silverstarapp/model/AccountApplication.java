package com.silverstarapp.model;

/* This AccountApplication class file the data required for a Silver
 * Star Banking account application. Available only to existing Silver
 * Star Banking customers.
 */

public class AccountApplication {
	
	private int applicationID;
	private int customerID;
	private double startingBalance;
	private double incomeGross;
	private double expenses;
	
	public AccountApplication(int applicationID, int customerID, double startingBalance, double incomeGross,
			double expenses) {
		super();
		this.applicationID = applicationID;
		this.customerID = customerID;
		this.startingBalance = startingBalance;
		this.incomeGross = incomeGross;
		this.expenses = expenses;
	}
	
	public AccountApplication(int customerID, double startingBalance, double incomeGross,
			double expenses) {
		super();
		this.customerID = customerID;
		this.startingBalance = startingBalance;
		this.incomeGross = incomeGross;
		this.expenses = expenses;
	}
	
	@Override
	public String toString() {
		return "\nApplicationID: " + applicationID + " Customer ID: " + customerID + 
				"\nStarting Balance: " + startingBalance + "\nGross Income: " + incomeGross +
				"\nAnnual Expenses:" + expenses +"\n";
	}
	
	// Provided so that test cases can be run comparing objects
	@Override
    public boolean equals(Object obj){
        AccountApplication application = (AccountApplication) obj;
        boolean status = false;
        if(this.applicationID == application.applicationID
                && this.startingBalance == (application.startingBalance)){
            status = true;
        }
        return status;
    }

	public int getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}

	public int getCustomer() {
		return customerID;
	}

	public void setCustomer(int customerID) {
		this.customerID = customerID;
	}

	public double getStartingBalance() {
		return startingBalance;
	}

	public void setStartingBalance(double startingBalance) {
		this.startingBalance = startingBalance;
	}

	public double getIncomeGross() {
		return incomeGross;
	}

	public void setIncomeGross(double incomeGross) {
		this.incomeGross = incomeGross;
	}

	public double getExpenses() {
		return expenses;
	}

	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

	public AccountApplication() {
		super();

	}

}
