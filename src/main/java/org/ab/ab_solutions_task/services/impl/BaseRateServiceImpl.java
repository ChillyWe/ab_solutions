package org.ab.ab_solutions_task.services.impl;

import org.ab.ab_solutions_task.domain.entities.BaseRate;
import org.ab.ab_solutions_task.domain.entities.Rate;
import org.ab.ab_solutions_task.domain.models.dtos.BaseRateJSONImportDTO;
import org.ab.ab_solutions_task.repositories.BaseRateRepository;
import org.ab.ab_solutions_task.repositories.RateRepository;
import org.ab.ab_solutions_task.services.contacts.LatestRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BaseRateServiceImpl implements LatestRateService {

	private final BaseRateRepository baseRateRepository;
	private final RateRepository rateRepository;

	@Autowired
	public BaseRateServiceImpl(BaseRateRepository baseRateRepository, RateRepository rateRepository) {
		super();
		this.baseRateRepository = baseRateRepository;
		this.rateRepository = rateRepository;
	}

	public void create(BaseRateJSONImportDTO dto) {	
		
		BaseRate baseRate = new BaseRate();
		
		baseRate.setSuccess(dto.getSuccess());
		baseRate.setTimestamp(dto.getTimestamp());
		baseRate.setBase(dto.getBase());
		baseRate.setDate(dto.getDate());
		
		dto.getRates().entrySet().stream().forEach(r -> {
			Rate rate = new Rate();
			rate.setSymbols(r.getKey());
			rate.setRate(r.getValue());
			this.rateRepository.saveAndFlush(rate);
			baseRate.getRates().add(rate);
			rate.setBase(baseRate);
		});
		
        this.baseRateRepository.saveAndFlush(baseRate);
    }
	
}