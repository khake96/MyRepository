package com.revature.ers.model;

public class RequestDTO {
	
	public double reimbAmount;
	public String reimbDescription;
	public int reimbAuthor;
	public int reimbTypeId;
//	public InputStream reimbReceipt;

	
	public RequestDTO(double reimbAmount, String reimbDescription, int reimbAuthor, int reimbTypeId) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbTypeId = reimbTypeId;
//		this.reimbReceipt = reimbReceipt;
	}

	public RequestDTO() {
		super();
	}

	@Override
	public String toString() {
		return "RequestDTO [reimbAmount=" + reimbAmount + ", reimbDescription=" + reimbDescription + ", reimbAuthor="
				+ reimbAuthor + ", reimbTypeId=" + reimbTypeId + "Receipt file: "+"]";
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public int getReimbAuthor() {
		return reimbAuthor;
	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

//	public InputStream getReceipt() {
//		return reimbReceipt;
//	}
//
//	public void setReceipt(InputStream receipt) {
//		this.reimbReceipt = receipt;
//	}
	

}
