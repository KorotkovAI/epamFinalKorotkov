package com.cashRegister.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Shift {
    private int id;
    private boolean isOpen;
    private Timestamp openTime;
    private Timestamp closeTime;

    public Shift() {
        this.openTime = Timestamp.valueOf(LocalDateTime.now());
        this.isOpen = true;
    }

}
