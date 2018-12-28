package com.cfin.prescriptioninventory.services.impl;

import com.cfin.prescriptioninventory.dtos.MedicineDTO;
import com.cfin.prescriptioninventory.dtos.PharmacyDTO;
import com.cfin.prescriptioninventory.models.Inventory;
import com.cfin.prescriptioninventory.models.Medicine;
import com.cfin.prescriptioninventory.models.Pharmacy;
import com.cfin.prescriptioninventory.repositories.InventoryRepository;
import com.cfin.prescriptioninventory.repositories.MedicineRepository;
import com.cfin.prescriptioninventory.repositories.PharmacyRepository;
import com.cfin.prescriptioninventory.services.InventoryService;
import com.cfin.prescriptioninventory.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private PharmacyRepository pharmacyRepository;

	@Override
	public List<PharmacyDTO> getPharmaciesByMedicines(List<MedicineDTO> medicineDTOList) {
		List<PharmacyDTO> topMatchingPharmacies = new ArrayList<>();
		Map<Long, List<MedicineDTO>> pharmacyMedicineMap = this.getPharmaciesThatContainMedicinesFromList(medicineDTOList);

		int numberOfIterations = 5;
		if (pharmacyMedicineMap.keySet().size() < 5) {
			numberOfIterations = pharmacyMedicineMap.keySet().size();
		}
		for (int i = 0; i < numberOfIterations; i++) {
			Pharmacy wantedPharmacy = getPharmacyWithMaxNumberOfWantedMedicines(pharmacyMedicineMap);
			topMatchingPharmacies.add(Mapper.pharmacy2PharmacyDTO(wantedPharmacy, pharmacyMedicineMap.get(wantedPharmacy.getId())));
			pharmacyMedicineMap.remove(wantedPharmacy.getId());
		}

		return topMatchingPharmacies;
	}

	private Map<Long, List<MedicineDTO>> getPharmaciesThatContainMedicinesFromList(List<MedicineDTO> medicineDTOList) {
		Map<Long, List<MedicineDTO>> pharmacyMedicineMap = new HashMap<>();

		for (MedicineDTO medicineDTO : medicineDTOList) {
			List<Inventory> inventoryList = this.inventoryRepository.findByMedicineName(medicineDTO.getName());
			inventoryList.forEach(inventory -> {
				Long pharmacyId = inventory.getPharmacy().getId();
				if (pharmacyMedicineMap.containsKey(inventory.getPharmacy().getId())) {
					pharmacyMedicineMap.get(pharmacyId).add(medicineDTO);
					pharmacyMedicineMap.put(pharmacyId, pharmacyMedicineMap.get(pharmacyId));
				} else {
					List<MedicineDTO> medicineInInventoryList = new ArrayList<>();
					medicineInInventoryList.add(medicineDTO);
					pharmacyMedicineMap.put(pharmacyId, medicineInInventoryList);
				}
			});
		}

		return pharmacyMedicineMap;
	}

	private Pharmacy getPharmacyWithMaxNumberOfWantedMedicines(Map<Long, List<MedicineDTO>> pharmacyMedicineMap) {
		int maxNoOfMedicines = 0;
		Long keyOfMaxNoOfMedicines = null;
		for (Map.Entry<Long, List<MedicineDTO>> pharmacyMedicineEntry : pharmacyMedicineMap.entrySet()) {
			if (pharmacyMedicineEntry.getValue().size() > maxNoOfMedicines) {
				maxNoOfMedicines = pharmacyMedicineEntry.getValue().size();
				keyOfMaxNoOfMedicines = pharmacyMedicineEntry.getKey();
			}
		}
		return this.pharmacyRepository.findById(keyOfMaxNoOfMedicines).get();
	}

	@Override
	public List<PharmacyDTO> getAllPharmacies() {
		List<PharmacyDTO> pharmacies = new ArrayList<>();
		this.pharmacyRepository.findAll().forEach(pharmacy -> {
			List<Medicine> medicines = new ArrayList<>();
			this.inventoryRepository.findByPharmacy(pharmacy).forEach(inventory -> medicines.add(inventory.getMedicine()));
			pharmacies.add(Mapper.pharmacy2PharmacyDTO(pharmacy, Mapper.medicineList2MedicineDTOList(medicines)));
		});
		return pharmacies;
	}

	@Override
	public List<MedicineDTO> getAllMedicines() {
		List<MedicineDTO> medicineDTOList = new ArrayList<>();
		this.medicineRepository.findAll().forEach(medicine -> medicineDTOList.add(Mapper.medicine2MedicineDTO(medicine)));
		return medicineDTOList;
	}
}
