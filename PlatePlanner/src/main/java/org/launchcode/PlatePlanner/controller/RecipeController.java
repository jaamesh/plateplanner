package org.launchcode.PlatePlanner.controller;


import org.launchcode.PlatePlanner.model.Recipe;
import org.launchcode.PlatePlanner.repository.RecipeIngredientRepository;
import org.launchcode.PlatePlanner.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recipes")
@CrossOrigin(origins = "http://localhost:5173")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;


    @GetMapping
    public List<Recipe> getAllSavedRecipes() {
        List<Recipe> savedRecipesList = (List<Recipe>) recipeRepository.findAll();
        return savedRecipesList;
    }

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        recipe.assignToIngredients();
        return recipeRepository.save(recipe);
    }
}
