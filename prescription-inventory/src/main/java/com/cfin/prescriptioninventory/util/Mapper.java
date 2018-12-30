package com.cfin.prescriptioninventory.util;

import com.cfin.prescriptioninventory.dtos.MedicineDTO;
import com.cfin.prescriptioninventory.dtos.MedicineWithQuantity;
import com.cfin.prescriptioninventory.dtos.PharmacyDTO;
import com.cfin.prescriptioninventory.models.Medicine;
import com.cfin.prescriptioninventory.models.Pharmacy;
import com.cfin.prescriptioninventory.models.Prescription;
import com.cfin.prescriptioninventory.models.PrescriptionMedicine;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
	public static PharmacyDTO pharmacy2PharmacyDTO(Pharmacy pharmacy) {
		return pharmacy2PharmacyDTO(pharmacy, new ArrayList<>());
	}

	public static PharmacyDTO pharmacy2PharmacyDTO(Pharmacy pharmacy, List<MedicineDTO> medicineDTOList) {
		PharmacyDTO pharmacyDTO = new PharmacyDTO();
		pharmacyDTO.setAddress(pharmacy.getAddress());
		pharmacyDTO.setCloseAt(pharmacy.getCloseAt());
		pharmacyDTO.setOpenAt(pharmacy.getOpenAt());
		pharmacyDTO.setName(pharmacy.getName());
		pharmacyDTO.setMedicineList(medicineDTOList);
		return pharmacyDTO;
	}

	public static MedicineDTO medicine2MedicineDTO(Medicine medicine) {
		MedicineDTO medicineDTO = new MedicineDTO();
		medicineDTO.setActiveQuantity(medicine.getActiveQuantity());
		medicineDTO.setActiveSubstance(medicine.getActiveSubstance());
		medicineDTO.setDetails(medicine.getDetails());
		medicineDTO.setExpiryDate(medicine.getExpiryDate());
		medicineDTO.setProductionDate(medicine.getProductionDate());
		medicineDTO.setName(medicine.getName());
		return medicineDTO;
	}

	public static List<MedicineDTO> medicineList2MedicineDTOList(List<Medicine> medicines) {
		List<MedicineDTO> medicineDTOList = new ArrayList<>();
		medicines.forEach(medicine -> medicineDTOList.add(medicine2MedicineDTO(medicine)));
		return medicineDTOList;
	}

	public static List<MedicineWithQuantity> medicineList2MedicineWithQuantityList(List<PrescriptionMedicine> prescriptionMedicines) {
		List<MedicineWithQuantity> medicineWithQuantityList = new ArrayList<>();
		for (PrescriptionMedicine prescriptionMedicine : prescriptionMedicines) {
			MedicineWithQuantity medicine = new MedicineWithQuantity();
			medicine.setMedicine(prescriptionMedicine.getMedicine());
			medicine.setQuantity(prescriptionMedicine.getQuantity());
			medicineWithQuantityList.add(medicine);
		}
		return medicineWithQuantityList;
	}
}
