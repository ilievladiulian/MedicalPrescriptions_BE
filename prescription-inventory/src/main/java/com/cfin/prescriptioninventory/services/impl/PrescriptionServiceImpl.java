package com.cfin.prescriptioninventory.services.impl;

import com.cfin.prescriptioninventory.dtos.MedicineWithQuantity;
import com.cfin.prescriptioninventory.dtos.PrescriptionDTO;
import com.cfin.prescriptioninventory.models.Medicine;
import com.cfin.prescriptioninventory.models.Prescription;
import com.cfin.prescriptioninventory.models.PrescriptionMedicine;
import com.cfin.prescriptioninventory.repositories.MedicineRepository;
import com.cfin.prescriptioninventory.repositories.PrescriptionMedicineRepository;
import com.cfin.prescriptioninventory.repositories.PrescriptionRepository;
import com.cfin.prescriptioninventory.services.PrescriptionService;
import com.cfin.prescriptioninventory.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Autowired
	private MedicineRepository medicineRepository;

	@Autowired
	private PrescriptionMedicineRepository prescriptionMedicineRepository;

	@Override
	public List<PrescriptionDTO> getAllPrescriptions() {
		List<Prescription> currentUserPrescriptions = this.prescriptionRepository.findByClientEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		List<PrescriptionDTO> response = new ArrayList<>();

		for (Prescription prescription : currentUserPrescriptions) {
			PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
			prescriptionDTO.setId(prescription.getId());
			prescriptionDTO.setCreationDate(prescription.getCreationDate());
			prescriptionDTO.setDescription(prescription.getDescription());
			prescriptionDTO.setMedicineList(Mapper.medicineList2MedicineWithQuantityList(this.prescriptionMedicineRepository.findByPrescription(prescription)));
			response.add(prescriptionDTO);
		}

		return response;
	}

	@Override
	public PrescriptionDTO getPrescription(Long prescriptionId) {
		Prescription prescription = this.prescriptionRepository.findById(prescriptionId).get();
		PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
		prescriptionDTO.setDescription(prescription.getDescription());
		prescriptionDTO.setCreationDate(prescription.getCreationDate());
		prescriptionDTO.setId(prescription.getId());
		prescriptionDTO.setMedicineList(Mapper.medicineList2MedicineWithQuantityList(this.prescriptionMedicineRepository.findByPrescription(prescription)));
		return prescriptionDTO;
	}

	@Override
	public boolean createPrescription(PrescriptionDTO prescriptionDTO) {
		Prescription prescription = new Prescription();
		prescription.setClientEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		prescription.setCreationDate(prescriptionDTO.getCreationDate());
		prescription.setDescription(prescriptionDTO.getDescription());
		Prescription savedPrescription = this.prescriptionRepository.save(prescription);
		if (savedPrescription == null) {
			throw new RuntimeException("Could not save prescription");
		}

		this.saveMedicineListOfPrescription(savedPrescription, prescriptionDTO);

		return true;
	}

	@Override
	public boolean updatePrescription(PrescriptionDTO prescriptionDTO) {
		Prescription prescription = new Prescription();
		prescription.setClientEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		prescription.setCreationDate(prescriptionDTO.getCreationDate());
		prescription.setDescription(prescriptionDTO.getDescription());
		Prescription savedPrescription = this.prescriptionRepository.save(prescription);
		if (savedPrescription == null) {
			throw new RuntimeException("Could not update prescription");
		}

		this.saveMedicineListOfPrescription(savedPrescription, prescriptionDTO);

		return true;
	}

	@Override
	public boolean deletePrescription(Long prescriptionId) {
		List<PrescriptionMedicine> prescriptionMedicines = this.prescriptionMedicineRepository.findByPrescription(this.prescriptionRepository.findById(prescriptionId).get());
		this.prescriptionMedicineRepository.deleteAll(prescriptionMedicines);

		this.prescriptionRepository.delete(this.prescriptionRepository.findById(prescriptionId).get());

		return true;
	}

	private void saveMedicineListOfPrescription(Prescription savedPrescription, PrescriptionDTO prescriptionDTO) {
		for (MedicineWithQuantity medicineWithQuantity : prescriptionDTO.getMedicineList()) {
			Medicine medicineInPrescription = this.medicineRepository.findByName(medicineWithQuantity.getMedicine().getName());
			PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine();
			prescriptionMedicine.setQuantity(medicineWithQuantity.getQuantity());
			prescriptionMedicine.setMedicine(medicineInPrescription);
			prescriptionMedicine.setPrescription(savedPrescription);
			PrescriptionMedicine savedPrescriptionMedicine = this.prescriptionMedicineRepository.save(prescriptionMedicine);
			if (savedPrescriptionMedicine == null) {
				throw new RuntimeException("Could not save medicine for prescription");
			}
		}
	}
}
