package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.MealPlan;
import org.launchcode.PlatePlanner.repository.MealPlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("meal-plan")
@CrossOrigin(origins = "http://localhost:5173")
public class MealPlanController {

    Logger logger = LoggerFactory.getLogger(IngredientController.class);

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @GetMapping("/all")
    public ResponseEntity<List<MealPlan>> getAllSavedMealPlans() {
        logger.info("In getAllSavedMealPlans...");
        return ResponseEntity.ok(mealPlanRepository.findAll());
    }

    @GetMapping("/{mealPlanId}")
    public ResponseEntity<Optional<MealPlan>> getSavedMealPlan(@PathVariable("mealPlanId") Long mealPlanId) {
        logger.info("In getSavedMealPlan...");
        if (mealPlanRepository.existsById(mealPlanId)) {
            logger.info("MealPlan with ID {} found...", mealPlanId);
            return ResponseEntity.ok(mealPlanRepository.findById(mealPlanId));
        } else {
            logger.warn("MealPlan with ID {} not found...", mealPlanId);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createMealPlan(@RequestBody @Valid MealPlan mealPlan, Errors errors) {
        logger.info("In createMealPlan...");
        mealPlanRepository.save(mealPlan);
        if (errors.hasErrors()) {
            logger.error("Error creating meal plan: {}", errors);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update/{mealPlanId}")
    public ResponseEntity<Object> updateMealPlan(@PathVariable("mealPlanId") Long mealPlanId, @RequestBody @Valid MealPlan mealPlan) {
        logger.info("In updateMealPlan...");
        if (mealPlanRepository.existsById(mealPlanId)) {
            logger.info("MealPlan with ID {} found.  Updating...", mealPlanId);
            mealPlanRepository.save(mealPlan);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("MealPlan with ID {} not found...", mealPlanId);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{mealPlanId}")
    public ResponseEntity<Object> deleteMealPlan(@PathVariable("mealPlanId") Long mealPlanId) {
        logger.info("In deleteIngredient...");
        if (mealPlanRepository.existsById(mealPlanId)) {
            logger.info("MealPlan with ID {} found.  Deleting...", mealPlanId);
            mealPlanRepository.deleteById(mealPlanId);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("MealPlan with ID {} not found...", mealPlanId);
            return ResponseEntity.notFound().build();
        }
    }

}
