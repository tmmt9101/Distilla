package com.models;

import java.util.List;

public class Product {
    private final Integer             id;
    private final String              name;
    private final String              description;
    private final Double              alcohol_percentage;
    private final Boolean             available;
    private final Integer             quantity;
    private final List<ProductPrices> prices;

    public Product(Integer             id,                      
                   String              name,                    
                   String              description,             
                   Double              alcohol_percentage,      
                   Boolean             available,               
                   Integer             quantity,                
                   List<ProductPrices> prices) {
        this.id                 = id;                      
        this.name               = name;                            
        this.description        = description;             
        this.alcohol_percentage = alcohol_percentage;      
        this.available          = available;               
        this.quantity           = quantity;                
        this.prices             = prices;                          
    }

    
    public Integer             getQuantity          () {return quantity;}
    public Boolean             getAvailable         () {return available;}
    public Double              getAlcohol_percentage() {return alcohol_percentage;}
    public String              getDescription       () {return description;}
    public String              getName              () {return name;}
    public Integer             getId                () {return id;}
    public List<ProductPrices> getPrices            () {return prices;}
}
