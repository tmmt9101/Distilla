package com.models;

public class ProductType {
    private final Integer id;
    private final String name;

    public ProductType(Integer id, String name) {
        this.id   = id;
        this.name = name;
    }

    public ProductType(String name) {
        this.id   = Integer.MIN_VALUE;;
        this.name = name;
    }
    public boolean isNew   () { return id.equals(Integer.MIN_VALUE); }
    public Integer getId   () { return id; }
    public String  getName () { return name; }
}
