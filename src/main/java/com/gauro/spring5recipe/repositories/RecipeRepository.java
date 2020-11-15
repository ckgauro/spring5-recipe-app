package com.gauro.spring5recipe.repositories;

import com.gauro.spring5recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Chandra
 */
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
}
