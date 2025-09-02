package com.models;


public record CartItem(Integer id,
                       Integer cart,
                       Integer product,
                       Integer quantity) {
    public CartItem(Integer id, Integer cart,
                    Integer product, Integer quantity) {
        this.id       = id;
        this.cart     = cart;
        this.product  = product;
        this.quantity = quantity;
    }
    public CartItem(Integer cart, Integer product, Integer quantity) {
        this(Integer.MIN_VALUE, cart, product, quantity);
    }

    public boolean isNew() {
        return this.id.equals(Integer.MIN_VALUE);
    }
}
