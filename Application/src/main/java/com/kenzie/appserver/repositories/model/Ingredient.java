package com.kenzie.appserver.repositories.model;

public class Ingredient {
    private int id;
    private String name;
    private double amount;
    private String unit;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }
}
