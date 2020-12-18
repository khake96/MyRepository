package com.revature.ers.model;

public class ProcessRequestDTO {
	
		public boolean isApproved;
		public int requestId;
		
		public ProcessRequestDTO(boolean isApproved, int requestId) {
			super();
			this.isApproved = isApproved;
			this.requestId = requestId;
		}

		public ProcessRequestDTO() {
			super();
		}

		public boolean isApproved() {
			return isApproved;
		}

		public int getReimbId() {
			return requestId;
		}

		public void setApproved(boolean isApproved) {
			this.isApproved = isApproved;
		}

		public void setReimbId(int reimbId) {
			this.requestId = reimbId;
		}
		
}

