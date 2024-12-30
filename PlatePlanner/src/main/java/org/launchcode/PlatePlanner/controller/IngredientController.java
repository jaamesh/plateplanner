package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.Ingredient;
import org.launchcode.PlatePlanner.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ingredient")
@CrossOrigin(origins = "http://localhost:5173")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/all")
    public List<Ingredient> getAllSavedIngredients() {
        return (List<Ingredient>) ingredientRepository.findAll();
    }

    @GetMapping("/{ingredientId}")
    public Optional<Ingredient> getSavedIngredient(@PathVariable Long ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    @PostMapping("/create")
    public Ingredient createIngredient(@RequestBody @Valid Ingredient ingredient, Errors errors) {
//      TODO: replace this line with appropriate Ingredient methods
        return ingredientRepository.save(ingredient);
    }

    @PostMapping("/update/{ingredientId}")
    public void updateIngredient(@PathVariable Long ingredientId, @RequestBody @Valid Ingredient ingredient) {
        if (ingredientRepository.existsById(ingredientId)) {
            ingredientRepository.save(ingredient);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping("/delete/{ingredientId}")
    public void deleteIngredient(@PathVariable Long ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }

}
