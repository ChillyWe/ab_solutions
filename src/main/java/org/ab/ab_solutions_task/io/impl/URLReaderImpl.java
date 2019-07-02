package org.ab.ab_solutions_task.io.impl;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.ab.ab_solutions_task.io.interfaces.URLReader;
import org.springframework.stereotype.Component;

@Component
public class URLReaderImpl implements URLReader {

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
			e.printStackTrace();
		}
		
		return inline.toString();
	}
}