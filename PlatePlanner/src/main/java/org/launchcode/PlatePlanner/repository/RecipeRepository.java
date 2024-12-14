package org.launchcode.PlatePlanner.repository;

import org.launchcode.PlatePlanner.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
