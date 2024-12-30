package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.ShoppingListItem;
import org.launchcode.PlatePlanner.repository.ShoppingListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("shopping-list-item")
@CrossOrigin(origins = "http://localhost:5173")
public class ShoppingListItemController {

    @Autowired
    private ShoppingListItemRepository shoppingListItemRepository;

    @GetMapping("/all")
    public List<ShoppingListItem> getAllShoppingListItems() {
        return (List<ShoppingListItem>) shoppingListItemRepository.findAll();
    }

    @GetMapping("/{shoppingListItemId}")
    public Optional<ShoppingListItem> getSavedShoppingListItem(@PathVariable Long shoppingListItemId) {
        return shoppingListItemRepository.findById(shoppingListItemId);
    }

    @PostMapping("/create")
    public ShoppingListItem createShoppingListItem(@RequestBody @Valid ShoppingListItem shoppingListItem, Errors errors) {
//        TODO: replace this line with appropriate ShoppingList logic
        if(errors.hasErrors()) {
            System.out.println(errors);
        }
        return shoppingListItemRepository.save(shoppingListItem);
    }

    @PostMapping("/update/{shoppingListItemId}")
    public void updateShoppingListItem(@PathVariable Long shoppingListItemId, @RequestBody @Valid ShoppingListItem shoppingListItem) {
        if (shoppingListItemRepository.existsById(shoppingListItemId)) {
            shoppingListItemRepository.save(shoppingListItem);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping("/delete/{shoppingListItemId}")
    public void deleteShoppingListItem(@PathVariable Long shoppingListItemId) {
        shoppingListItemRepository.deleteById(shoppingListItemId);
    }

}
