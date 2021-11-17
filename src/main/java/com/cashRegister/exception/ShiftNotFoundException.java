package com.cashRegister.exception;

public class ShiftNotFoundException extends Exception {
    public ShiftNotFoundException() {
    }

    public ShiftNotFoundException(String message) {
        super(message);
    }
}
