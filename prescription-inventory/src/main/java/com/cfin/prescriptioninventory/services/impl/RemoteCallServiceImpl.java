package com.cfin.prescriptioninventory.services.impl;

import com.cfin.prescriptioninventory.services.RemoteCallService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class RemoteCallServiceImpl implements RemoteCallService {
	public String doCallServiceGet(URL url) throws IOException {
		return doCallService(url, "GET", null);
	}

	public String doCallServicePost(URL url, String postBody) throws IOException {
		return doCallService(url, "POST", postBody);
	}

	private HttpURLConnection makeRequest(URL url, String method, String postBody) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Accept", "application/json");

		if ("GET".equals(method)) {
			conn.setRequestMethod("GET");
		} else {
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("content-type", "application/json");

			BufferedWriter streamWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			streamWriter.write(postBody);
			streamWriter.flush();
			streamWriter.close();
		}
		return conn;
	}

	private String doCallService(URL url, String method, String postBody) throws IOException {
		BufferedReader streamReader = null;
		HttpURLConnection conn = null;
		try {
			conn = makeRequest(url, method, postBody);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed with http error code: " + conn.getResponseCode());
			}

			streamReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();
			String inputStr;
			while ((inputStr = streamReader.readLine()) != null) {
				stringBuilder.append(inputStr);
			}

			String result = stringBuilder.toString();
			return result;
		} finally {
			if (streamReader != null) {
				streamReader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

}