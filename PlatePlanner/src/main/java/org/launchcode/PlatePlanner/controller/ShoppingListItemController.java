package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.ShoppingListItem;
import org.launchcode.PlatePlanner.repository.ShoppingListItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("shopping-list-item")
@CrossOrigin(origins = "http://localhost:5173")
public class ShoppingListItemController {

    Logger logger = LoggerFactory.getLogger(ShoppingListItemController.class);

    @Autowired
    private ShoppingListItemRepository shoppingListItemRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ShoppingListItem>> getAllSavedShoppingListItems() {
        logger.info("In getAllSavedShoppingListItems...");
        return ResponseEntity.ok(shoppingListItemRepository.findAll());
    }

    @GetMapping("/{shoppingListItemId}")
    public ResponseEntity<Optional<ShoppingListItem>> getSavedShoppingListItem(@PathVariable("shoppingListItemId") Long shoppingListItemId) {
        logger.info("In getSavedShoppingListItem...");
        if (shoppingListItemRepository.existsById(shoppingListItemId)) {
            logger.info("ShoppingListItem with ID {} found...", shoppingListItemId);
            return ResponseEntity.ok(shoppingListItemRepository.findById(shoppingListItemId));
        } else {
            logger.warn("ShoppingListItem with ID {} not found...", shoppingListItemId);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createShoppingListItem(@RequestBody @Valid ShoppingListItem shoppingListItem, Errors errors) {
        logger.info("In createShoppingListItem...");
        shoppingListItemRepository.save(shoppingListItem);
        if (errors.hasErrors()) {
            logger.error("Error creating shoppingListItem: {}", errors);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update/{shoppingListItemId}")
    public ResponseEntity<Object> updateShoppingListItem(@PathVariable("shoppingListItemId") Long shoppingListItemId, @RequestBody @Valid ShoppingListItem shoppingListItem) {
        logger.info("In updateShoppingListItem...");
        if (shoppingListItemRepository.existsById(shoppingListItemId)) {
            logger.info("ShoppingListItem with ID {} found.  Updating...", shoppingListItemId);
            shoppingListItemRepository.save(shoppingListItem);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("ShoppingListItem with ID {} not found...", shoppingListItemId);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{shoppingListItemId}")
    public ResponseEntity<Object> deleteShoppingListItem(@PathVariable("shoppingListItemId") Long shoppingListItemId) {
        logger.info("In deleteShoppingListItem...");
        if (shoppingListItemRepository.existsById(shoppingListItemId)) {
            logger.info("ShoppingListItem with ID {} found.  Deleting...", shoppingListItemId);
            shoppingListItemRepository.deleteById(shoppingListItemId);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("ShoppingListItem with ID {} not found...", shoppingListItemId);
            return ResponseEntity.notFound().build();
        }
    }

}
