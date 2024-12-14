package org.launchcode.PlatePlanner.service;

import jakarta.persistence.EntityNotFoundException;
import org.launchcode.PlatePlanner.model.MealPlan;
import org.launchcode.PlatePlanner.model.Recipe;
import org.launchcode.PlatePlanner.model.User;
import org.launchcode.PlatePlanner.repository.MealPlanRepository;
import org.launchcode.PlatePlanner.repository.RecipeRepository;
import org.launchcode.PlatePlanner.repository.UserRepository;

import java.util.Optional;

//Code by DW

public class MealPlanService {

    private MealPlanRepository mealPlanRepository;
    private RecipeRepository recipeRepository;
    private UserRepository userRepository;

    //Users can create a meal plan

    public MealPlan createMealPlan(Long userId, String mealPlanName) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }



    }

    //Users can add a recipe to an existing meal plan

    public MealPlan addRecipeToMealPlan(Long recipeId, Long mealPlanId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        Optional<MealPlan> optionalMealPlan = mealPlanRepository.findById(mealPlanId);

        if (optionalMealPlan.isEmpty()) {
            throw new EntityNotFoundException("Meal plan with ID " + mealPlanId + " not found.");
        }

        if (optionalRecipe.isEmpty()) {
            throw new EntityNotFoundException("Recipe with ID " + recipeId + " not found.");
        }

        MealPlan mealPlan = optionalMealPlan.get();
        Recipe recipe = optionalRecipe.get();

        mealPlan.addRecipe(recipe);

        return mealPlanRepository.save(mealPlan);

    }


    //Users can remove a recipe from a meal plan

    public void removeRecipeFromMealPlan(Long recipeId, Long mealPlanId) {

    }

    //User can view a list of existing meal plans


    //User can view a specific meal plan

}
