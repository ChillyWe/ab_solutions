package org.ab.ab_solutions_task.repositories;

import java.time.LocalDate;
import java.util.List;

import org.ab.ab_solutions_task.domain.entities.BaseRate;
import org.ab.ab_solutions_task.domain.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BaseRateRepository  extends JpaRepository<BaseRate, String> {
	
	@Query("SELECT u.rates FROM BaseRate u WHERE u.date = :date and u.base = :base")
	List<Rate> findByBaseCurrencyAndDate(
	  @Param("date") LocalDate date, 
	  @Param("base") String base);
}