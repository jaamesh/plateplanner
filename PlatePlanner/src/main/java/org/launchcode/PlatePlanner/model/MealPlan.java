package org.launchcode.PlatePlanner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MealPlan extends AbstractEntity {

    @ManyToOne
    @NotNull
    private User user;

    @ManyToMany
    @JoinTable(
            name = "mealplan_recipe",
            joinColumns = @JoinColumn(name = "mealplan_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes = new ArrayList<>();

    @OneToOne
    private ShoppingList shoppingList;

    @NotNull
    @Length(max = 50, message = "Meal Plan name cannot exceed 50 characters.")
    private String name;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private LocalDateTime createdAt;

    public MealPlan() {}

    public MealPlan(User user, List<Recipe> recipes, ShoppingList shoppingList, String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.user = user;
        this.recipes = recipes;
        this.shoppingList = shoppingList;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Meal Plan: " + this.name + ", created by " + (this.user != null ? this.user.toString() : "Unknown User") + " on " + this.createdAt;
    }
}
