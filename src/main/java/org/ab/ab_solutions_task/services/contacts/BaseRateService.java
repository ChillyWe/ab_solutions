package org.ab.ab_solutions_task.services.contacts;

import org.ab.ab_solutions_task.domain.models.dtos.BaseRateJSONImportDTO;
import org.ab.ab_solutions_task.domain.models.view.ReportViewDTO;

public interface BaseRateService {
	
	public void create(BaseRateJSONImportDTO dto);
	
	public ReportViewDTO getOneByDateAndCurrency(String date, String currency);

}