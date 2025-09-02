package com.models;

public record ProductTag(Integer id,
                         Integer productId,
                         Integer typeId) {
    public ProductTag(Integer id, Integer productId, Integer typeId) {
        this.id        = id;
        this.productId = productId;
        this.typeId    = typeId;
    }

    public ProductTag(Integer productId, Integer typeId) {
        this(Integer.MIN_VALUE, productId, typeId);
    }

    public boolean isNew() {
        return this.id.equals(Integer.MIN_VALUE);
    }
}
