package com.cfin.prescriptioninventory.dtos;

import com.cfin.prescriptioninventory.models.Medicine;

public class MedicineWithQuantity {
	private Medicine medicine;
	private Integer quantity;

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
