package com.cfin.prescriptionauth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TokenUtils {
	public static String getCurrentTimeStamp() {
		Date newDate = DateUtils.addHours(new Date(), 3);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newDate);
	}

	public static String createJsonWebToken(String username){
		String jwt = JWT.create()
				.withSubject(username)
				.withIssuer("auth0")
				.withExpiresAt(DateUtils.addHours(new Date(), 3))
				.sign(Algorithm.HMAC256(Constants.APPLICATION_SECRET));
		return jwt;
	}
}
