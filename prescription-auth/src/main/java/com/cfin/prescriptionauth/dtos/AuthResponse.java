package com.cfin.prescriptionauth.dtos;

public class AuthResponse {
	private String username;
	private Boolean authorized;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getAuthorized() {
		return authorized;
	}

	public void setAuthorized(Boolean authorized) {
		this.authorized = authorized;
	}
}
