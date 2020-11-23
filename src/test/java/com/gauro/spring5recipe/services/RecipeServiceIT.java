package com.gauro.spring5recipe.services;

import com.gauro.spring5recipe.commands.RecipeCommand;
import com.gauro.spring5recipe.converters.RecipeToRecipeCommand;
import com.gauro.spring5recipe.domain.Recipe;
import com.gauro.spring5recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chandra
 */
@Slf4j
@SpringBootTest
class RecipeServiceIT {
    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        // given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        log.debug("=========testSaveOfDescription========>>>>>>");
        log.debug(testRecipe.toString());
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand saveRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION, saveRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(),saveRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), saveRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), saveRecipeCommand.getIngredients().size());


    }

}