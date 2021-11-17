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

    public Shift(int id, boolean isOpen, Timestamp openTime, Timestamp closeTime) {
        this.id = id;
        this.isOpen = isOpen;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public int getId() {
        return id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public Timestamp getOpenTime() {
        return openTime;
    }

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
    }
}
