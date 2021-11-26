package com.cashRegister.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoodsTest {

    private Goods goodsWithOutId;
    private Goods goodsFull;

    @Before
    public void BeforeTest() {
        goodsWithOutId = new Goods("Carrot", 3, 4.5);
        goodsFull = new Goods(3, "Banana", 5, 2.1);
    }

    @Test
    public void getIdGoodsWithOutId() {
        assertEquals(goodsWithOutId.getId(), 0);
    }

    @Test
    public void getIdGoodsFull() {
        assertEquals(goodsFull.getId(), 3);
    }

    @Test
    public void getNameGoodsFull() {
        assertEquals(goodsFull.getName(), "Banana");
    }

    @Test
    public void getAmountGoodsFull() {
        assertEquals(goodsFull.getAmount(), 5);
    }

    @Test
    public void getPriceGoodsFull() {
        assertEquals(goodsFull.getPrice(), 2.1, 0.0);
    }

    @Test
    public void setIdGoodsFull() {
        int newId = 56;
        goodsFull.setId(56);
        assertEquals(goodsFull.getId(), newId);
    }

    @Test
    public void setNameGoodsFull() {
        String newName = "Tune";
        goodsFull.setName(newName);
        assertEquals(goodsFull.getName(), newName);
    }

    @Test
    public void setAmountGoodsFull() {
        int newAmount = 96;
        goodsFull.setAmount(newAmount);
        assertEquals(goodsFull.getAmount(), newAmount);
    }

    @Test
    public void setPriceGoodsFull() {
        double newPrice = 43.1;
        goodsFull.setPrice(newPrice);
        assertEquals(goodsFull.getPrice(), newPrice, 0.0);
    }

    @Test
    public void equalsGoodsWithOutId() {
        assertEquals(goodsWithOutId, new Goods("Carrot", 3, 4.5));
    }

    @Test
    public void egualsGoodsFull() {
        assertEquals(goodsFull, new Goods(3, "Banana", 5, 2.1));
    }

    @Test
    public void equalsGoodsFull() {
        assertNotEquals(goodsFull, new Goods(56, "Banana", 5, 2.1));
        assertNotEquals(goodsFull, new Goods(3, "Test", 5, 2.1));
        assertNotEquals(goodsFull, new Goods(3, "Banana", 2, 2.1));
        assertNotEquals(goodsFull, new Goods(3, "Banana", 5, 5.1));
        assertNotEquals(goodsFull, new Goods(3, null, 5, 2.1));
    }

    @Test
    public void hashGoodsFull() {
        Goods testGoods = new Goods(3, "Banana", 5, 2.1);
        assertEquals(goodsFull.hashCode(), testGoods.hashCode());
    }

    @Test
    public void falseHashGoodsFull() {
        Goods testGoods = new Goods(5, "Banana", 5, 2.1);
        assertNotEquals(goodsFull.hashCode(), testGoods.hashCode());
    }

    @Test
    public void toStringGoodsFull() {
        assertEquals(goodsFull.toString(), "Goods{id=3, name='Banana', amount=5, price=2.1}");
    }
}
