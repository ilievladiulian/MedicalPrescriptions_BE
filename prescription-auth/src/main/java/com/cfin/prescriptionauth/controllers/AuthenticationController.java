package com.cfin.prescriptionauth.controllers;

import com.cfin.prescriptionauth.dtos.*;
import com.cfin.prescriptionauth.models.Client;
import com.cfin.prescriptionauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public AuthResponse isUserAuthenticated(@RequestBody AuthenticatedUserDTO authenticatedUserDTO) {
		try {
			return this.userService.authorizeUser(authenticatedUserDTO);
		} catch (Exception e) {
			AuthResponse authResponse = new AuthResponse();
			authResponse.setUsername(authenticatedUserDTO.getUsername());
			authResponse.setAuthorized(false);
			return authResponse;
		}
	}

	@PostMapping("/users/register")
	@CrossOrigin
	public OperationResponse registerNewUser(@RequestBody ClientDTO clientDTO) {
		OperationResponse response = new OperationResponse();
		response.setOperationName("Register new user");
		response.setSuccess(this.userService.registerNewUser(clientDTO));
		return response;
	}

	@PostMapping("/users")
	@CrossOrigin
	public Client getClientDetails(@RequestBody EmailDTO emailDTO) {
		return this.userService.getClientDetails(emailDTO.getEmail());
	}
}
