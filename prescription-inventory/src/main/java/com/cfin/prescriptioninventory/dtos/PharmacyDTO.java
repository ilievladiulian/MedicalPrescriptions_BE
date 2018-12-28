package com.cfin.prescriptioninventory.dtos;

import java.util.List;

public class PharmacyDTO {
	private String name;
	private String address;
	private Integer openAt;
	private Integer closeAt;

	private List<MedicineDTO> medicineList;

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

	public List<MedicineDTO> getMedicineList() {
		return medicineList;
	}

	public void setMedicineList(List<MedicineDTO> medicineList) {
		this.medicineList = medicineList;
	}
}
