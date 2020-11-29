package com.gauro.spring5recipe.converters;

import com.gauro.spring5recipe.commands.CategoryCommand;
import com.gauro.spring5recipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chandra
 */

class CategoryCommandToCategoryTest {
    public static final Long ID_Value=new Long(1L);
    public  static  final String  DESCRIPTION = "description";

    CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter=new CategoryCommandToCategory();
    }
    @Test
    public  void testNullObject(){
        assertNull(converter.convert(null));
    }
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert(){
        //given
        CategoryCommand categoryCommand=new CategoryCommand();
        categoryCommand.setId(ID_Value);
        categoryCommand.setDescription(DESCRIPTION);

        //when
        Category category=converter.convert(categoryCommand);

        //then
        assertEquals(ID_Value, category.getId());
        assertEquals(DESCRIPTION,category.getDescription());

    }


}