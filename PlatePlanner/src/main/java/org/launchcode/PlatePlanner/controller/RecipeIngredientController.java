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
@RequestMapping("recipe-ingredient")
@CrossOrigin(origins = "http://localhost:5173")
public class RecipeIngredientController {

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @GetMapping("/all")
    public List<RecipeIngredient> getAllSavedRecipeIngredients() {
        return (List<RecipeIngredient>) recipeIngredientRepository.findAll();
    }

    @GetMapping("/{recipeIngredientId}")
    public Optional<RecipeIngredient> getSavedRecipeIngredient(@PathVariable Long recipeIngredientId) {
        return recipeIngredientRepository.findById(recipeIngredientId);
    }

    @PostMapping("/create")
    public RecipeIngredient createRecipeIngredient(@RequestBody @Valid RecipeIngredient recipeIngredient, Errors errors) {
        return recipeIngredientRepository.save(recipeIngredient);
    }

    @PostMapping("/update/{recipeIngredientId}")
    public void updateRecipeIngredient(@PathVariable Long recipeIngredientId, @RequestBody @Valid RecipeIngredient recipeIngredient) {
        if (recipeIngredientRepository.existsById(recipeIngredientId)) {
            recipeIngredientRepository.save(recipeIngredient);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping("/delete/{recipeIngredientId}")
    public void deleteRecipeIngredient(@PathVariable Long recipeIngredientId) {
        recipeIngredientRepository.deleteById(recipeIngredientId);
    }
}
