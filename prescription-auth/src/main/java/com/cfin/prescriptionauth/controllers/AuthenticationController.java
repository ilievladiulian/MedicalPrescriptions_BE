package com.cfin.prescriptionauth.controllers;

import com.cfin.prescriptionauth.dtos.*;
import com.cfin.prescriptionauth.models.Client;
import com.cfin.prescriptionauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController()
public class AuthenticationController {
	@Autowired
	private UserService userService;

	@PostMapping("/users/authenticate")
	@CrossOrigin
	public AuthenticatedUserDTO login(@RequestBody UserDTO userDTO) {
		this.validateLoginData(userDTO);
		return this.userService.authenticateUser(userDTO);
	}

	@PostMapping("/users/authorize")
	@CrossOrigin
	public AuthResponse isUserAuthenticated(@RequestBody AuthenticatedUserDTO authenticatedUserDTO) {
		this.validateAuthorizeData(authenticatedUserDTO);
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
		this.validateRegisterUserData(clientDTO);
		OperationResponse response = new OperationResponse();
		response.setOperationName("Register new user");
		response.setSuccess(this.userService.registerNewUser(clientDTO));
		return response;
	}

	@PostMapping("/users")
	@CrossOrigin
	public Client getClientDetails(@RequestBody EmailDTO emailDTO) {
		this.validateClientDetailsRequest(emailDTO);
		return this.userService.getClientDetails(emailDTO.getEmail());
	}

	private void validateLoginData(UserDTO userDTO) {
		if (StringUtils.isEmpty(userDTO.getUsername())) {
			throw new RuntimeException("Username cannot be empty or null");
		}
		if (StringUtils.isEmpty(userDTO.getPassword())) {
			throw new RuntimeException("Password cannot be empty or null");
		}
	}

	private void validateAuthorizeData(AuthenticatedUserDTO authenticatedUserDTO) {
		if (StringUtils.isEmpty(authenticatedUserDTO.getUsername())) {
			throw new RuntimeException("Username cannot be empty or null");
		}
		if (StringUtils.isEmpty(authenticatedUserDTO.getToken())) {
			throw new RuntimeException("Token cannot be empty or null");
		}
	}

	private void validateClientDetailsRequest(EmailDTO emailDTO) {
		if (StringUtils.isEmpty(emailDTO.getEmail())) {
			throw new RuntimeException("Email cannot be empty or null");
		}
	}

	private void validateRegisterUserData(ClientDTO clientDTO) {
		if (StringUtils.isEmpty(clientDTO.getEmail())) {
			throw new RuntimeException("Client email cannot be null or empty");
		}
		if (StringUtils.isEmpty(clientDTO.getPassword())) {
			throw new RuntimeException("Client password cannot be null or empty");
		}
		if (StringUtils.isEmpty(clientDTO.getFullName())) {
			throw new RuntimeException("Name cannot be null or empty");
		}
		if (clientDTO.getAge() == null || clientDTO.getAge() <= 0) {
			throw new RuntimeException("Client age cannot be null or lower than 0");
		}
	}
}
