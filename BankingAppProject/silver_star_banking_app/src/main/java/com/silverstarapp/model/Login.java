package com.silverstarapp.model;

/* This Login class file contains the data required for a Silver
 * Star Banking employee or customer login. The data for each instance
 *  is stored in the database.
 */

public class Login {
	
	private String userName;
	private String password;
	
	
	
	public Login() {
		super();
	}
	
	public Login(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	// Provided so that test cases can be run comparing objects
	@Override
    public boolean equals(Object obj){
        Login x = (Login) obj;
        boolean status = false;
        if(this.userName.equals(x.userName) 
                && this.password.equals(x.password) ){
            status = true;
        }
        return status;
    }
	
	public String toString(char[] password) {
		String pWord = new String(password);
		return pWord;		
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

}
