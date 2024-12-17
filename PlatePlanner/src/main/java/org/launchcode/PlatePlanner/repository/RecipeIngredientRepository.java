package org.launchcode.PlatePlanner.repository;

import org.launchcode.PlatePlanner.model.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Long> {
}
