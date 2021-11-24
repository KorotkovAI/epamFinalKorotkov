package com.cashRegister.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shift shift = (Shift) o;
        return id == shift.id && isOpen == shift.isOpen && openTime.equals(shift.openTime) && Objects.equals(closeTime, shift.closeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isOpen, openTime, closeTime);
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", isOpen=" + isOpen +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                '}';
    }
}
