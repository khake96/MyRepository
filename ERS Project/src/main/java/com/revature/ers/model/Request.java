package com.revature.ers.model;

import java.sql.Blob;
import java.sql.Timestamp;

public class Request {
	
	private int reimbId;
	private double reimbAmount;
	private Timestamp processedDate;
	private Timestamp requestDate;
	private String reimbDescription;
	private Blob reimbReceipt;
	private int reimbAuthor;
	private int reimbResolver;
	private int reimbStatusId;
	private int reimbTypeId;
	
	public Request(int reimbId, double reimbAmount, Timestamp requestDate,
			String reimbDescription, int reimbAuthor, int reimbTypeId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.requestDate = requestDate;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbTypeId = reimbTypeId;
	}


	public Request(int reimbId, double reimbAmount, Timestamp processedDate, Timestamp requestDate,
			String reimbDescription, Blob reimbReceipt, int reimbAuthor, int reimbResolver, int reimbStatusId,
			int reimbTypeId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.processedDate = processedDate;
		this.requestDate = requestDate;
		this.reimbDescription = reimbDescription;
		this.reimbReceipt = reimbReceipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}
	


	
	public Request(double reimbAmount, Timestamp processedDate, Timestamp requestDate,
			String reimbDescription, Blob reimbReceipt, int reimbAuthor, int reimbResolver, int reimbStatusId,
			int reimbTypeId) {
		super();
		this.reimbAmount = reimbAmount;
		this.processedDate = processedDate;
		this.requestDate = requestDate;
		this.reimbDescription = reimbDescription;
		this.reimbReceipt = reimbReceipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}
	
	public Request(RequestDTO requestDTO) {
		super();
		this.reimbAmount = requestDTO.getReimbAmount();
		this.reimbDescription = requestDTO.getReimbDescription();
		this.reimbAuthor = requestDTO.getReimbAuthor();
		this.reimbTypeId = requestDTO.getReimbTypeId();
	}
	
	public Request() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((processedDate == null) ? 0 : processedDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reimbAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + reimbAuthor;
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbReceipt == null) ? 0 : reimbReceipt.hashCode());
		result = prime * result + reimbResolver;
		result = prime * result + reimbStatusId;
		result = prime * result + reimbTypeId;
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (processedDate == null) {
			if (other.processedDate != null)
				return false;
		} else if (!processedDate.equals(other.processedDate))
			return false;
		if (Double.doubleToLongBits(reimbAmount) != Double.doubleToLongBits(other.reimbAmount))
			return false;
		if (reimbAuthor != other.reimbAuthor)
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbReceipt == null) {
			if (other.reimbReceipt != null)
				return false;
		} else if (!reimbReceipt.equals(other.reimbReceipt))
			return false;
		if (reimbResolver != other.reimbResolver)
			return false;
		if (reimbStatusId != other.reimbStatusId)
			return false;
		if (reimbTypeId != other.reimbTypeId)
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		return true;
	}


	public int getReimbId() {
		return reimbId;
	}


	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}


	public double getReimbAmount() {
		return reimbAmount;
	}


	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}


	public Timestamp getProcessedDate() {
		return processedDate;
	}


	public void setProcessedDate(Timestamp processedDate) {
		this.processedDate = processedDate;
	}


	public Timestamp getRequestDate() {
		return requestDate;
	}


	public void setRequestDate(Timestamp lastRequestDate) {
		this.requestDate = lastRequestDate;
	}


	public String getReimbDescription() {
		return reimbDescription;
	}


	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}


	public Blob getReimbReceipt() {
		return reimbReceipt;
	}


	public void setReimbReceipt(Blob reimbReceipt) {
		this.reimbReceipt = reimbReceipt;
	}


	public int getReimbAuthor() {
		return reimbAuthor;
	}


	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}


	public int getReimbResolver() {
		return reimbResolver;
	}


	public void setReimbResolver(int reimbResolver) {
		this.reimbResolver = reimbResolver;
	}


	public int getReimbStatusId() {
		return reimbStatusId;
	}


	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}


	public int getReimbTypeId() {
		return reimbTypeId;
	}


	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	@Override
	public String toString() {
		return "Request [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", processedDate=" + processedDate
				+ ", requestDate=" + requestDate + ", reimbDescription=" + reimbDescription + ", reimbAuthor="
				+ reimbAuthor + ", reimbResolver=" + reimbResolver + ", reimbStatusId=" + reimbStatusId
				+ ", reimbTypeId=" + reimbTypeId + "]";
	}
	
	

}

