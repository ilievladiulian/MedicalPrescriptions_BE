package com.cfin.prescriptioninventory.repositories;

import com.cfin.prescriptioninventory.models.Prescription;
import com.cfin.prescriptioninventory.models.PrescriptionMedicine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionMedicineRepository extends CrudRepository<PrescriptionMedicine, Long> {
	List<PrescriptionMedicine> findByPrescription(Prescription prescription);
}
