package com.gauro.spring5recipe.controllers;

import com.gauro.spring5recipe.commands.RecipeCommand;
import com.gauro.spring5recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        log.debug("showById is called ======>"+id);
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        log.info(recipeService.findById(Long.valueOf(id)).toString());
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        log.debug("newRecipe is called ======>");
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        log.debug("===============recipe>>>>>>"+id);
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        log.debug(model.toString());
        return "recipe/recipeform";

    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand saveCommand=recipeService.saveRecipeCommand(command);
        log.debug("redirect:/recipe/show/  is calling======>"+saveCommand.getId());

        return "redirect:/recipe/"+saveCommand.getId()+"/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id:"+id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(){
        log.error("Handling not found exception");
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("404error");
        return modelAndView;
    }


}
