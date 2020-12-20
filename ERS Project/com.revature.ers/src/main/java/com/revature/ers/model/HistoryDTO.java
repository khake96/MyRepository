package com.revature.ers.model;

import java.sql.Timestamp;

public class HistoryDTO {	
	
	public int reimbId;
	public double reimbAmount;
	public Timestamp processedDate;
	public Timestamp requestDate;
	public String reimbDescription;
	public int reimbAuthor;
	public int reimbResolver;
	public int reimbStatusId;
	public int reimbTypeId;
	public String author_last_name;
	public String author_first_name;
	public String process_first_name;
	public String process_last_name;
	
	public HistoryDTO(int reimbId, double reimbAmount, Timestamp processedDate, Timestamp requestDate,
			String reimbDescription, int reimbAuthor, int reimbResolver, int reimbStatusId, int reimbTypeId,
			String author_last_name, String author_first_name, String process_first_name, String process_last_name) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.processedDate = processedDate;
		this.requestDate = requestDate;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.author_last_name = author_last_name;
		this.author_first_name = author_first_name;
		this.process_first_name = process_first_name;
		this.process_last_name = process_last_name;
	}
	
	public HistoryDTO(int reimbId, double reimbAmount, Timestamp processedDate, Timestamp requestDate,
			String reimbDescription, int reimbAuthor, int reimbResolver, int reimbStatusId, int reimbTypeId,
			String author_last_name, String author_first_name) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.processedDate = processedDate;
		this.requestDate = requestDate;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.author_last_name = author_last_name;
		this.author_first_name = author_first_name;
	}
	
	public HistoryDTO(int reimbId, String process_last_name, String process_first_name) {
		super();
		this.reimbId = reimbId;
		this.process_last_name = process_last_name;
		this.process_first_name = process_first_name;
	}

	public HistoryDTO() {
		super();
	}

	public int getReimbId() {
		return reimbId;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public Timestamp getProcessedDate() {
		return processedDate;
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

	public int getReimbResolver() {
		return reimbResolver;
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

	public String getAuthor_last_name() {
		return author_last_name;
	}

	public String getAuthor_first_name() {
		return author_first_name;
	}

	public String getProcess_first_name() {
		return process_first_name;
	}

	public String getProcess_last_name() {
		return process_last_name;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public void setProcessedDate(Timestamp processedDate) {
		this.processedDate = processedDate;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}

	public void setReimbResolver(int reimbResolver) {
		this.reimbResolver = reimbResolver;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	public void setAuthor_last_name(String author_last_name) {
		this.author_last_name = author_last_name;
	}

	public void setAuthor_first_name(String author_first_name) {
		this.author_first_name = author_first_name;
	}

	public void setProcess_first_name(String process_first_name) {
		this.process_first_name = process_first_name;
	}

	public void setProcess_last_name(String process_last_name) {
		this.process_last_name = process_last_name;
	}
	
}
