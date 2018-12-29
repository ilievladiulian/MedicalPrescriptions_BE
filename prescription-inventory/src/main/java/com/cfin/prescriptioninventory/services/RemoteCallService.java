package com.cfin.prescriptioninventory.services;

import java.io.IOException;
import java.net.URL;

public interface RemoteCallService {
	String doCallServiceGet(URL url) throws IOException;
	String doCallServicePost(URL url, String postBody) throws IOException;
}
