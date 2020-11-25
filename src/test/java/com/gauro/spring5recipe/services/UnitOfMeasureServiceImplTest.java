package com.gauro.spring5recipe.services;

import com.gauro.spring5recipe.commands.UnitOfMeasureCommand;
import com.gauro.spring5recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.gauro.spring5recipe.domain.UnitOfMeasure;
import com.gauro.spring5recipe.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Chandra
 */
class UnitOfMeasureServiceImplTest {
    UnitOfMeasureService unitOfMeasureService;
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void ListAllUoms()  throws Exception{
        //given
        Set<UnitOfMeasure> unitOfMeasures=new HashSet<>();
        UnitOfMeasure uom1=new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2=new UnitOfMeasure();
        uom1.setId(2L);
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitOfMeasureCommand> commands=unitOfMeasureService.listAllUoms();

        //then
        assertEquals(2, commands.size());
        verify(unitOfMeasureRepository,times(1)).findAll();

    }
}