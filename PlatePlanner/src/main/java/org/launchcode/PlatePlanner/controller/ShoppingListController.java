package org.launchcode.PlatePlanner.controller;

import jakarta.validation.Valid;
import org.launchcode.PlatePlanner.model.ShoppingList;
import org.launchcode.PlatePlanner.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("shopping-list")
@CrossOrigin(origins = "http://localhost:5173")
public class ShoppingListController {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @GetMapping("/all")
    public List<ShoppingList> getAllShoppingLists() {
        return (List<ShoppingList>) shoppingListRepository.findAll();
    }

    @GetMapping("/{shoppingListId}")
    public Optional<ShoppingList> getSavedShoppingList(@PathVariable Long shoppingListId) {
        return shoppingListRepository.findById(shoppingListId);
    }

    @PostMapping("/create")
    public ShoppingList createShoppingList(@RequestBody @Valid ShoppingList shoppingList, Errors errors) {
//        TODO: replace this line with appropriate ShoppingList logic
        if(errors.hasErrors()) {
            System.out.println(errors);
        }
        return shoppingListRepository.save(shoppingList);
    }

    @PostMapping("/update/{shoppingListId}")
    public void updateShoppingList(@PathVariable Long shoppingListId, @RequestBody @Valid ShoppingList shoppingList) {
        if (shoppingListRepository.existsById(shoppingListId)) {
            shoppingListRepository.save(shoppingList);
        } else {
//            TODO: throw error;
        }
    }

    @DeleteMapping("/delete/{shoppingListId}")
    public void deleteShoppingList(@PathVariable Long shoppingListId) {
        shoppingListRepository.deleteById(shoppingListId);
    }

}
