package com.cfin.prescriptionauth.controllers;

import com.cfin.prescriptionauth.dtos.AuthenticatedUserDTO;
import com.cfin.prescriptionauth.dtos.ClientDTO;
import com.cfin.prescriptionauth.dtos.UserDTO;
import com.cfin.prescriptionauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AuthenticationController {
	@Autowired
	private UserService userService;

	@PostMapping("/users/authenticate")
	@CrossOrigin
	public AuthenticatedUserDTO login(@RequestBody UserDTO userDTO) {
		return this.userService.authenticateUser(userDTO);
	}

	@PostMapping("/users/authorize")
	@CrossOrigin
	public boolean isUserAuthenticated(@RequestBody AuthenticatedUserDTO authenticatedUserDTO) {
		try {
			return this.userService.authorizeUser(authenticatedUserDTO);
		} catch (Exception e) {
			return false;
		}
	}

	@PostMapping("/users/register")
	@CrossOrigin
	public boolean registerNewUser(@RequestBody ClientDTO clientDTO) {
		return this.userService.registerNewUser(clientDTO);
	}
}
