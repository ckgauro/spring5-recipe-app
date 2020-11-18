package com.gauro.spring5recipe.controllers;

import com.gauro.spring5recipe.domain.Category;
import com.gauro.spring5recipe.domain.UnitOfMeasure;
import com.gauro.spring5recipe.repositories.CategoryRepository;
import com.gauro.spring5recipe.repositories.UnitOfMeasureRepository;
import com.gauro.spring5recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author Chandra
 */
@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        log.debug("Getting index page");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";


    }
}
