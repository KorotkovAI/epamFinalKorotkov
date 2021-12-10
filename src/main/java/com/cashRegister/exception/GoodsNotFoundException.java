package com.cashRegister.exception;

/**
 * This class implements its own error with find goods
 */
public class GoodsNotFoundException extends Exception{
    public GoodsNotFoundException() {
    }

    public GoodsNotFoundException(String message) {
        super(message);
    }
}
