package org.launchcode.PlatePlanner.repository;

import org.launchcode.PlatePlanner.model.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
}
