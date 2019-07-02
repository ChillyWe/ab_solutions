package org.ab.ab_solutions_task.services.contacts;

import org.ab.ab_solutions_task.domain.models.dtos.BaseRateJSONImportDTO;

public interface LatestRateService {
	
	public void create(BaseRateJSONImportDTO dto);

}