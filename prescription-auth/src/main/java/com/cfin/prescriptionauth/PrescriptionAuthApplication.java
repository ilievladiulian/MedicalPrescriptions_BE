package com.cfin.prescriptionauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class PrescriptionAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrescriptionAuthApplication.class, args);
	}

}

