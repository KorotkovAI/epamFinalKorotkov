package com.cashRegister.repository;

import com.cashRegister.model.Goods;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CheckGoodsRepositoryTest {

    private CheckGoodsRepository checkGoodsRepository;

    @Before
    public void beforeStart() {
        checkGoodsRepository = mock(CheckGoodsRepository.class);
    }

    @Test
    public void returnCheckGoods() {
        int checkId = 1;
        when(checkGoodsRepository.returnCheckGoods(checkId)).thenCallRealMethod();
        List<Goods> result = checkGoodsRepository.returnCheckGoods(checkId);
        assertTrue(result.size() > 0);
    }

    @Test
    public void checkNotInDBReturnCheckGoods() {
        int checkId = Integer.MAX_VALUE;
        when(checkGoodsRepository.returnCheckGoods(checkId)).thenCallRealMethod();
        List<Goods> result = checkGoodsRepository.returnCheckGoods(checkId);
        assertFalse(result.size() > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentReturnCheckGoods() {
        int checkId = Integer.MIN_VALUE;
        when(checkGoodsRepository.returnCheckGoods(checkId)).thenCallRealMethod();
        List<Goods> result = checkGoodsRepository.returnCheckGoods(checkId);
        assertFalse(result.size() > 0);
    }
}
