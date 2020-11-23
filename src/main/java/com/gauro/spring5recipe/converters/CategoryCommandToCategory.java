package com.gauro.spring5recipe.converters;

import com.gauro.spring5recipe.commands.CategoryCommand;
import com.gauro.spring5recipe.domain.Category;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * @author Chandra
 */
@Slf4j
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
         if(source==null)
         {
            log.debug("Null for Catgory");
             return null;

         }

         final Category category=new Category();
         category.setId(source.getId());
         category.setDescription(source.getDescription());
         return category;
    }
}
