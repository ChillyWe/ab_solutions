package org.ab.ab_solutions_task.io.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;

import org.ab.ab_solutions_task.io.interfaces.URLReader;
import org.springframework.stereotype.Component;

@Component
public class URLReaderImpl implements URLReader {
	
	// Logger ----------------------------------
	private static final Logger LOGGER = Logger.getLogger(URLReaderImpl.class.getName());

	// Constructors ----------------------------------
	public URLReaderImpl() {
		super();
	}
	
	// Public methods ----------------------------------
	public String read(URL url) {
		StringBuilder inline = new StringBuilder();
		Scanner sc;
		try {
			sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				inline.append(sc.nextLine());
			}
			sc.close();
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
		}
		
		return inline.toString();
	}
}