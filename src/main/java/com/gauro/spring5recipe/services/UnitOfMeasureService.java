package com.gauro.spring5recipe.services;

import com.gauro.spring5recipe.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * @author Chandra
 */
public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
