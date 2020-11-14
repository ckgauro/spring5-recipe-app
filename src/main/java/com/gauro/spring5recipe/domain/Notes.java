package com.gauro.spring5recipe.domain;

import javax.persistence.*;

/**
 * @author Chandra
 */
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;
    @Lob
    private Long recipeNotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Long getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(Long recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}