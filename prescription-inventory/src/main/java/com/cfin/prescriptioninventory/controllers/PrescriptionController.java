package com.cfin.prescriptioninventory.controllers;

import com.cfin.prescriptioninventory.dtos.OperationResponse;
import com.cfin.prescriptioninventory.dtos.PrescriptionDTO;
import com.cfin.prescriptioninventory.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrescriptionController {
	@Autowired
	private PrescriptionService prescriptionService;

	@GetMapping("prescriptions")
	@CrossOrigin
	public List<PrescriptionDTO> getPrescriptions() {
		return this.prescriptionService.getAllPrescriptions();
	}

	@GetMapping("prescriptions/{id}")
	@CrossOrigin
	public PrescriptionDTO getPrescription(@PathVariable(name = "id") Long prescriptionId) {
		return this.prescriptionService.getPrescription(prescriptionId);
	}

	@PostMapping("prescriptions")
	@CrossOrigin
	public OperationResponse createPrescription(@RequestBody PrescriptionDTO prescription) {
		this.validateCreatePrescription(prescription);
		OperationResponse operationResponse = new OperationResponse();
		operationResponse.setOperationName("Create prescription");
		operationResponse.setSuccess(this.prescriptionService.createPrescription(prescription));
		return operationResponse;
	}

	@PutMapping("prescriptions")
	@CrossOrigin
	public OperationResponse updatePrescription(@RequestBody PrescriptionDTO prescription) {
		this.validateUpdatePrescription(prescription);
		OperationResponse operationResponse = new OperationResponse();
		operationResponse.setOperationName("Update prescription");
		operationResponse.setSuccess(this.prescriptionService.updatePrescription(prescription));
		return operationResponse;
	}

	@DeleteMapping("prescriptions/{id}")
	@CrossOrigin
	public OperationResponse deletePrescription(@PathVariable(name = "id") Long prescriptionId) {
		OperationResponse operationResponse = new OperationResponse();
		operationResponse.setOperationName("Delete prescription");
		operationResponse.setSuccess(this.prescriptionService.deletePrescription(prescriptionId));
		return operationResponse;
	}

	private void validateUpdatePrescription(PrescriptionDTO prescriptionDTO) {
		if (StringUtils.isEmpty(prescriptionDTO.getMedic())) {
			throw new RuntimeException("Prescription must have a medic");
		}
		if (prescriptionDTO.getId() == null) {
			throw new RuntimeException("Cannot update prescription without an id");
		}
		if (prescriptionDTO.getCreationDate() == null) {
			throw new RuntimeException("Prescription must have a creation date");
		}
	}

	private void validateCreatePrescription(PrescriptionDTO prescriptionDTO) {
		if (StringUtils.isEmpty(prescriptionDTO.getMedic())) {
			throw new RuntimeException("Prescription must have a medic");
		}
		if (prescriptionDTO.getCreationDate() == null) {
			throw new RuntimeException("Prescription must have a creation date");
		}
	}
}
