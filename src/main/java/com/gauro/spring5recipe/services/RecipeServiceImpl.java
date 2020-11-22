package com.gauro.spring5recipe.services;

import com.gauro.spring5recipe.commands.RecipeCommand;
import com.gauro.spring5recipe.converters.RecipeCommandToRecipe;
import com.gauro.spring5recipe.converters.RecipeToRecipeCommand;
import com.gauro.spring5recipe.domain.Recipe;
import com.gauro.spring5recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Chandra
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;


    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("I'm in service Imp");
        log.info("============>>>>>>>>>>>>");
        Set<Recipe>  recipeSet=new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        log.info("======getRecipes===Starts===>>>>>>>>>>>>");
        log.info(recipeSet.toString());
        log.info("====getRecipes ==Ends======>>>>>>>>>>>>");
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l){
        log.info("============>>>>>>>>>>>>");
        Optional<Recipe> recipeOptional =recipeRepository.findById(l);
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found");
        }
        return  recipeOptional.get();
    }

    @Transactional
    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }


}
