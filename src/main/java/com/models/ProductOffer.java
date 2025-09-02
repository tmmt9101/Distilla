package com.models;

public record ProductOffer(Integer id,
                           Integer product,
                           Integer product_price,
                           String  description) {
    public ProductOffer(Integer id, Integer product,
                        Integer product_price, String  description) {
        this.id            = id;
        this.product       = product;
        this.product_price = product_price;
        this.description   = description;
    }
    public ProductOffer(Integer product, Integer product_price, String  description) {
        this(Integer.MIN_VALUE, product, product_price, description);
    }
    public boolean isNew() {
        return this.id.equals(Integer.MIN_VALUE);
    }
}
