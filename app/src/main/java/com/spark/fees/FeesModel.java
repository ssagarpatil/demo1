package com.spark.fees;

class FeesModel {
    private String name;
    private int amount;

    public FeesModel(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() { return name; }
    public int getAmount() { return amount; }
}
