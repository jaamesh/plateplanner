package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.*;
import org.launchcode.PlatePlanner.repository.MealPlanRepository;
import org.launchcode.PlatePlanner.repository.ShoppingListRepository;
import org.launchcode.PlatePlanner.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("shopping-list")
@CrossOrigin(origins = "http://localhost:5173")
public class ShoppingListController {

    Logger logger = LoggerFactory.getLogger(ShoppingListController.class);

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private MealPlanRepository mealPlanRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ShoppingList>> getAllSavedShoppingLists() {
        logger.info("In getAllSavedShoppingLists...");
        return ResponseEntity.ok(shoppingListRepository.findAll());
    }

    //Fetch shopping list of Authenticated User

    @GetMapping
    public ResponseEntity<ShoppingList> getUserShoppingList(@AuthenticationPrincipal UserDetails userDetails) {
        logger.info("In getUserShoppingList...");

        if (userDetails == null) {
            logger.error("No authenticated user found. Cannot retrieve shopping list.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = userDetails.getUsername();
        logger.info("Authenticated user: {}", username);

        logger.info("Retrieving userID and shopping list.");

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            logger.error("No user found with username: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = optionalUser.get();
        logger.info("User ID: {}", user.getId());

        Set<ShoppingList> shoppingLists = user.getShoppingLists();

        if (shoppingLists.isEmpty()) {
            logger.info("User has no shopping lists.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            logger.info("Shopping list found.");
            return ResponseEntity.ok(shoppingLists.iterator().next());
        }
    }

    @GetMapping("/{shoppingListId}")
    public ResponseEntity<Optional<ShoppingList>> getSavedShoppingList(@PathVariable("shoppingListId") Long shoppingListId) {
        logger.info("In getSavedShoppingList...");
        if (shoppingListRepository.existsById(shoppingListId)) {
            logger.info("ShoppingList with ID {} found...", shoppingListId);
            return ResponseEntity.ok(shoppingListRepository.findById(shoppingListId));
        } else {
            logger.warn("ShoppingList with ID {} not found...", shoppingListId);
            return ResponseEntity.notFound().build();
        }
    }

    //A method to update the shopping list with the current meal plan

//    @GetMapping("/createOrUpdate")
//    public ResponseEntity<ShoppingList> createOrUpdateShoppingList(@AuthenticationPrincipal UserDetails userDetails) {
//        logger.info("In createOrUpdateShoppingList...");
//
//        if (userDetails == null) {
//            logger.error("No authenticated user found. Cannot create or update meal plan.");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        String username = userDetails.getUsername();
//        logger.info("Authenticated user: {}", username);
//
//        logger.info("Retrieving userID and shopping list.");
//
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//        if (optionalUser.isEmpty()) {
//            logger.error("No user found with username: {}", username);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        User user = optionalUser.get();
//        logger.info("User ID: {}", user.getId());
//
//
//        Set<MealPlan> mealPlans = user.getMealPlans();
//        MealPlan mealPlan;
//
//        if (mealPlans.isEmpty()) {
//            logger.error("User has no saved meal plans");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        } else {
//            mealPlan = mealPlans.iterator().next();
//        }
//
//        //retrieve the user's shopping list and
//
//        Set<ShoppingList> shoppingLists = user.getShoppingLists();
//
//
//        if (shoppingLists.isEmpty()) {
//            logger.info("User has no shopping lists. Creating a new shopping list based on the meal plan...");
//            ShoppingList shoppingList = new ShoppingList()
//        }
//
//        return;
//
//    }

    @PostMapping("/create")
    public ResponseEntity<Object> createShoppingList(@RequestBody @Valid ShoppingList shoppingList, Errors errors) {
        logger.info("In createShoppingList...");
        shoppingListRepository.save(shoppingList);
        if (errors.hasErrors()) {
            logger.error("Error creating shoppingList: {}", errors);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update/{shoppingListId}")
    public ResponseEntity<Object> updateShoppingList(@PathVariable("shoppingListId") Long shoppingListId, @RequestBody @Valid ShoppingList shoppingList) {
        logger.info("In updateShoppingList...");
        if (shoppingListRepository.existsById(shoppingListId)) {
            logger.info("ShoppingList with ID {} found.  Updating...", shoppingListId);
            shoppingListRepository.save(shoppingList);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("ShoppingList with ID {} not found...", shoppingListId);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{shoppingListId}")
    public ResponseEntity<Object> deleteShoppingList(@PathVariable("shoppingListId") Long shoppingListId) {
        logger.info("In deleteShoppingList...");
        if (shoppingListRepository.existsById(shoppingListId)) {
            logger.info("ShoppingList with ID {} found.  Deleting...", shoppingListId);
            shoppingListRepository.deleteById(shoppingListId);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("ShoppingList with ID {} not found...", shoppingListId);
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/shopping-list/{shoppingListId}/sync-with-meal-plan/{mealPlanId}")
    public ResponseEntity<ShoppingList> syncShoppingListWithMealPlan(@PathVariable("shoppingListId") Long shoppingListId, @PathVariable("mealPlanId") Long mealPlanId) {

        logger.info("In syncShoppingListWithMealPlan... shoppingListId={}, mealPlanId={}",
                shoppingListId, mealPlanId);

        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListId);
        if (optionalShoppingList.isEmpty()) {
            logger.warn("ShoppingList with ID {} not found...", shoppingListId);
            return ResponseEntity.notFound().build();
        }
        ShoppingList shoppingList = optionalShoppingList.get();

        Optional<MealPlan> optionalMealPlan = mealPlanRepository.findById(mealPlanId);
        if (optionalMealPlan.isEmpty()) {
            logger.warn("MealPlan with ID {} not found...", mealPlanId);
            return ResponseEntity.notFound().build();
        }
        MealPlan mealPlan = optionalMealPlan.get();

        logger.info("Clearing existing items from ShoppingList ID {}", shoppingListId);
        shoppingList.getShoppingListItems().clear();

        logger.info("Aggregating items from MealPlan ID {}", mealPlanId);

        if (mealPlan.getMealPlanRecipes() != null) {
            mealPlan.getMealPlanRecipes().forEach(mpr -> {
                Recipe recipe = mpr.getRecipe();
                if (recipe != null && recipe.getRecipeIngredients() != null) {
                    recipe.getRecipeIngredients().forEach(recipeIngredient -> {

                        ShoppingListItem item = new ShoppingListItem();
                        item.setIngredient(recipeIngredient.getIngredient());
                        item.setQuantity(recipeIngredient.getQuantity());
                        item.setUnit(recipeIngredient.getUnit());

                        item.setShoppingList(shoppingList);
                        shoppingList.addShoppingListItem(item);

                    });
                }
            });
        }

        shoppingListRepository.save(shoppingList);
        logger.info("ShoppingList ID {} updated successfully from MealPlan ID {}.", shoppingListId, mealPlanId);

        return ResponseEntity.ok(shoppingList);
    }
}
