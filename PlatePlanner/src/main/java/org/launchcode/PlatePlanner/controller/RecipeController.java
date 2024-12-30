package org.launchcode.PlatePlanner.controller;


import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.Recipe;
import org.launchcode.PlatePlanner.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("recipe")
@CrossOrigin(origins = "http://localhost:5173")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;


    @GetMapping("/all")
    public List<Recipe> getAllSavedRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    @GetMapping("/{recipeId}")
    public Optional<Recipe> getSavedRecipe(@PathVariable Long recipeId) {
        return recipeRepository.findById(recipeId);
    }

    @PostMapping("/create")
    public Recipe createRecipe(@RequestBody @Valid Recipe recipe, Errors errors) {
        recipe.assignToIngredients();
        return recipeRepository.save(recipe);
    }

    @PostMapping("/update/{recipeId}")
    public void updateRecipe(@PathVariable Long recipeId, @RequestBody @Valid Recipe recipe) {
        if (recipeRepository.existsById(recipeId)) {
            recipe.assignToIngredients();
            recipeRepository.save(recipe);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping("/delete/{recipeId}")
    public void deleteRecipe(@PathVariable Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }

}
