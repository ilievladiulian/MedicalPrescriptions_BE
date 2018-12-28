package com.cfin.prescriptioninventory.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pharmacy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String address;
	private Integer openAt;
	private Integer closeAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getOpenAt() {
		return openAt;
	}

	public void setOpenAt(Integer openAt) {
		this.openAt = openAt;
	}

	public Integer getCloseAt() {
		return closeAt;
	}

	public void setCloseAt(Integer closeAt) {
		this.closeAt = closeAt;
	}
}
