package com.cfin.prescriptioninventory.services;

import com.cfin.prescriptioninventory.dtos.AuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class UserAuthProxyService {

	@Value("http://localhost:8301/users/authorize")
	private String userAuthorization;

	@Autowired
	private RemoteCallService proxyService;

	public AuthResponse authorizeUser(String username, String token) {
		try {
			String postBody = String.format("{\"token\": \"%s\", \"username\": \"%s\"}", token, username);

			String jsonAsString = proxyService.doCallServicePost(new URL(userAuthorization), postBody);

			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonAsString, AuthResponse.class);
		} catch (IOException e) {
			throw new RuntimeException("Error while calling authorization service");
		}
	}

}