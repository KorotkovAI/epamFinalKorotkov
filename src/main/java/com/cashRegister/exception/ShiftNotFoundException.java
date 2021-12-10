package com.cashRegister.exception;

/**
 * This class implements its own error with find shift
 */
public class ShiftNotFoundException extends Exception {
    public ShiftNotFoundException() {
    }

    public ShiftNotFoundException(String message) {
        super(message);
    }
}
