package com.silverstarapp.exception;

public class BusinessException extends Exception {

	/* This BusinessException class file stores constructors for 
	 * Silver Star App business exceptions. It is used at the 
	 * service layer to generate exceptions to pass back to the 
	 * user interface.
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public BusinessException() {
		super();
	}
	
	public BusinessException(final String arg0) {
		super(arg0);
	}
}
