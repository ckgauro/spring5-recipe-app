package com.gauro.spring5recipe.repositories;

import com.gauro.spring5recipe.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Chandra
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {
   Optional<UnitOfMeasure> findByDescription(String description);
}
