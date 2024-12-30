package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.ShoppingList;
import org.launchcode.PlatePlanner.repository.ShoppingListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("shopping-list")
@CrossOrigin(origins = "http://localhost:5173")
public class ShoppingListController {

    Logger logger = LoggerFactory.getLogger(ShoppingListController.class);

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ShoppingList>> getAllSavedShoppingLists() {
        logger.info("In getAllSavedShoppingLists...");
        return ResponseEntity.ok(shoppingListRepository.findAll());
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

}
