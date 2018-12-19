package com.cfin.prescriptionauth.services;

import com.cfin.prescriptionauth.dtos.AuthenticatedUserDTO;
import com.cfin.prescriptionauth.dtos.ClientDTO;
import com.cfin.prescriptionauth.dtos.UserDTO;

public interface UserService {
	AuthenticatedUserDTO authenticateUser(UserDTO userDTO);
	boolean authorizeUser(AuthenticatedUserDTO authenticatedUserDTO);
	boolean registerNewUser(ClientDTO clientDTO);
}
