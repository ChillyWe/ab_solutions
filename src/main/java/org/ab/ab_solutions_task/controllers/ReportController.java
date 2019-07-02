package org.ab.ab_solutions_task.controllers;

import org.ab.ab_solutions_task.io.impl.URLReaderImpl;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/report")
public class ReportController extends BaseController {

	public ReportController(ObjectMapper objectMapper, URLReaderImpl urlReader) {
		super(objectMapper, urlReader);
	}

	@GetMapping(path = "/{currency}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode handle(Model model, @PathVariable String currency, @PathVariable String date) {
		
		
		return null;
	}
}