package com.gauro.spring5recipe.services;

import com.gauro.spring5recipe.domain.Recipe;
import com.gauro.spring5recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
}
