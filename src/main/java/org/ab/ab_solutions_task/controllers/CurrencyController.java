package org.ab.ab_solutions_task.controllers;

import org.ab.ab_solutions_task.io.impl.URLReaderImpl;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CurrencyController extends BaseController {

	public CurrencyController(ObjectMapper objectMapper, URLReaderImpl urlReader) {
		super(objectMapper, urlReader);
	}

	@GetMapping(path = "/currency", produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode handle(Model model) {

		return super.readJSONfromURI("http://data.fixer.io/api/symbols?access_key=1044dc5ed8f8b4d8fa259794bbb38fcc");
		
	}
}