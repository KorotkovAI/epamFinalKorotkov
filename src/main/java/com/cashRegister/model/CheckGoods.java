package com.cashRegister.model;

public class CheckGoods {
    private int id;
    private int idGoods;
    private String nameGoods;
    private int amountGoods;
    private double priceGoods;
    private Check idCheck;

    public CheckGoods(int idGoods, String nameGoods, int amountGoods, double priceGoods, Check idCheck) {
        this.idGoods = idGoods;
        this.nameGoods = nameGoods;
        this.amountGoods = amountGoods;
        this.priceGoods = priceGoods;
        this.idCheck = idCheck;
    }

    public CheckGoods(int id, int idGoods, String nameGoods, int amountGoods, double priceGoods, Check idCheck) {
        this.id = id;
        this.idGoods = idGoods;
        this.nameGoods = nameGoods;
        this.amountGoods = amountGoods;
        this.priceGoods = priceGoods;
        this.idCheck = idCheck;
    }

    public int getId() {
        return id;
    }

    public int getIdGoods() {
        return idGoods;
    }

    public String getNameGoods() {
        return nameGoods;
    }

    public int getAmountGoods() {
        return amountGoods;
    }

    public double getPriceGoods() {
        return priceGoods;
    }

    public Check getIdCheck() {
        return idCheck;
    }

    public void setIdGoods(int idGoods) {
        this.idGoods = idGoods;
    }

    public void setNameGoods(String nameGoods) {
        this.nameGoods = nameGoods;
    }

    public void setAmountGoods(int amountGoods) {
        this.amountGoods = amountGoods;
    }

    public void setPriceGoods(double priceGoods) {
        this.priceGoods = priceGoods;
    }

    public void setIdCheck(Check idCheck) {
        this.idCheck = idCheck;
    }

}
