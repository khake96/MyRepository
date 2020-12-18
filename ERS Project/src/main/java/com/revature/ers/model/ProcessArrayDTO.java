package com.revature.ers.model;

import java.util.ArrayList;
import java.util.List;


public class ProcessArrayDTO {
	
	public ArrayList<ProcessRequestDTO> processArray;

	public ProcessArrayDTO(ArrayList<ProcessRequestDTO> processArray) {
		super();
		this.processArray = processArray;
	}

	public ProcessArrayDTO() {
		super();
	}

	public List<ProcessRequestDTO> getProcessArray() {
		return processArray;
	}

	public void setProcessArray(ArrayList<ProcessRequestDTO> processArray) {
		this.processArray = processArray;
	}

}