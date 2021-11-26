package com.cashRegister.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class ShiftTest {
    private Shift shiftWithId;
    private Shift shiftOnlyWithTime;
    private Timestamp openTime = Timestamp.valueOf("2021-11-26 12:36:48.0");
    private Timestamp closeTime = Timestamp.valueOf("2021-11-27 08:15:12.0");


    @Before
    public void BeforeTest() {
        shiftOnlyWithTime = new Shift();
        shiftWithId = new Shift(2, true, openTime, closeTime);
    }

    @After
    public void AfterTest() {
        shiftWithId = null;
        shiftOnlyWithTime = null;
    }

    @Test
    public void getIdShiftWithId() {
        assertEquals(shiftWithId.getId(), 2);
    }

    @Test
    public void getIdshiftOnlyWithTime() {
        assertEquals(shiftOnlyWithTime.getId(), 0);
    }

    @Test
    public void getIsOpenShiftWithId() {
        assertEquals(shiftWithId.isOpen(), true);
    }

    @Test
    public void getIsOpenShiftOnlyWithTime() {
        assertEquals(shiftOnlyWithTime.isOpen(), true);
    }

    @Test
    public void getOpenTimeShiftWithId() {
        assertEquals(shiftWithId.getOpenTime(), openTime);
    }

    @Test
    public void getCloseTimeShiftWithId() {
        assertEquals(shiftWithId.getCloseTime(), closeTime);
    }

    @Test
    public void setIdShiftWithId() {
        int newId = 5;
        shiftWithId.setId(newId);
        assertEquals(shiftWithId.getId(), newId);
    }

    @Test
    public void setIsOpenShiftWithId() {
        shiftWithId.setOpen(false);
        assertEquals(shiftWithId.isOpen(), false);
    }

    @Test
    public void setOpenTimeShiftWithId() {
        Timestamp newOpenTime = Timestamp.valueOf("2011-11-26 11:16:48.0");
        shiftWithId.setOpenTime(newOpenTime);
        assertEquals(shiftWithId.getOpenTime(), newOpenTime);
    }

    @Test
    public void setCloseTimeShiftWithId() {
        Timestamp newCloseTime = Timestamp.valueOf("2011-11-26 11:16:48.0");
        shiftWithId.setCloseTime(newCloseTime);
        assertEquals(shiftWithId.getCloseTime(), newCloseTime);
    }

    @Test
    public void equalsShiftWithId() {
        assertEquals(shiftWithId, new Shift(2, true, openTime, closeTime));
    }

    @Test
    public void notEqualsShiftWithId() {
        assertNotEquals(shiftWithId, new Shift(3, true, openTime, closeTime));
        assertNotEquals(shiftWithId, new Shift(2, false, openTime, closeTime));
        assertNotEquals(shiftWithId, new Shift(2, true, null, closeTime));
        assertNotEquals(shiftWithId, new Shift(2, true, openTime, null));
        assertNotEquals(shiftWithId, new Shift(2, true, Timestamp.valueOf("2011-11-26 11:16:48.0"), closeTime));
        assertNotEquals(shiftWithId, new Shift(2, true, openTime, Timestamp.valueOf("2011-11-26 11:16:48.0")));

    }

    @Test
    public void hashShiftWithId() {
        Shift testShift = new Shift(2, true, openTime, closeTime);
        assertEquals(shiftWithId.hashCode() , testShift.hashCode());
    }

    @Test
    public void falseHashShiftWithId() {
        Shift testShift = new Shift(3, true, openTime, closeTime);
        assertNotEquals(shiftWithId.hashCode() , testShift.hashCode());
    }

    @Test
    public void toStringShiftWithId() {
        assertEquals(shiftWithId.toString(), "Shift{id=2, isOpen=true, openTime=2021-11-26 12:36:48.0, closeTime=2021-11-27 08:15:12.0}");
    }
}
