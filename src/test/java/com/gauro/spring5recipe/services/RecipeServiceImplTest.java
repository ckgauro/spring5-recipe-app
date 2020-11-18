package com.gauro.spring5recipe.services;

import com.gauro.spring5recipe.domain.Recipe;
import com.gauro.spring5recipe.repositories.RecipeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chandra
 */
class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository);
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
}