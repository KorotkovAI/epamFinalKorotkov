package com.cashRegister.model;

public class Goods {
    private int id;
    private String name;
    private int amount;
    private double price;

    private static int counter = 1;

    public Goods(int id, String name, int amount, double price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public Goods(String name, int amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public static int getCounter() {
        return counter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static void setCounter(int counter) {
        Goods.counter = counter;
    }
}
