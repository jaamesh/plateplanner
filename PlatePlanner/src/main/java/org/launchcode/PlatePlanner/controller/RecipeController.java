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
@RequestMapping("recipes")
@CrossOrigin(origins = "http://localhost:5173")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;


    @GetMapping
    public List<Recipe> getAllSavedRecipes() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    @GetMapping
    public Optional<Recipe> getSavedRecipe(Long recipeId) {
        return recipeRepository.findById(recipeId);
    }

    @PostMapping()
    public Recipe createRecipe(@RequestBody @Valid Recipe recipe, Errors errors) {
        recipe.assignToIngredients();
        return recipeRepository.save(recipe);
    }

    @PostMapping()
    public void updateRecipe(@RequestBody @Valid Recipe recipe) {
        if (recipeRepository.existsById(recipe.getId())) {
            recipe.assignToIngredients();
            recipeRepository.save(recipe);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping
    public void deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }

}
