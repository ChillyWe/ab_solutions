package org.ab.ab_solutions_task.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.ab.ab_solutions_task.Constants;
import org.ab.ab_solutions_task.domain.models.dtos.BaseRateJSONImportDTO;
import org.ab.ab_solutions_task.io.impl.URLReaderImpl;
import org.ab.ab_solutions_task.services.contacts.LatestRateService;
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

	private final LatestRateService latestRateService;

	public RatesController(ObjectMapper objectMapper, URLReaderImpl urlReader, BaseRateServiceImpl latestRateService) {
		super(objectMapper, urlReader);
		this.latestRateService = latestRateService;
	}

	@GetMapping(path = "/historic/{base}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<JsonNode> handleHistoricModelWithBaseForYear(Model model, @PathVariable String base) {

		List<JsonNode> result = new LinkedList<JsonNode>();

		this.getDatesBetweenYear(Constants.STRING_VALUE_OF_1999).stream().forEach(day -> {
			JsonNode jsonDayWithBase = super.readJSONfromURI(String
					.format("http://data.fixer.io/api/%s?access_key=1044dc5ed8f8b4d8fa259794bbb38fcc&base=%s", day.toString(), base));
			
			result.add(jsonDayWithBase);		
			this.saveJSONObject(jsonDayWithBase);
		});
		
		return result;
	}



	@GetMapping(path = "/historic/{base}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode handleHistoricModelWithBaseAndDate(Model model, @PathVariable String base, @PathVariable String date) {

		return super.readJSONfromURI(String
				.format("http://data.fixer.io/api/%s?access_key=1044dc5ed8f8b4d8fa259794bbb38fcc&base=%s", date, base));

	}

	@GetMapping(path = "/latest/{base}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonNode handleLatestWithBase(Model model, @PathVariable String base) {

		JsonNode jsonLatestWithBase = super.readJSONfromURI(String
				.format("http://data.fixer.io/api/latest?access_key=1044dc5ed8f8b4d8fa259794bbb38fcc&base=%s", base));
//		JsonNode jsonLatestWithBase = null;
//
//		try {
//			jsonLatestWithBase = super.objectMapper.readTree(
//					"{\"success\":true,\"timestamp\":1561932545,\"base\":\"EUR\",\"date\":\"2019-06-30\",\"rates\":{\"AED\":4.174147,\"AFN\":92.623819,\"ALL\":122.479271,\"AMD\":542.920126,\"ANG\":2.13269,\"AOA\":386.696888,\"ARS\":48.299643,\"AUD\":1.616862,\"AWG\":2.045582,\"AZN\":1.937608,\"BAM\":1.954436,\"BBD\":2.296621,\"BDT\":96.108473,\"BGN\":1.955351,\"BHD\":0.428448,\"BIF\":2097.858389,\"BMD\":1.136435,\"BND\":1.535093,\"BOB\":7.859866,\"BRL\":4.376458,\"BSD\":1.137287,\"BTC\":1.01E-4,\"BTN\":78.340094,\"BWP\":12.100783,\"BYN\":2.320944,\"BYR\":22274.119405,\"BZD\":2.2927,\"CAD\":1.487343,\"CDF\":1887.617597,\"CHF\":1.112684,\"CLF\":0.027903,\"CLP\":769.945845,\"CNY\":7.803666,\"COP\":3651.932791,\"CRC\":662.439229,\"CUC\":1.136435,\"CUP\":30.115519,\"CVE\":110.632197,\"CZK\":25.430111,\"DJF\":201.96748,\"DKK\":7.464501,\"DOP\":57.969258,\"DZD\":134.832311,\"EGP\":18.985343,\"ERN\":17.046176,\"ETB\":33.06775,\"EUR\":1,\"FJD\":2.423163,\"FKP\":0.895755,\"GBP\":0.895499,\"GEL\":3.233188,\"GGP\":0.895372,\"GHS\":6.165168,\"GIP\":0.895755,\"GMD\":56.483377,\"GNF\":10483.609261,\"GTQ\":8.7639,\"GYD\":237.963732,\"HKD\":8.876851,\"HNL\":28.07109,\"HRK\":7.392622,\"HTG\":106.639641,\"HUF\":323.076628,\"IDR\":16055.548926,\"ILS\":4.053204,\"IMP\":0.895372,\"INR\":78.357742,\"IQD\":1352.35725,\"IRR\":47849.581842,\"ISK\":141.701543,\"JEP\":0.895372,\"JMD\":148.474827,\"JOD\":0.805699,\"JPY\":123.079853,\"KES\":116.302319,\"KGS\":78.974028,\"KHR\":4625.289304,\"KMF\":492.218311,\"KPW\":1022.863656,\"KRW\":1314.207053,\"KWD\":0.344851,\"KYD\":0.94794,\"KZT\":432.936248,\"LAK\":9848.343043,\"LBP\":1713.743852,\"LKR\":200.569756,\"LRD\":223.025464,\"LSL\":16.012475,\"LTL\":3.355595,\"LVL\":0.687418,\"LYD\":1.579926,\"MAD\":10.887273,\"MDL\":20.558677,\"MGA\":4093.997033,\"MKD\":61.54873,\"MMK\":1723.233248,\"MNT\":3022.836302,\"MOP\":9.149265,\"MRO\":405.707572,\"MUR\":40.513351,\"MVR\":17.55785,\"MWK\":863.689797,\"MXN\":21.766018,\"MYR\":4.696894,\"MZN\":70.579256,\"NAD\":16.01299,\"NGN\":409.116206,\"NIO\":38.013431,\"NOK\":9.681311,\"NPR\":125.752168,\"NZD\":1.691253,\"OMR\":0.437482,\"PAB\":1.137514,\"PEN\":3.741708,\"PGK\":3.843993,\"PHP\":58.236559,\"PKR\":185.23884,\"PLN\":4.243578,\"PYG\":7046.746909,\"QAR\":4.13773,\"RON\":4.721201,\"RSD\":117.85095,\"RUB\":71.74846,\"RWF\":1034.155544,\"SAR\":4.261972,\"SBD\":9.36621,\"SCR\":15.520857,\"SDG\":51.311149,\"SEK\":10.546597,\"SGD\":1.535782,\"SHP\":1.501116,\"SLL\":10142.679068,\"SOS\":663.106166,\"SRD\":8.475574,\"STD\":24502.429129,\"SVC\":9.953065,\"SYP\":585.263905,\"SZL\":16.012685,\"THB\":34.866163,\"TJS\":10.731463,\"TMT\":3.977521,\"TND\":3.270704,\"TOP\":2.587264,\"TRY\":6.514742,\"TTD\":7.698834,\"TWD\":35.198827,\"TZS\":2613.119537,\"UAH\":29.749604,\"UGX\":4197.196203,\"USD\":1.136435,\"UYU\":40.048212,\"UZS\":9722.198065,\"VEF\":11.350147,\"VND\":26490.292007,\"VUV\":130.766626,\"WST\":2.978487,\"XAF\":655.506669,\"XAG\":0.074805,\"XAU\":8.15E-4,\"XCD\":3.071271,\"XDR\":0.817584,\"XOF\":655.723,\"XPF\":119.679949,\"YER\":284.452928,\"ZAR\":16.008979,\"ZMK\":10229.286458,\"ZMW\":14.58784,\"ZWL\":365.931963}}");
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		this.saveJSONObject(jsonLatestWithBase);

		return jsonLatestWithBase;

	}

	private List<LocalDate> getDatesBetweenYear(String year) {

		LocalDate startDate = LocalDate.of(Integer.parseInt(year), 1, 1);
		// TODO don't forget to change month
		LocalDate endDate = LocalDate.of(Integer.parseInt(year), 1, 31);
		
		return startDate.datesUntil(endDate).collect(Collectors.toList());
	}
	
	private void saveJSONObject(JsonNode jsonDayWithBase) {
		try {
			String obj = jsonDayWithBase.toString();
			BaseRateJSONImportDTO modelDTO = super.objectMapper.readValue(obj, BaseRateJSONImportDTO.class);
			this.latestRateService.create(modelDTO);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}