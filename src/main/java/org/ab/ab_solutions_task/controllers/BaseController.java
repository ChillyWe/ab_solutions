package org.ab.ab_solutions_task.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.ab.ab_solutions_task.Constants;
import org.ab.ab_solutions_task.exceptions.FixerException;
import org.ab.ab_solutions_task.io.impl.URLReaderImpl;
import org.ab.ab_solutions_task.io.interfaces.URLReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseController {

	// Logger ----------------------------------
	protected static final Logger LOGGER = Logger.getLogger(BaseController.class.getName());
	
	// Protected fields ----------------------------------
	protected final ObjectMapper objectMapper;
	protected final URLReader urlReader;
	
	// Constructors ----------------------------------
	@Autowired
	protected BaseController(ObjectMapper objectMapper, URLReaderImpl urlReader) {
		this.objectMapper = objectMapper;
		this.urlReader = urlReader;
	}
	
	// Protected methods ----------------------------------
	// Method to extract json data from URL
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
				throw new FixerException(Constants.FIXER_EXCEPTION_MESSAGE + responsecode);
			} else {
				jsonStringResult = urlReader.read(url);
			}
			conn.disconnect();
			result = objectMapper.readTree(jsonStringResult);
			
		} catch (MalformedURLException me) {
			LOGGER.info(me.getMessage());
		} catch (IOException ioe) {
			LOGGER.info(ioe.getMessage());
		} catch (FixerException fe) {
			LOGGER.info(fe.getMessage());
		}
		
		return result;
	}
}