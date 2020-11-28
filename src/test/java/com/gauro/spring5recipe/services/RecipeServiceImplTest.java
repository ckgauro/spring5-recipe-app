package com.gauro.spring5recipe.services;

import com.gauro.spring5recipe.controllers.NotFoundException;
import com.gauro.spring5recipe.converters.RecipeCommandToRecipe;
import com.gauro.spring5recipe.converters.RecipeToRecipeCommand;
import com.gauro.spring5recipe.domain.Recipe;
import com.gauro.spring5recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chandra
 */
@Slf4j
class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRecipes() {
        Recipe recipe=new Recipe();
        HashSet recipesData=new HashSet();
        recipesData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipes=recipeService.getRecipes();

        assertEquals(recipes.size(),1);
        verify(recipeRepository,times(1)).findAll();
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional=Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        //assertNotNull("Null recipe returned", (Supplier<String>) recipeReturned);
        assertNotNull("Null recipe returned",recipeReturned.toString());
        log.debug("========assertNotNull=====recipeReturned========");
        log.info(recipeReturned.toString());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();


    }

    @Test
    public void getRecipeByIdTestNotFound() throws Exception {

        Assertions.assertThrows(NotFoundException.class, () -> {

            Optional<Recipe> recipeOptional = Optional.empty();

            when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

            Recipe recipeReturned = recipeService.findById(1L);

        });

        //should go boom
    }

    @Test
    public void getRecipesTest() throws Exception {

        Recipe recipe = new Recipe();
        HashSet receipesData = new HashSet();
        receipesData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(receipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }



}