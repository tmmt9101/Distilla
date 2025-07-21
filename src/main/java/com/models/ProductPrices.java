package com.models;

import java.sql.Time;
import java.util.Optional;

/*
  create table ProductPrices (
       id int not null unique auto_increment,
       product int not null,
       price decimal(4, 2) not null,
       validity_start time,
       validity_end time,
       constraint valid_price_check check
       ((validity_start is null and validity_end is null) or
        ((validity_start is null and validity_end is not null) and
         (validity_start < validity_end))),
       primary key (id),
       foreign key (product) references Product(id),
);
 */
public final  class ProductPrices {
    private final Integer         id;
    private final Float           price;
    private final Optional<Time>  validity_start;
    private final Optional<Time>  validity_end;

    public ProductPrices(Integer        id,
                         // Product        product,
                         Float          price,
                         Time validity_start,
                         Time validity_end) {
        this.id             = id;
        // this.product        = product;
        this.price          = price;
        this.validity_start = Optional.ofNullable(validity_start);
        this.validity_end   = Optional.ofNullable(validity_end);
    }

    public Boolean valid() {
        return (validity_start.isEmpty() && validity_end.isEmpty())
            || (validity_start.isEmpty() && validity_end.isPresent())
            || (validity_start.get().before(validity_end.get()));
    }

    public Integer getId() { return id; }
    // public Product getProduct() { return product; }
    public Optional<Time> getValidity_start() { return validity_start; }
    public Float getPrice() { return price; }
    public Optional<Time> getValidity_end() { return validity_end; }
 }
