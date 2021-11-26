package com.cashRegister.model;

import java.util.Objects;

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

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckGoods that = (CheckGoods) o;
        return id == that.id && idGoods == that.idGoods && amountGoods == that.amountGoods && Double.compare(that.priceGoods, priceGoods) == 0 && nameGoods.equals(that.nameGoods) && idCheck.equals(that.idCheck);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idGoods, nameGoods, amountGoods, priceGoods, idCheck);
    }

    @Override
    public String toString() {
        return "CheckGoods{" +
                "id=" + id +
                ", idGoods=" + idGoods +
                ", nameGoods='" + nameGoods + '\'' +
                ", amountGoods=" + amountGoods +
                ", priceGoods=" + priceGoods +
                ", idCheck=" + idCheck +
                '}';
    }
}
