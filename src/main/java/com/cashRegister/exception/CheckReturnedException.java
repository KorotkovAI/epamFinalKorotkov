package com.cashRegister.exception;

/**
 * This class implements its own error with the return of checks
 */
public class CheckReturnedException extends Exception{
    public CheckReturnedException() {
    }

    public CheckReturnedException(String message) {
        super(message);
    }
}
