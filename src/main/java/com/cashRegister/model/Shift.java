package com.cashRegister.model;

public class Shift {
    private int id;
    private boolean isOpen;

    private static int counter = 1;

    public Shift() {
        this.id = counter++;
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
