package com.cashRegister.model;

import java.sql.Timestamp;

public class Check {
    private int id;
    private double sum;
    private Timestamp timestamp;
    private boolean isReturned;
    private Shift shiftId;


    public Check(int id, double sum, Timestamp timestamp, boolean isReturned, Shift shiftId) {
        this.id = id;
        this.sum = sum;
        this.timestamp = timestamp;
        this.isReturned = isReturned;
        this.shiftId = shiftId;
    }

    public Check(double sum, Timestamp timestamp, boolean isReturned, Shift shiftId) {
        this.sum = sum;
        this.timestamp = timestamp;
        this.isReturned = isReturned;
        this.shiftId = shiftId;
    }

    public int getId() {
        return id;
    }

    public double getSum() {
        return sum;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public Shift getShiftId() {
        return shiftId;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public void setShiftId(Shift shiftId) {
        this.shiftId = shiftId;
    }
}
