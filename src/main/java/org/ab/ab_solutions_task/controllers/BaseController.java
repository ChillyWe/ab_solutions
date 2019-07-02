package org.ab.ab_solutions_task.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.ab.ab_solutions_task.Constants;
import org.ab.ab_solutions_task.io.impl.URLReaderImpl;
import org.ab.ab_solutions_task.io.interfaces.URLReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseController {

	
	protected final ObjectMapper objectMapper;
	protected final URLReader urlReader;
	
	@Autowired
	protected BaseController(ObjectMapper objectMapper, URLReaderImpl urlReader) {
		this.objectMapper = objectMapper;
		this.urlReader = urlReader;
	}
	
	protected JsonNode readJSONfromURI(String uriString) {	
		JsonNode result = null;
		String jsonStringResult = Constants.EMPTY_STRING;
		
		try {
			URL url = new URL(uriString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(Constants.HTTP_GET_METHOD);
			conn.connect();
			int responsecode = conn.getResponseCode();

			if (responsecode != 200) {
				throw new RuntimeException(Constants.HTTP_RESPONSE_CODE_MESSAGE + responsecode);
			} else {
				jsonStringResult = urlReader.read(url);
			}
			conn.disconnect();
			result = objectMapper.readTree(jsonStringResult);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}