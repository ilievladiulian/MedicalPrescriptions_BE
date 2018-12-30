package com.cfin.prescriptioninventory.services;

import com.cfin.prescriptioninventory.dtos.PrescriptionDTO;

import java.util.List;

public interface PrescriptionService {
	List<PrescriptionDTO> getAllPrescriptions();
	PrescriptionDTO getPrescription(Long prescriptionId);
	boolean createPrescription(PrescriptionDTO prescription);
	boolean updatePrescription(PrescriptionDTO prescription);
	boolean deletePrescription(Long prescriptionId);
}
