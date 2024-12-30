package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.ShoppingList;
import org.launchcode.PlatePlanner.model.ShoppingListItem;
import org.launchcode.PlatePlanner.repository.ShoppingListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("shoppingListItem")
@CrossOrigin(origins = "http://localhost:5173")
public class ShoppingListItemController {

    @Autowired
    private ShoppingListItemRepository shoppingListItemRepository;

    @GetMapping
    public List<ShoppingListItem> getAllShoppingListItems() {
        return (List<ShoppingListItem>) shoppingListItemRepository.findAll();
    }

    @GetMapping
    public Optional<ShoppingListItem> getSavedShoppingListItem(Long shoppingListItemId) {
        return shoppingListItemRepository.findById(shoppingListItemId);
    }

    @PostMapping()
    public ShoppingListItem createShoppingListItem(@RequestBody @Valid ShoppingListItem shoppingListItem, Errors errors) {
//        TODO: replace this line with appropriate ShoppingListItem logic
        if(errors.hasErrors()) {
            System.out.println(errors);
        }
        return shoppingListItemRepository.save(shoppingListItem);
    }

    @PostMapping()
    public void updateShoppingListItem(@RequestBody @Valid ShoppingListItem shoppingListItem, Long shoppingListItemId) {
        if (shoppingListItemRepository.existsById(shoppingListItemId)) {
            shoppingListItemRepository.save(shoppingListItem);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping
    public void deleteShoppingListItem(Long shoppingListItemId) {
        shoppingListItemRepository.deleteById(shoppingListItemId);
    }


}
