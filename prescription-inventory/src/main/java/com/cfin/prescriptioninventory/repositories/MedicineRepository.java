package com.cfin.prescriptioninventory.repositories;

import com.cfin.prescriptioninventory.models.Medicine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Long> {
	Medicine findByName(String medicineName);

	Medicine findByNameContainingIgnoreCase(String medicineName);
}
