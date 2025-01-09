package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.RecipeIngredient;
import org.launchcode.PlatePlanner.repository.RecipeIngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("recipe-ingredient")
@CrossOrigin(origins = "http://localhost:5173")
public class RecipeIngredientController {

    Logger logger = LoggerFactory.getLogger(RecipeIngredientController.class);

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @GetMapping("/all")
    public ResponseEntity<List<RecipeIngredient>> getAllSavedRecipeIngredients() {
        logger.info("In getAllSavedRecipeIngredients...");
        return ResponseEntity.ok(recipeIngredientRepository.findAll());
    }

    @GetMapping("/{recipeIngredientId}")
    public ResponseEntity<Optional<RecipeIngredient>> getSavedRecipeIngredient(@PathVariable("recipeIngredientId") Long recipeIngredientId) {
        logger.info("In getSavedRecipeIngredient...");
        if (recipeIngredientRepository.existsById(recipeIngredientId)) {
            logger.info("RecipeIngredient with ID {} found...", recipeIngredientId);
            return ResponseEntity.ok(recipeIngredientRepository.findById(recipeIngredientId));
        } else {
            logger.warn("RecipeIngredient with ID {} not found...", recipeIngredientId);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createRecipeIngredient(@RequestBody @Valid RecipeIngredient recipeIngredient, Errors errors) {
        logger.info("In createRecipeIngredient...");
        recipeIngredientRepository.save(recipeIngredient);
        if (errors.hasErrors()) {
            logger.error("Error creating recipeIngredient: {}", errors);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update/{recipeIngredientId}")
    public ResponseEntity<Object> updateRecipeIngredient(@PathVariable("recipeIngredientId") Long recipeIngredientId, @RequestBody @Valid RecipeIngredient recipeIngredient) {
        logger.info("In updateRecipeIngredient...");
        if (recipeIngredientRepository.existsById(recipeIngredientId)) {
            logger.info("RecipeIngredient with ID {} found.  Updating...", recipeIngredientId);
            recipeIngredientRepository.save(recipeIngredient);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("RecipeIngredient with ID {} not found...", recipeIngredientId);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{recipeIngredientId}")
    public ResponseEntity<Object> deleteRecipeIngredient(@PathVariable("recipeIngredientId") Long recipeIngredientId) {
        logger.info("In deleteRecipeIngredient...");
        if (recipeIngredientRepository.existsById(recipeIngredientId)) {
            logger.info("RecipeIngredient with ID {} found.  Deleting...", recipeIngredientId);
            recipeIngredientRepository.deleteById(recipeIngredientId);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("RecipeIngredient with ID {} not found...", recipeIngredientId);
            return ResponseEntity.notFound().build();
        }
    }

}
