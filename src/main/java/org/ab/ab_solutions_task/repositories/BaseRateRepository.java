package org.ab.ab_solutions_task.repositories;

import org.ab.ab_solutions_task.domain.entities.BaseRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRateRepository  extends JpaRepository<BaseRate, String> {

}