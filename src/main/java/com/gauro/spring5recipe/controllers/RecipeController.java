package com.gauro.spring5recipe.controllers;

import com.gauro.spring5recipe.commands.RecipeCommand;
import com.gauro.spring5recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author Chandra
 */
@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        log.debug("showById is called ======>"+id);
      //  model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        log.debug("Recipe show is calling showById====================>"+id);
        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        log.debug("Recipe show is calling showById data fetched=======******============>");
        log.info(recipeService.findById(Long.valueOf(id)).toString());
        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        log.debug("newRecipe is called ======>");
        model.addAttribute("recipe", new RecipeCommand());
        return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        log.debug("===============recipe>>>>>>"+id);
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        log.debug(model.toString());
        return RECIPE_RECIPEFORM_URL;

    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            return RECIPE_RECIPEFORM_URL;
        }

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
    public ModelAndView handleNotFound(Exception exception){
        log.error("Handling not found exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception",exception);
        return modelAndView;
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(NumberFormatException.class)
//    public ModelAndView  handleNumberFormat(Exception exception){
//        log.error("Handling Number Format Exception");
//        log.error(exception.getMessage());
//
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("400error");
//        modelAndView.addObject("exception",exception);
//        return  modelAndView;
//    }



}
