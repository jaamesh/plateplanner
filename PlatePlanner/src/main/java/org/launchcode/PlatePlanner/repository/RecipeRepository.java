package org.launchcode.PlatePlanner.repository;

import org.launchcode.PlatePlanner.model.Recipe;
import org.launchcode.PlatePlanner.model.Tag;
import org.launchcode.PlatePlanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    public List<Recipe> findByUser(User user);

    public List<Recipe> findByNameAndUser(String name, User user);

}
