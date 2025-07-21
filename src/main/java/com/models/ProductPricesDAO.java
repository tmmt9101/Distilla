package com.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.Optional;

public class ProductPricesDAO implements Dao<ProductPrices, Integer> {
    private final DataSource source;
    private static final String getById =
        """
        SELECT *
        FROM ProductPrices
        WHERE id = ?
        """;
    private static final String getAll =
        """
        SELECT *
        FROM ProductPrices
        """;
    private static final String getByProductId =
        """
        SELECT *
        FROM ProductPrices
        WHERE product = ?
        """;
    public ProductPricesDAO(DataSource source) {
        this.source = source;
    }
    @Override
    public void update(Integer id, ProductPrices object) throws SQLException {
        // TODO Auto-generated method stub
    }
    @Override
    public void save(ProductPrices object) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public void remove(Integer object) throws SQLException {
        // TODO Auto-generated method stub
    }
    @Override
    public List<ProductPrices> getAll() throws SQLException {
        var data = new ArrayList<ProductPrices>();
        try (var con = source.getConnection();
             var statement = con.prepareStatement(getById)) {
            var result = statement.executeQuery();
            while (result.next()) {
                data.add(new ProductPrices(Integer.valueOf(result.getInt("id")),
                                           Float.valueOf(result.getFloat("price")),
                                           result.getTime("validity_start"),
                                           result.getTime("validity_start")));
            }
        }
        return data;
    }

    @Override
    public Optional<ProductPrices> getById(Integer id) throws SQLException {
        try (Connection con = source.getConnection();
             var statement  = con.prepareStatement(getById);) {
            var result      = statement.executeQuery();
            if (result.next()) {
                return Optional.of(new ProductPrices(id,
                                         Float.valueOf(result.getFloat(2)),
                                         result.getTime(4),
                                              result.getTime(5)));
            }
        }
        return Optional.empty();
    }
    public List<ProductPrices> getByProductId(Integer id) throws SQLException {
        var price = new ArrayList<ProductPrices>();
        try (Connection con = source.getConnection();
             var statement  = con.prepareStatement(getByProductId)) {
            statement.setInt(1, id.intValue());
            var result = statement.executeQuery();
            while (result.next()) {
                price.add(new ProductPrices(Integer.valueOf(result.getInt(1)),
                                            Float.valueOf(result.getFloat(3)),
                                            result.getTime(4),
                                            result.getTime(5)));
            }
        }
        return price;
    }
}
