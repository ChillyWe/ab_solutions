package org.ab.ab_solutions_task.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.ab.ab_solutions_task.Constants;
import org.ab.ab_solutions_task.domain.models.dtos.BaseRateJSONImportDTO;
import org.ab.ab_solutions_task.io.impl.URLReaderImpl;
import org.ab.ab_solutions_task.services.contacts.BaseRateService;
import org.ab.ab_solutions_task.services.impl.BaseRateServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/rates")
public class RatesController extends BaseController {

	// Private fields ----------------------------------
	private final BaseRateService baseRateService;

	// Constructors ----------------------------------
	public RatesController(ObjectMapper objectMapper, URLReaderImpl urlReader, BaseRateServiceImpl latestRateService) {
		super(objectMapper, urlReader);
		this.baseRateService = latestRateService;
	}

	// Public methods ----------------------------------
	@GetMapping(path = "/historic/{base}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<JsonNode> handleHistoricModelWithBaseForYear(Model model, @PathVariable String base) {

		List<JsonNode> result = new LinkedList<JsonNode>();

		this.getDatesBetweenYear(Constants.STRING_VALUE_OF_1999).stream().forEach(day -> {
			JsonNode jsonDayWithBase = super.readJSONfromURI(String.format("http://data.fixer.io/api/%s?access_key=%s&base=%s", Constants.KEY_FOR_FIXER, day.toString(), base));
			
			result.add(jsonDayWithBase);		
			this.saveJSONObject(jsonDayWithBase);
		});
		
		return result;
	}

	@GetMapping(path = "/historic/{base}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode handleHistoricModelWithBaseAndDate(Model model, @PathVariable String base, @PathVariable String date) {

		return super.readJSONfromURI(String.format("http://data.fixer.io/api/%s?access_key=%s&base=%s", Constants.KEY_FOR_FIXER, date, base));

	}

	@GetMapping(path = "/latest/{base}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode handleLatestWithBase(Model model, @PathVariable String base) {
		
		JsonNode jsonLatestWithBase = super.readJSONfromURI(String.format("http://data.fixer.io/api/latest?access_key=%s&base=%s", Constants.KEY_FOR_FIXER, base));

		this.saveJSONObject(jsonLatestWithBase);

		return jsonLatestWithBase;

	}

	// Private methods ----------------------------------
	// Custom method to return all days from year
	private List<LocalDate> getDatesBetweenYear(String year) {

		LocalDate startDate = LocalDate.of(Integer.parseInt(year), 1, 1);
		// TODO don't forget to change month
		LocalDate endDate = LocalDate.of(Integer.parseInt(year), 1, 31);
		
		return startDate.datesUntil(endDate).collect(Collectors.toList());
	}
	
	// this method save JSON object in database
	private void saveJSONObject(JsonNode jsonDayWithBase) {
		try {
			String obj = jsonDayWithBase.toString();
			BaseRateJSONImportDTO modelDTO = super.objectMapper.readValue(obj, BaseRateJSONImportDTO.class);
			this.baseRateService.create(modelDTO);
		} catch (JsonParseException jsonpe) {
			LOGGER.info(jsonpe.getMessage());
		} catch (JsonMappingException jsonme) {
			LOGGER.info(jsonme.getMessage());
		} catch (IOException ioe) {
			LOGGER.info(ioe.getMessage());
		}
	}
}