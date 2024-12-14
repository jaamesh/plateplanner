package org.launchcode.PlatePlanner.repository;

import org.launchcode.PlatePlanner.model.MealPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPlanRepository extends CrudRepository<MealPlan, Long> {
}
