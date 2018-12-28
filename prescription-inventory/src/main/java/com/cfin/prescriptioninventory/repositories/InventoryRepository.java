package com.cfin.prescriptioninventory.repositories;

import com.cfin.prescriptioninventory.models.Inventory;
import com.cfin.prescriptioninventory.models.Pharmacy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
	List<Inventory> findByMedicineName(String medicineName);
	List<Inventory> findByPharmacy(Pharmacy pharmacy);
}
