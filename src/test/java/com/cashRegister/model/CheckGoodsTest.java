package com.cashRegister.model;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class CheckGoodsTest {

    private CheckGoods checkGoodsWithOutId;
    private CheckGoods checkGoodsFull;
    private final Timestamp timestamp = Timestamp.valueOf("2021-11-26 12:36:48.0");
    private final Shift currentShift = new Shift(2, true, Timestamp.valueOf("2021-11-26 12:36:48.0"), Timestamp.valueOf("2021-11-27 08:15:12.0"));
    private final User currentUser = new User(4, "root", "tt333s!", "Ivan", "Petrov", new Role("Tester"));
    private final Check check = new Check(5, 45.1, timestamp, false, currentShift, currentUser);

    @Before
    public void beforeTest() {
        checkGoodsFull = new CheckGoods(3, 45, "Paper", 2, 5.5, check);
        checkGoodsWithOutId = new CheckGoods(2, "Stone", 1, 45.2, check);
    }

    @Test
    public void getIdCheckGoodsFull() {
        assertEquals(checkGoodsFull.getId(), 3);
    }

    @Test
    public void getIdCheckGoodsWithOutId() {
        assertEquals(checkGoodsWithOutId.getId(), 0);
    }

    @Test
    public void getIdGoodsCheckGoodsFull() {
        assertEquals(checkGoodsFull.getIdGoods(), 45);
    }

    @Test
    public void getNameGoodsCheckGoodsFull() {
        assertEquals(checkGoodsFull.getNameGoods(), "Paper");
    }

    @Test
    public void getAmountGoodsCheckGoodsFull() {
        assertEquals(checkGoodsFull.getAmountGoods(), 2);
    }

    @Test
    public void getPriceGoodsCheckGoodsFull() {
        assertEquals(checkGoodsFull.getPriceGoods(), 5.5, 0.0);
    }

    @Test
    public void getCheckCheckGoodsFull() {
        assertEquals(checkGoodsFull.getIdCheck(), check);
    }

    @Test
    public void setIdCheckGoodsFull() {
        int newId = 67;
        checkGoodsFull.setId(newId);
        assertEquals(checkGoodsFull.getId(), newId);
    }

    @Test
    public void setIdGoodsCheckGoodsFull() {
        int newIdGoods = 678;
        checkGoodsFull.setIdGoods(newIdGoods);
        assertEquals(checkGoodsFull.getIdGoods(), newIdGoods);
    }

    @Test
    public void setNameGoodsCheckGoodsFull() {
        String newName = "Coca-cola";
        checkGoodsFull.setNameGoods(newName);
        assertEquals(checkGoodsFull.getNameGoods(), newName);
    }

    @Test
    public void setAmountGoodsCheckGoodsFull() {
        int newAmount = 33;
        checkGoodsFull.setAmountGoods(newAmount);
        assertEquals(checkGoodsFull.getAmountGoods(), newAmount);
    }

    @Test
    public void setPriceGoodsCheckGoodsFull() {
        double newPrice = 67.7;
        checkGoodsFull.setPriceGoods(newPrice);
        assertEquals(checkGoodsFull.getPriceGoods(), newPrice, 0.0);
    }

    @Test
    public void setCheckCheckGoodsFull() {
        Check newCheck = new Check(2, 5.8, timestamp, true, currentShift, currentUser);
        checkGoodsFull.setIdCheck(newCheck);
        assertEquals(checkGoodsFull.getIdCheck(), newCheck);
    }

    @Test
    public void equalsCheckGoodsFull() {
        CheckGoods newCheckGoods = new CheckGoods(3, 45, "Paper", 2, 5.5, check);
        assertEquals(checkGoodsFull, newCheckGoods);
    }

    @Test
    public void falseEqualseCheckGoodsFull() {
        assertNotEquals(checkGoodsFull, new CheckGoods(6, 45, "Paper", 2, 5.5, check));
        assertNotEquals(checkGoodsFull, new CheckGoods(3, 5, "Paper", 2, 5.5, check));
        assertNotEquals(checkGoodsFull, new CheckGoods(3, 45, "Magazine", 2, 5.5, check));
        assertNotEquals(checkGoodsFull, new CheckGoods(3, 45, "Paper", 3, 5.5, check));
        assertNotEquals(checkGoodsFull, new CheckGoods(3, 45, "Paper", 2, 0.5, check));
        assertNotEquals(checkGoodsFull, new CheckGoods(3, 45, "Paper", 2, 5.5, null));
    }

    @Test
    public void hashCheckGoodsFull() {
        CheckGoods testCheckGoods = new CheckGoods(3, 45, "Paper", 2, 5.5, check);
        assertEquals(checkGoodsFull.hashCode(), testCheckGoods.hashCode());
    }

    @Test
    public void falseHashCheckGoodsFull() {
        CheckGoods testCheckGoods = new CheckGoods(4, 45, "Paper", 2, 5.5, check);
        assertNotEquals(checkGoodsFull.hashCode(), testCheckGoods.hashCode());
    }

    @Test
    public void toStringCheckGoodsFull() {
        assertEquals(checkGoodsFull.toString(), "CheckGoods{id=3, idGoods=45, nameGoods='Paper', amountGoods=2, priceGoods=5.5, idCheck=Check{id=5, sum=45.1, timestamp=2021-11-26 12:36:48.0, isReturned=false, shiftId=Shift{id=2, isOpen=true, openTime=2021-11-26 12:36:48.0, closeTime=2021-11-27 08:15:12.0}, userId=User{id=4, login='root', password='tt333s!', name='Ivan', surname='Petrov', roleName=Role{id=0, name='Tester'}}}}");
    }

}
