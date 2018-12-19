package com.cfin.prescriptionauth.repositories;

import com.cfin.prescriptionauth.models.UserLogin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends CrudRepository<UserLogin, Long> {
	UserLogin findByReferencedUserUsernameAndToken(String username, String token);
}
