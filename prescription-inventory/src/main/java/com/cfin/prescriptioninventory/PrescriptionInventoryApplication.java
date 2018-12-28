package com.cfin.prescriptioninventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class PrescriptionInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrescriptionInventoryApplication.class, args);
	}

}

