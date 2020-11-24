package com.gauro.spring5recipe.services;

import com.gauro.spring5recipe.commands.RecipeCommand;
import com.gauro.spring5recipe.domain.Recipe;

import java.util.Set;

/**
 * @author Chandra
 */
public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long l);
    RecipeCommand findCommandById(Long l);
    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);
}
