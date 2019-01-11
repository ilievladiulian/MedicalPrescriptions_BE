package com.cfin.prescriptioninventory.repositories;

import com.cfin.prescriptioninventory.models.Prescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
	List<Prescription> findByClientEmail(String clientEmail);
}
