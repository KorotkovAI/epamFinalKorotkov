package com.cashRegister.repository;

import com.cashRegister.exception.ShiftNotFoundException;
import com.cashRegister.model.Shift;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ShiftRepositoryTest {

    private ShiftRepository shiftRepository;

    @Before
    public void beforeStart() {
        shiftRepository = mock(ShiftRepository.class);
    }


    @Test
    public void firstOpenShift() {
        when(shiftRepository.firstOpenShift()).thenCallRealMethod();
        Shift currentShift = shiftRepository.firstOpenShift();
        assertNotNull(currentShift );
    }

    @Test
    public void getAllShifts() {
        when(shiftRepository.getAllShifts()).thenCallRealMethod();
        List<Shift> shiftList = shiftRepository.getAllShifts();
        assertTrue(shiftList.size() > 0);
    }

    @Test
    public void getShiftById() throws ShiftNotFoundException {
        int shiftId = 2;
        when(shiftRepository.getAllShifts()).thenCallRealMethod();
        when(shiftRepository.getShiftById(shiftId)).thenCallRealMethod();
        Shift currentShift = shiftRepository.getShiftById(shiftId);
        assertEquals(currentShift.getId(), shiftId);
    }

    @Test(expected = ShiftNotFoundException.class)
    public void falseGetShiftById() throws ShiftNotFoundException {
        int shiftId = Integer.MAX_VALUE;
        when(shiftRepository.getAllShifts()).thenCallRealMethod();
        when(shiftRepository.getShiftById(shiftId)).thenCallRealMethod();
        shiftRepository.getShiftById(shiftId);
    }
/*
    @Test
    public void closeShift() throws ShiftNotFoundException, IllegalAccessException {
        when(shiftRepository.firstOpenShift()).thenCallRealMethod();
        Shift currentShift = shiftRepository.firstOpenShift();
        int openShiftId = currentShift.getId();
        when(shiftRepository.getAllShifts()).thenCallRealMethod();
        when(shiftRepository.getShiftById(openShiftId)).thenCallRealMethod();
        when(shiftRepository.closeShift(openShiftId)).thenCallRealMethod();
        boolean result = shiftRepository.closeShift(openShiftId);
        assertTrue(result);
    }

 */

    @Test(expected = ShiftNotFoundException.class)
    public void shiftNotFoundCloseShift() throws ShiftNotFoundException, IllegalAccessException {
        int openShiftId = Integer.MAX_VALUE;
        when(shiftRepository.getAllShifts()).thenCallRealMethod();
        when(shiftRepository.getShiftById(openShiftId)).thenCallRealMethod();
        when(shiftRepository.closeShift(openShiftId)).thenCallRealMethod();
        shiftRepository.closeShift(openShiftId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentCloseShift() throws ShiftNotFoundException, IllegalAccessException {
        int openShiftId = Integer.MIN_VALUE;
        when(shiftRepository.getAllShifts()).thenCallRealMethod();
        when(shiftRepository.getShiftById(openShiftId)).thenCallRealMethod();
        when(shiftRepository.closeShift(openShiftId)).thenCallRealMethod();
        shiftRepository.closeShift(openShiftId);
    }
/*
    @Test
    public void OpenShift() {
        when(shiftRepository.openShift()).thenCallRealMethod();
        boolean result = shiftRepository.openShift();
        assertTrue(result);
    }

 */
}
