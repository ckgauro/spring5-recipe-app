package com.gauro.spring5recipe.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chandra
 */
class CategoryTest {

    Category category;

    @BeforeEach
    void setUp() {
        category=new Category();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getId() throws  Exception {
        Long idValue=4L;
        category.setId(idValue);
        assertEquals(idValue,
                category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}