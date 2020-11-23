package com.gauro.spring5recipe.controllers;

import com.gauro.spring5recipe.commands.RecipeCommand;
import com.gauro.spring5recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Chandra
 */
@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){
        log.debug("showById is called ======>"+id);
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        log.info(recipeService.findById(Long.valueOf(id)).toString());

        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        log.debug("newRecipe is called ======>");
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }
    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand saveCommand=recipeService.saveRecipeCommand(command);
        log.debug("redirect:/recipe/show/  is calling======>"+saveCommand.getId());
        return "redirect:/recipe/show/"+saveCommand.getId();
    }
}
