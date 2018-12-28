package com.cfin.prescriptioninventory.dtos;

import java.time.LocalDate;
import java.util.Date;

public class MedicineDTO {
	private String name;
	private String activeSubstance;
	private Integer activeQuantity;
	private LocalDate productionDate;
	private LocalDate expiryDate;
	private String details;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActiveSubstance() {
		return activeSubstance;
	}

	public void setActiveSubstance(String activeSubstance) {
		this.activeSubstance = activeSubstance;
	}

	public Integer getActiveQuantity() {
		return activeQuantity;
	}

	public void setActiveQuantity(Integer activeQuantity) {
		this.activeQuantity = activeQuantity;
	}

	public LocalDate getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(LocalDate productionDate) {
		this.productionDate = productionDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
