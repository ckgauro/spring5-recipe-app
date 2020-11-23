package com.gauro.spring5recipe.converters;

import com.gauro.spring5recipe.commands.NotesCommand;
import com.gauro.spring5recipe.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chandra
 */
class NotesToNotesCommandTest {
    public static final Long ID_VALUE = new Long(1L);
    public static final String RECIPE_NOTES = "Notes";
    NotesToNotesCommand converter;
    @BeforeEach
    void setUp() {
        converter=new NotesToNotesCommand();
    }
    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }


    @Test
    void convert() {
        //given
        Notes notes=new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand notesCommand=converter.convert(notes);

        //then
        assertEquals(ID_VALUE,notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}