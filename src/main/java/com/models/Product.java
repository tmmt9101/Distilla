package com.models;

import java.util.List;

public record Product(Integer id,
                      String  name,
                      String  description,
                      Float   alcohol_percentage,
                      Boolean available,
                      Integer quantity) {
    public Product(Integer id,
                   String  name,
                   String  description,
                   Float   alcohol_percentage,
                   Boolean available,
                   Integer quantity) {
        this.id                 = id;
        this.name               = name;
        this.description        = description;
        this.alcohol_percentage = alcohol_percentage;
        this.available          = available;
        this.quantity           = quantity;
    }
    public Product(String  name,
                   String  description,
                   Float   alcohol_percentage,
                   Boolean available,
                   Integer quantity) {
        this(Integer.MIN_VALUE,
             name,
             description,
             alcohol_percentage,
             available,
             quantity);
    }
    public boolean equals(Product that) {
        return (this.id.equals(Integer.MIN_VALUE) ||
                that.id.equals(Integer.MIN_VALUE)) ?
            (this.id.equals(that.id)                                 &&
             this.name.equals(that.name)                             &&
             this.description.equals(that.description)               &&
             this.alcohol_percentage.equals(that.alcohol_percentage) &&
             this.available.equals(that.available)                   &&
             this.quantity.equals(that.quantity))
            :
            (this.name.equals(that.name)                             &&
             this.description.equals(that.description)               &&
             this.alcohol_percentage.equals(that.alcohol_percentage) &&
             this.available.equals(that.available)                   &&
             this.quantity.equals(that.quantity));
    }
    public boolean isNew() {return id.equals(Integer.MIN_VALUE);}
}



/*
public class Product {
    private final Integer             id;
    private final String              name;
    private final String              description;
    private final Float               alcohol_percentage;
    private final Boolean             available;
    private final Integer             quantity;

    public Product(Integer             id,
                   String              name,
                   String              description,
                   Float               alcohol_percentage,
                   Boolean             available,
                   Integer             quantity) {
        this.id                 = id;
        this.name               = name;
        this.description        = description;
        this.alcohol_percentage = alcohol_percentage;
        this.available          = available;
        this.quantity           = quantity;
    }

    public Product(String              name,
                   String              description,
                   Float               alcohol_percentage,
                   Boolean             available,
                   Integer             quantity) {
        this.id                 = Integer.MIN_VALUE;
        this.name               = name;
        this.description        = description;
        this.alcohol_percentage = alcohol_percentage;
        this.available          = available;
        this.quantity           = quantity;
    }

    public boolean equals(Product that) {
        return (this.id.equals(Integer.MIN_VALUE) ||
                that.id.equals(Integer.MIN_VALUE)) ?
            (this.id.equals(that.id)                                 &&
             this.name.equals(that.name)                             &&
             this.description.equals(that.description)               &&
             this.alcohol_percentage.equals(that.alcohol_percentage) &&
             this.available.equals(that.available)                   &&
             this.quantity.equals(that.quantity))
            :
            (this.name.equals(that.name)                             &&
             this.description.equals(that.description)               &&
             this.alcohol_percentage.equals(that.alcohol_percentage) &&
             this.available.equals(that.available)                   &&
             this.quantity.equals(that.quantity));


    }
    public Integer             getQuantity          () {return quantity;}
    public Boolean             getAvailable         () {return available;}
    public Float               getAlcohol_percentage() {return alcohol_percentage;}
    public String              getDescription       () {return description;}
    public String              getName              () {return name;}
    public Integer             getId                () {return id;}
    public boolean             isNew                () {return id.equals(Integer.MIN_VALUE);}
}
/*
