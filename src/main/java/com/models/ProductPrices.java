package com.models;

import java.sql.Timestamp;
import java.util.Optional;

public record ProductPrices(Integer              id,
                            Float                price,
                            Optional<Timestamp>  validity_start,
                            Optional<Timestamp>  validity_end) {

    public ProductPrices(Integer   id,
                         Float     price,
                         Timestamp validity_start,
                         Timestamp validity_end) {
        this(id, price,
             Optional.ofNullable(validity_start),
             Optional.ofNullable(validity_end));
    }
    public ProductPrices(Float          price,
                         Timestamp validity_start,
                         Timestamp validity_end) {
        this(Integer.MIN_VALUE, price,
             Optional.ofNullable(validity_start),
             Optional.ofNullable(validity_end));
    }
    public boolean isNew() {return this.id.equals(Integer.MIN_VALUE);}
 }
