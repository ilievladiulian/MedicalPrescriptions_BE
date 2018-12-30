package com.cfin.prescriptioninventory.controllers;

import com.cfin.prescriptioninventory.dtos.OperationResponse;
import com.cfin.prescriptioninventory.dtos.PrescriptionDTO;
import com.cfin.prescriptioninventory.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrescriptionController {
	@Autowired
	private PrescriptionService prescriptionService;

	@GetMapping("prescription")
	public List<PrescriptionDTO> getPrescriptions() {
		return this.prescriptionService.getAllPrescriptions();
	}

	@GetMapping("prescription/{id}")
	public PrescriptionDTO getPrescription(@PathVariable(name = "id") Long prescriptionId) {
		return this.prescriptionService.getPrescription(prescriptionId);
	}

	@PostMapping("prescription")
	public OperationResponse createPrescription(@RequestBody PrescriptionDTO prescription) {
		OperationResponse operationResponse = new OperationResponse();
		operationResponse.setOperationName("Create prescription");
		operationResponse.setSuccess(this.prescriptionService.createPrescription(prescription));
		return operationResponse;
	}

	@PutMapping("prescription")
	public OperationResponse updatePrescription(@RequestBody PrescriptionDTO prescription) {
		OperationResponse operationResponse = new OperationResponse();
		operationResponse.setOperationName("Update prescription");
		operationResponse.setSuccess(this.prescriptionService.updatePrescription(prescription));
		return operationResponse;
	}

	@DeleteMapping("prescription/{id}")
	public OperationResponse deletePrescription(@PathVariable(name = "id") Long prescriptionId) {
		OperationResponse operationResponse = new OperationResponse();
		operationResponse.setOperationName("Delete prescription");
		operationResponse.setSuccess(this.prescriptionService.deletePrescription(prescriptionId));
		return operationResponse;
	}
}
