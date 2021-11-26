package com.cashRegister.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class CheckTest {

    private Check checkWithoutId;
    private Check checkFull;
    private final Timestamp timestamp = Timestamp.valueOf("2021-11-26 12:36:48.0");
    private final Shift currentShift = new Shift(2, true, Timestamp.valueOf("2021-11-26 12:36:48.0"), Timestamp.valueOf("2021-11-27 08:15:12.0"));
    private final User currentUser = new User(4, "root", "tt333s!", "Ivan", "Petrov", new Role("Tester"));

    @Before
    public void BeforeTest() {
        checkFull = new Check(5, 45.1, timestamp, false, currentShift, currentUser);
        checkWithoutId = new Check(45.1, timestamp, false, currentShift, currentUser);
    }

    @After
    public void AfterTest() {
        checkFull = null;
        checkWithoutId = null;
    }

    @Test
    public void getIdCheckWithoutId() {
        assertEquals(checkWithoutId.getId(), 0);
    }

    @Test
    public void getIdCheckFull() {
        assertEquals(checkFull.getId(), 5);
    }

    @Test
    public void getSumCheckFull() {
        assertEquals(checkFull.getSum(), 45.1, 0.0);
    }

    @Test
    public void getTimeCheckFull() {
        assertEquals(checkFull.getTimestamp(), Timestamp.valueOf("2021-11-26 12:36:48.0"));
    }

    @Test
    public void getIsReturnedCheckFull() {
        assertEquals(checkFull.isReturned(), false);
    }

    @Test
    public void getShiftCheckFull() {
        assertEquals(checkFull.getShiftId(), currentShift);
    }

    @Test
    public void getUserCheckFull() {
        assertEquals(checkFull.getUserId(), currentUser);
    }

    @Test
    public void setIdCheckFull() {
        int newId = 55;
        checkFull.setId(newId);
        assertEquals(checkFull.getId(), newId);
    }

    @Test
    public void setSumCheckFull() {
        double newSum = 888.8;
        checkFull.setSum(newSum);
        assertEquals(checkFull.getSum(), newSum, 0.0);
    }

    @Test
    public void setTimeCheckFull() {
        Timestamp newTime = Timestamp.valueOf("2015-11-26 12:36:48.0");
        checkFull.setTimestamp(newTime);
        assertEquals(checkFull.getTimestamp(), newTime);
    }

    @Test
    public void setShiftCheckFull() {
        Shift newShift = new Shift(5, false, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
        checkFull.setShiftId(newShift);
        assertEquals(checkFull.getShiftId(), newShift);
    }

    @Test
    public void setUserCheckFull() {
        User newUser = new User(6, "petr", "bbbb666W", "Petr", "Petrov", new Role("Tester"));
        checkFull.setUserId(newUser);
        assertEquals(checkFull.getUserId(), newUser);
    }

    @Test
    public void setIsreturned() {
        boolean newIsReturned = true;
        checkFull.setReturned(newIsReturned);
        assertEquals(checkFull.isReturned(), newIsReturned);
    }

    @Test
    public void equalsCheckFull() {
        assertEquals(checkFull, new Check(5, 45.1, timestamp, false, currentShift, currentUser));
    }

    @Test
    public void notEqualsCheckFull() {
        assertNotEquals(checkFull, new Check(6, 45.1, timestamp, false, currentShift, currentUser));
        assertNotEquals(checkFull, new Check(5, 5.1, timestamp, false, currentShift, currentUser));
        assertNotEquals(checkFull, new Check(5, 45.1, null, false, currentShift, currentUser));
        assertNotEquals(checkFull, new Check(5, 45.1, timestamp, true, currentShift, currentUser));
        assertNotEquals(checkFull, new Check(5, 45.1, timestamp, false, null, currentUser));
        assertNotEquals(checkFull, new Check(5, 45.1, timestamp, false, currentShift, null));
    }

    @Test
    public void falseHashCheckFull() {
        Check testCheck = new Check(5, 5.1, timestamp, true, currentShift, currentUser);
        assertNotEquals(checkFull.hashCode(), testCheck.hashCode());
    }

    @Test
    public void hashCheckFull() {
        Check testCheck = new Check(5, 45.1, timestamp, false, currentShift, currentUser);
        assertEquals(testCheck.hashCode(), checkFull.hashCode());
    }

    @Test
    public void toStringCheckFull() {
        assertEquals(checkFull.toString(), "Check{id=5, sum=45.1, timestamp=2021-11-26 12:36:48.0, isReturned=false, shiftId=Shift{id=2, isOpen=true, openTime=2021-11-26 12:36:48.0, closeTime=2021-11-27 08:15:12.0}, userId=User{id=4, login='root', password='tt333s!', name='Ivan', surname='Petrov', roleName=Role{id=0, name='Tester'}}}");
    }
}
