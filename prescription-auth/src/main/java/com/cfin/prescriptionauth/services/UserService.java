package com.cfin.prescriptionauth.services;

import com.cfin.prescriptionauth.dtos.AuthResponse;
import com.cfin.prescriptionauth.dtos.AuthenticatedUserDTO;
import com.cfin.prescriptionauth.dtos.ClientDTO;
import com.cfin.prescriptionauth.dtos.UserDTO;

import java.text.ParseException;

public interface UserService {
	AuthenticatedUserDTO authenticateUser(UserDTO userDTO);
	AuthResponse authorizeUser(AuthenticatedUserDTO authenticatedUserDTO) throws ParseException;
	boolean registerNewUser(ClientDTO clientDTO);
}
