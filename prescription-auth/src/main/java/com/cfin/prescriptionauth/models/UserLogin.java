package com.cfin.prescriptionauth.models;

import javax.persistence.*;

@Entity
public class UserLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private User referencedUser;

	private String token;
	private String tokenExpireTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getReferencedUser() {
		return referencedUser;
	}

	public void setReferencedUser(User referencedUser) {
		this.referencedUser = referencedUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenExpireTime() {
		return tokenExpireTime;
	}

	public void setTokenExpireTime(String tokenExpireTime) {
		this.tokenExpireTime = tokenExpireTime;
	}
}
