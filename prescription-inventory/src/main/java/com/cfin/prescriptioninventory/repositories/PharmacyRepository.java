package com.cfin.prescriptioninventory.repositories;

import com.cfin.prescriptioninventory.models.Pharmacy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends CrudRepository<Pharmacy, Long> {
	Pharmacy findByNameAndAddress(String name, String address);
}
