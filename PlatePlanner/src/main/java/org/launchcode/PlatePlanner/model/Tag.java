package org.launchcode.PlatePlanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag extends AbstractEntity {

    @ManyToMany(mappedBy = "tags")
    @NotNull
    @JsonIgnoreProperties({"tags"})
    private Set<Recipe> recipes = new HashSet<>();

    @NotNull
    @Length(max = 20, message = "Tag name cannot exceed 20 characters.")
    @Column(unique = true)
    private String name;

    @Length(max = 150, message = "Tag description cannot exceed 150 characters.")
    private String description;

    public Tag() {}

    public Tag(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Tag(String name) {
        this.name = name;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe) {
        if (recipe != null) {
            recipes.add(recipe);
            recipe.getTags().add(this);
        }
    }

    public void removeRecipe(Recipe recipe) {
        if (recipe != null) {
            recipes.remove(recipe);
            recipe.getTags().remove(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
