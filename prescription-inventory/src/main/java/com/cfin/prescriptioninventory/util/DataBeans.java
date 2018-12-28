package com.cfin.prescriptioninventory.util;

import com.cfin.prescriptioninventory.models.Inventory;
import com.cfin.prescriptioninventory.models.Medicine;
import com.cfin.prescriptioninventory.models.Pharmacy;
import com.cfin.prescriptioninventory.repositories.InventoryRepository;
import com.cfin.prescriptioninventory.repositories.MedicineRepository;
import com.cfin.prescriptioninventory.repositories.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class DataBeans {
	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private PharmacyRepository pharmacyRepository;

	@PostConstruct
	public void init() {
		this.initMedicine();
		this.initPharmacy();
		this.initInventory();
	}

	private void initMedicine() {
		Medicine medicine = new Medicine();
		medicine.setActiveQuantity(200);
		medicine.setActiveSubstance("ibuprofen");
		medicine.setDetails("Pentru durere");
		medicine.setName("Nurofen");
		medicine.setExpiryDate(LocalDate.of(2020, 10, 23));
		medicine.setProductionDate(LocalDate.of(2017, 10, 23));
		this.medicineRepository.save(medicine);

		medicine = new Medicine();
		medicine.setName("Brufen");
		medicine.setActiveQuantity(400);
		medicine.setActiveSubstance("ibuprofen");
		medicine.setDetails("Pentru durere");
		medicine.setExpiryDate(LocalDate.of(2020, 10, 23));
		medicine.setProductionDate(LocalDate.of(2017, 10, 23));
		this.medicineRepository.save(medicine);

		medicine = new Medicine();
		medicine.setName("Paracetamol");
		medicine.setActiveSubstance("Paracetamol");
		medicine.setDetails("Pentru durere");
		medicine.setExpiryDate(LocalDate.of(2020, 10, 23));
		medicine.setProductionDate(LocalDate.of(2017, 10, 23));
		medicine.setActiveQuantity(400);
		this.medicineRepository.save(medicine);
	}

	private void initPharmacy() {
		Pharmacy pharmacy = new Pharmacy();
		pharmacy.setAddress("aaaaa");
		pharmacy.setName("Catena");
		pharmacy.setOpenAt(9);
		pharmacy.setCloseAt(18);
		this.pharmacyRepository.save(pharmacy);

		pharmacy = new Pharmacy();
		pharmacy.setOpenAt(10);
		pharmacy.setCloseAt(21);
		pharmacy.setName("Dona");
		pharmacy.setAddress("bbbb");
		this.pharmacyRepository.save(pharmacy);
	}

	private void initInventory() {
		Pharmacy catena = this.pharmacyRepository.findByNameAndAddress("Catena", "aaaaa");
		Pharmacy dona = this.pharmacyRepository.findByNameAndAddress("Dona", "bbbb");
		Inventory inventory = new Inventory();
		inventory.setMedicine(this.medicineRepository.findByName("Paracetamol"));
		inventory.setPharmacy(catena);
		inventory.setQuantity(100);
		this.inventoryRepository.save(inventory);

		inventory = new Inventory();
		inventory.setPharmacy(dona);
		inventory.setQuantity(100);
		inventory.setMedicine(this.medicineRepository.findByName("Paracetamol"));
		this.inventoryRepository.save(inventory);

		inventory = new Inventory();
		inventory.setMedicine(this.medicineRepository.findByName("Nurofen"));
		inventory.setPharmacy(catena);
		inventory.setQuantity(100);
		this.inventoryRepository.save(inventory);

		inventory = new Inventory();
		inventory.setPharmacy(dona);
		inventory.setQuantity(100);
		inventory.setMedicine(this.medicineRepository.findByName("Nurofen"));
		this.inventoryRepository.save(inventory);

		inventory = new Inventory();
		inventory.setMedicine(this.medicineRepository.findByName("Brufen"));
		inventory.setQuantity(100);
		inventory.setPharmacy(catena);
		this.inventoryRepository.save(inventory);
	}
}
