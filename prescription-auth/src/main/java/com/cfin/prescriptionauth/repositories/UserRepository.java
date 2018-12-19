package com.cfin.prescriptionauth.repositories;

import com.cfin.prescriptionauth.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
}
