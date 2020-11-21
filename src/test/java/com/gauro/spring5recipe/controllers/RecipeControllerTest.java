package com.gauro.spring5recipe.controllers;

import static org.mockito.Mockito.when;
import com.gauro.spring5recipe.domain.Recipe;
import com.gauro.spring5recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.anyLong;
/**
 * @author Chandra
 */
class RecipeControllerTest {
    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    @BeforeEach
    public void setUp() throws  Exception{
        MockitoAnnotations.initMocks(this);
        recipeController=new RecipeController(recipeService);

    }
    @Test
    public void testGetRecipe() throws Exception {
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();
        when(recipeService.findById(anyLong())).thenReturn(recipe);
        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
    }
    @Test
    void showById() {
    }
}