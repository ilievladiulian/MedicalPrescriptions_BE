package com.cfin.prescriptionauth.services;

import com.cfin.prescriptionauth.dtos.AuthenticatedUserDTO;
import com.cfin.prescriptionauth.dtos.ClientDTO;
import com.cfin.prescriptionauth.dtos.UserDTO;

import java.text.ParseException;

public interface UserService {
	AuthenticatedUserDTO authenticateUser(UserDTO userDTO);
	boolean authorizeUser(AuthenticatedUserDTO authenticatedUserDTO) throws ParseException;
	boolean registerNewUser(ClientDTO clientDTO);
}
