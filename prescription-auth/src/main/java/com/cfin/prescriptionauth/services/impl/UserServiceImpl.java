package com.cfin.prescriptionauth.services.impl;

import com.cfin.prescriptionauth.dtos.AuthenticatedUserDTO;
import com.cfin.prescriptionauth.dtos.ClientDTO;
import com.cfin.prescriptionauth.dtos.UserDTO;
import com.cfin.prescriptionauth.models.Client;
import com.cfin.prescriptionauth.models.User;
import com.cfin.prescriptionauth.models.UserLogin;
import com.cfin.prescriptionauth.repositories.ClientRepository;
import com.cfin.prescriptionauth.repositories.UserLoginRepository;
import com.cfin.prescriptionauth.repositories.UserRepository;
import com.cfin.prescriptionauth.services.UserService;
import com.cfin.prescriptionauth.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserLoginRepository userLoginRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public AuthenticatedUserDTO authenticateUser(UserDTO userDTO) {
		User currentUser = this.userRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
		if (currentUser == null) {
			throw new RuntimeException("User not found");
		} else {
			String token = TokenUtils.createJsonWebToken(userDTO.getUsername());
			UserLogin userLogin = new UserLogin();
			userLogin.setReferencedUser(currentUser);
			userLogin.setToken(token);
			userLogin.setTokenExpireTime(TokenUtils.getCurrentTimeStamp());

			this.userLoginRepository.save(userLogin);

			AuthenticatedUserDTO authenticatedUserDTO = new AuthenticatedUserDTO();
			authenticatedUserDTO.setToken(token);
			authenticatedUserDTO.setUsername(currentUser.getUsername());
			return authenticatedUserDTO;
		}
	}

	@Override
	public boolean authorizeUser(AuthenticatedUserDTO authenticatedUserDTO) throws ParseException {
		UserLogin userLogin = this.userLoginRepository.findByReferencedUserUsernameAndToken(authenticatedUserDTO.getUsername(), authenticatedUserDTO.getToken());
		if (userLogin != null) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = format.parse(userLogin.getTokenExpireTime());

			return new Date().compareTo(date) < 1;
		}
		return false;
	}

	@Override
	public boolean registerNewUser(ClientDTO clientDTO) {
		User user = new User();
		user.setPassword(clientDTO.getPassword());
		user.setUsername(clientDTO.getEmail());
		User savedUser = this.userRepository.save(user);
		if (savedUser == null) {
			throw new RuntimeException("Could not save user");
		}

		Client client = new Client();
		client.setFullName(clientDTO.getFullName());
		client.setAge(clientDTO.getAge());
		client.setEmail(clientDTO.getEmail());
		Client savedClient = this.clientRepository.save(client);
		if (savedClient == null) {
			throw new RuntimeException("Could not save cleint");
		}

		return true;
	}
}
