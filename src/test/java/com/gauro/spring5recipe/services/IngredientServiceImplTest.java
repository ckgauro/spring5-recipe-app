package com.gauro.spring5recipe.services;


import com.gauro.spring5recipe.commands.IngredientCommand;
import com.gauro.spring5recipe.converters.IngredientCommandToIngredient;
import com.gauro.spring5recipe.converters.IngredientToIngredientCommand;
import com.gauro.spring5recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.gauro.spring5recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.gauro.spring5recipe.domain.Ingredient;
import com.gauro.spring5recipe.domain.Recipe;
import com.gauro.spring5recipe.repositories.RecipeRepository;
import com.gauro.spring5recipe.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


/**
 * @author Chandra
 */
class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    //init converters
    IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient=new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService=new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient, recipeRepository, unitOfMeasureRepository);
    }
    @Test
    public void findByRecipeIdAndId(){

    }
    @Test
    public void findByRecipeIdAndReceipeIdHappyPath() throws Exception {
        //given
        Recipe recipe=new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1=new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2=new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3=new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);


        Optional<Recipe> recipeOptional=Optional.of(recipe);

        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand=ingredientService.findByRecipeIdAndIngredientId(1L,2L);

        assertEquals(2L, ingredientCommand.getId());
        assertEquals(1L,recipeOptional.get().getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());

        verify(recipeRepository, times(1)).findById(anyLong());

    }
    @Test
    public void testSaveRecipeCommand() throws Exception {
        //given
        IngredientCommand command=new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional=Optional.of(new Recipe());
        Recipe savedRecipe=new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when (recipeRepository.save(any())).thenReturn(savedRecipe);


        //when

        IngredientCommand savedCommand=ingredientService.saveIngredientCommand(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any(Recipe.class));

    }
}