package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.MealPlan;
import org.launchcode.PlatePlanner.repository.MealPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("mealPlan")
@CrossOrigin(origins = "http://localhost:5173")
public class MealPlanController {

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @GetMapping
    public List<MealPlan> getAllSavedMealPlans() {
        return (List<MealPlan>) mealPlanRepository.findAll();
    }

    @GetMapping
    public Optional<MealPlan> getSavedMealPlan(Long mealPlanId) {
        return mealPlanRepository.findById(mealPlanId);
    }

    @PostMapping()
    public MealPlan createMealPlan(@RequestBody @Valid MealPlan mealPlan, Errors errors) {
//      TODO: replace this line with appropriate MealPlan methods
        return mealPlanRepository.save(mealPlan);
    }

    @PostMapping()
    public void updateMealPlan(@RequestBody @Valid MealPlan mealPlan) {
        if (mealPlanRepository.existsById(mealPlan.getId())) {
            mealPlanRepository.save(mealPlan);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping
    public void deleteMealPlan(Long mealPlanId) {
        mealPlanRepository.deleteById(mealPlanId);
    }


}
