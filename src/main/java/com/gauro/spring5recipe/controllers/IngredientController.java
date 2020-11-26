package com.gauro.spring5recipe.controllers;

import com.gauro.spring5recipe.commands.IngredientCommand;
import com.gauro.spring5recipe.commands.RecipeCommand;
import com.gauro.spring5recipe.commands.UnitOfMeasureCommand;
import com.gauro.spring5recipe.services.IngredientService;
import com.gauro.spring5recipe.services.RecipeService;
import com.gauro.spring5recipe.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chandra
 */
@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Recipe ingredients is called =====> ");
        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,@PathVariable String id, Model model){
        log.debug("Getting ingredient list for recipe id:"+recipeId);

        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        return "recipe/ingredient/show";

    }


    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model){
        //make sure we have a good id value
        RecipeCommand recipeCommand=recipeService.findCommandById(Long.valueOf(recipeId));

        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand=new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient",ingredientCommand);

        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList",unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }



    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),
                Long.valueOf(id)));
        log.debug("showRecipeIngredient recipe id {} and ingredient id {}:",recipeId,id);
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";

    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand=ingredientService.saveIngredientCommand(command);

        log.debug("saved recipe id:"+savedCommand.getRecipeId());
        log.debug("saved ingredients id:"+savedCommand.getId());
        return "redirect:/recipe/"+savedCommand.getRecipeId()+ "/ingredient/" + savedCommand.getId()+"/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id){

        log.debug("deleting ingredient id:" + id);
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }


}
