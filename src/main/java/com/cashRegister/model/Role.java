package com.cashRegister.model;


public class Role {
    private int id;
    private String name;

    private static int counter = 1;

    public Role(String name) {
        this.id = counter++;
        this.name = name;
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
