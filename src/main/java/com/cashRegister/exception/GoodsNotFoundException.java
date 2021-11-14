package com.cashRegister.exception;

public class GoodsNotFoundException extends Exception{
    public GoodsNotFoundException() {
    }

    public GoodsNotFoundException(String message) {
        super(message);
    }
}
