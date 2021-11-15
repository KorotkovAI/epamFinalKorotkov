package com.cashRegister.model;

public class Shift {
    private int id;
    private boolean isOpen;

    public Shift() {
        this.isOpen = true;
    }

    public int getId() {
        return id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
