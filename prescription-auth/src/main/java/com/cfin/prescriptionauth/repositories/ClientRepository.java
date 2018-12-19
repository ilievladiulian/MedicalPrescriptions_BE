package com.cfin.prescriptionauth.repositories;

import com.cfin.prescriptionauth.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
