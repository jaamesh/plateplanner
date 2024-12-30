package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.Ingredient;
import org.launchcode.PlatePlanner.repository.IngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ingredient")
@CrossOrigin(origins = "http://localhost:5173")
public class IngredientController {

    Logger logger = LoggerFactory.getLogger(IngredientController.class);

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Ingredient>> getAllSavedIngredients() {
        logger.info("In getAllSavedIngredients...");
        return ResponseEntity.ok(ingredientRepository.findAll());
    }

    @GetMapping("/{ingredientId}")
    public ResponseEntity<Optional<Ingredient>> getSavedIngredient(@PathVariable("ingredientId") Long ingredientId) {
        logger.info("In getSavedIngredient...");

        if (ingredientRepository.existsById(ingredientId)) {
            logger.info("Ingredient with ID {} found...", ingredientId);
            return ResponseEntity.ok(ingredientRepository.findById(ingredientId));
        } else {
            logger.warn("Ingredient with ID {} not found...", ingredientId);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createIngredient(@RequestBody @Valid Ingredient ingredient, Errors errors) {
        logger.info("In createIngredient...");
        ingredientRepository.save(ingredient);
        if (errors.hasErrors()) {
            logger.error("Error creating ingredient: {}", errors);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update/{ingredientId}")
    public ResponseEntity<Object> updateIngredient(@PathVariable("ingredientId") Long ingredientId, @RequestBody @Valid Ingredient ingredient) {
        logger.info("In updateIngredient...");
        if (ingredientRepository.existsById(ingredientId)) {
            logger.info("Ingredient with ID {} found.  Updating...", ingredientId);
            ingredientRepository.save(ingredient);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Ingredient with ID {} not found...", ingredientId);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{ingredientId}")
    public ResponseEntity<Object> deleteIngredient(@PathVariable("ingredientId") Long ingredientId) {
        logger.info("In deleteIngredient...");
        if (ingredientRepository.existsById(ingredientId)) {
            logger.info("Ingredient with ID {} found.  Deleting...", ingredientId);
            ingredientRepository.deleteById(ingredientId);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Ingredient with ID {} not found...", ingredientId);
            return ResponseEntity.notFound().build();
        }
    }

}
