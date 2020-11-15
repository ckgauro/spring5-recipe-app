package com.gauro.spring5recipe.repositories;

import com.gauro.spring5recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Chandra
 */
public interface CategoryRepository  extends CrudRepository<Category, Long> {
}
