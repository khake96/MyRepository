package com.revature.ers.model;

import java.sql.Timestamp;

public class PendingDTO {
	
	public int reimbId;
	public double reimbAmount;
	public Timestamp requestDate;
	public String reimbDescription;
	public int reimbAuthor;
	public int reimbTypeId;
	public String last_name;
	public String first_name;
	
	public PendingDTO(int reimbId, double reimbAmount, Timestamp requestDate, String reimbDescription, int reimbAuthor,
			int reimbTypeId, String last_name, String first_name) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.requestDate = requestDate;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbTypeId = reimbTypeId;
		this.last_name = last_name;
		this.first_name = first_name;
	}

	public PendingDTO() {
		super();
	}

	public int getReimbId() {
		return reimbId;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public Timestamp getRequestDate() {
		return requestDate;
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

	public String getLast_name() {
		return last_name;
	}

	public String getFirst_name() {
		return first_name;
	}
	
	
	
	

}
