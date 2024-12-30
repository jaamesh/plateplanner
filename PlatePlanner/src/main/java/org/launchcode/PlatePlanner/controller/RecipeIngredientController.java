package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.RecipeIngredient;
import org.launchcode.PlatePlanner.repository.RecipeIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("recipesIngredient")
@CrossOrigin(origins = "http://localhost:5173")
public class RecipeIngredientController {

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @GetMapping
    public List<RecipeIngredient> getAllSavedRecipeIngredients() {
        return (List<RecipeIngredient>) recipeIngredientRepository.findAll();
    }

    @GetMapping
    public Optional<RecipeIngredient> getSavedRecipeIngredient(Long recipeIngredientId) {
        return recipeIngredientRepository.findById(recipeIngredientId);
    }

    @PostMapping()
    public RecipeIngredient createRecipeIngredient(@RequestBody @Valid RecipeIngredient recipeIngredient, Errors errors) {
//      TODO: replace this line with appropriate RecipeIngredient methods
        return recipeIngredientRepository.save(recipeIngredient);
    }

    @PostMapping()
    public void updateRecipeIngredient(@RequestBody @Valid RecipeIngredient recipeIngredient) {
        if (recipeIngredientRepository.existsById(recipeIngredient.getId())) {
            recipeIngredientRepository.save(recipeIngredient);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping
    public void deleteRecipeIngredient(Long recipeIngredientId) {
        recipeIngredientRepository.deleteById(recipeIngredientId);
    }


}
