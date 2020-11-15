package com.gauro.spring5recipe.repositories;

import com.gauro.spring5recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Chandra
 */
public interface CategoryRepository  extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
