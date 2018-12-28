package com.cfin.prescriptioninventory.services;

import com.cfin.prescriptioninventory.dtos.MedicineDTO;
import com.cfin.prescriptioninventory.dtos.PharmacyDTO;

import java.util.List;

public interface InventoryService {
	List<PharmacyDTO> getPharmaciesByMedicines(List<MedicineDTO> medicineDTOList);
	List<PharmacyDTO> getAllPharmacies();
	List<MedicineDTO> getAllMedicines();
}
