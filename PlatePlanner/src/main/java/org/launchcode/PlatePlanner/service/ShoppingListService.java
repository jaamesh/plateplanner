package org.launchcode.PlatePlanner.service;

import org.launchcode.PlatePlanner.model.*;
import org.launchcode.PlatePlanner.repository.ShoppingListItemRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class ShoppingListService {

    public Set<ShoppingListItem> buildShoppingListItemsFromMealPlan(MealPlan mealPlan) {

        Map<String, ShoppingListItem> aggregator = new HashMap<>();

        for (MealPlanRecipe mealPlanRecipe : mealPlan.getMealPlanRecipes()) {
            Recipe recipe = mealPlanRecipe.getRecipe();
            if (recipe == null) continue;

            for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {
                String key = recipeIngredient.getIngredient().getId() + "-" + recipeIngredient.getUnit();

                if (!aggregator.containsKey(key)) {
                    ShoppingListItem newItem = new ShoppingListItem();
                    newItem.setIngredient(recipeIngredient.getIngredient());
                    newItem.setUnit(recipeIngredient.getUnit());
                    newItem.setQuantity(recipeIngredient.getQuantity());

                    aggregator.put(key, newItem);
                } else {
                    ShoppingListItem existing = aggregator.get(key);
                    double newQuantity = existing.getQuantity() + recipeIngredient.getQuantity();
                    existing.setQuantity(newQuantity);
                }
            }
        }

        return new HashSet<>(aggregator.values());

    }
}
