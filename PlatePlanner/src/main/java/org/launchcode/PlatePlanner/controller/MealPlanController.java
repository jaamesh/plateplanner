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
@RequestMapping("meal-plan")
@CrossOrigin(origins = "http://localhost:5173")
public class MealPlanController {

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @GetMapping("/all")
    public List<MealPlan> getAllSavedMealPlans() {
        return (List<MealPlan>) mealPlanRepository.findAll();
    }

    @GetMapping("/{mealPlanId}")
    public Optional<MealPlan> getSavedMealPlan(@PathVariable Long mealPlanId) {
        return mealPlanRepository.findById(mealPlanId);
    }

    @PostMapping("/create")
    public MealPlan createMealPlan(@RequestBody @Valid MealPlan mealPlan, Errors errors) {
//      TODO: replace this line with appropriate MealPlan methods
        return mealPlanRepository.save(mealPlan);
    }

    @PostMapping("/update/{mealPlanId}")
    public void updateMealPlan(@PathVariable Long mealPlanId, @RequestBody @Valid MealPlan mealPlan) {
        if (mealPlanRepository.existsById(mealPlanId)) {
            mealPlanRepository.save(mealPlan);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping("/delete/{mealPlanId}")
    public void deleteMealPlan(@PathVariable Long mealPlanId) {
        mealPlanRepository.deleteById(mealPlanId);
    }


}
