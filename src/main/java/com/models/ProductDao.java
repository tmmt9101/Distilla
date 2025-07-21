package com.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.Optional;

public class ProductDao implements Dao<Product, Integer> {
    private final DataSource source;
    private final static String getAll = "SELECT * FROM Product;";

    public ProductDao(DataSource source) {
        this.source = source;
    }

    public List<Product> getAll() throws SQLException {
        var products = new ArrayList<Product>();
        try (var conn      = source.getConnection();
             var statement = conn.createStatement()) {
            var result = statement.executeQuery(getAll);
            while (result.next()) {
                var id = Integer.valueOf(result.getInt(1));
                products.add(new Product(id,
                                         result.getString(2),
                                         result.getString(3),
                                         Float.valueOf(result.getFloat(4)),
                                         result.getBoolean(5),
                                         Integer.valueOf(result.getInt(6)),
                                         new ProductPricesDAO(this.source).getByProductId(id)));
            }
        }
        return products;
    }

    public Optional<Product> getById(Integer id) throws SQLException {
        try (var conn = source.getConnection();
                var statement = conn.prepareStatement("SELECT * FROM Produtcts WHERE id = ?;")) {
            statement.setInt(1, id.intValue());
            var result = statement.executeQuery();
            if (result.next()) {
                return Optional.of(new Product(id,
                                               result.getString(2),
                                               result.getString(3),
                                               Float.valueOf(result.getFloat(4)),
                                               result.getBoolean(5),
                                               Integer.valueOf(result.getInt(6)),
                                               new ProductPricesDAO(source).getByProductId(id)));
            }
            return Optional.empty();
        }
    }
    public void remove(Integer object) throws SQLException {}
    public void save(Product object) throws SQLException {}
    public void update(Integer old, Product object) throws SQLException {}
}
