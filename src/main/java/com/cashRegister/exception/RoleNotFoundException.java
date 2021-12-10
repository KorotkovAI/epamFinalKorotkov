package com.cashRegister.exception;

/**
 * This class implements its own error with find roles
 */
public class RoleNotFoundException extends Exception{

    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
