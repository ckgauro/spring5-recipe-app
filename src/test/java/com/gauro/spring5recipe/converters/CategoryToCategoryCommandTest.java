package com.gauro.spring5recipe.converters;

import com.gauro.spring5recipe.commands.CategoryCommand;
import com.gauro.spring5recipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chandra
 */
class CategoryToCategoryCommandTest {

    public static final Long ID_VALUE=new Long(1L);
    public static final String DESCRIPTION = "descript";

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter=new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Category()));
    }
    @Test
    public void convert() throws Exception {
        //given
        Category category=new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryCommand categoryCommand=converter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION,categoryCommand.getDescription());


    }


}