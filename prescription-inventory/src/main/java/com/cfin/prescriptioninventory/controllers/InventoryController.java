package com.cfin.prescriptioninventory.controllers;

import com.cfin.prescriptioninventory.dtos.MedicineDTO;
import com.cfin.prescriptioninventory.dtos.PharmacyDTO;
import com.cfin.prescriptioninventory.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;

	@GetMapping("inventory/pharmacy")
	@CrossOrigin
	public List<PharmacyDTO> getAllPharmacies() {
		return this.inventoryService.getAllPharmacies();
	}

	@GetMapping("inventory/medicine")
	@CrossOrigin
	public List<MedicineDTO> getAllMedicines() {
		return this.inventoryService.getAllMedicines();
	}

	@PostMapping("inventory/pharmacy")
	@CrossOrigin
	public List<PharmacyDTO> getPharmaciesByMedicines(@RequestBody List<MedicineDTO> medicineDTOList) {
		return this.inventoryService.getPharmaciesByMedicines(medicineDTOList);
	}
}
