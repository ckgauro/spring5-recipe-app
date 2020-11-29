package com.gauro.spring5recipe.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Chandra
 */

@NoArgsConstructor
@Getter
@Setter
public class NotesCommand {
    private Long id;
    private String recipeNotes;
}
