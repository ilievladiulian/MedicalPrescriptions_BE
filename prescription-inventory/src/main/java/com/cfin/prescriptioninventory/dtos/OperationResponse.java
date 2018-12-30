package com.cfin.prescriptioninventory.dtos;

public class OperationResponse {
	private Boolean success;
	private String operationName;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
}
