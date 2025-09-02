package com.models;

public record Cart(Integer id, Integer user) {
    public Cart(Integer id, Integer user) {
        this.id = id;
        this.user = user;
    }
    public Cart() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public boolean isNew() {
        return this.id.equals(Integer.MIN_VALUE);
    }
}
