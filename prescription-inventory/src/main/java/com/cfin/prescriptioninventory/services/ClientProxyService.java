package com.cfin.prescriptioninventory.services;

import com.cfin.prescriptioninventory.dtos.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class ClientProxyService {
	@Value("http://localhost:8301/users")
	private String clientDetails;

	@Autowired
	private RemoteCallService proxyService;

	public Client getClientDetails(String username) {
		try {
			String postBody = String.format("{\"username\": \"%s\"}", username);

			String jsonAsString = proxyService.doCallServicePost(new URL(clientDetails), postBody);

			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonAsString, Client.class);
		} catch (IOException e) {
			throw new RuntimeException("Error while calling authorization service");
		}
	}
}
