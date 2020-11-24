package com.gauro.spring5recipe.services;

import com.gauro.spring5recipe.commands.IngredientCommand;

/**
 * @author Chandra
 */
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
