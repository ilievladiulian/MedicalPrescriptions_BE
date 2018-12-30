package com.cfin.prescriptioninventory.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class PrescriptionDTO {
	private Long id;
	private LocalDateTime creationDate;
	private List<MedicineWithQuantity> medicineList;
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public List<MedicineWithQuantity> getMedicineList() {
		return medicineList;
	}

	public void setMedicineList(List<MedicineWithQuantity> medicineList) {
		this.medicineList = medicineList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
