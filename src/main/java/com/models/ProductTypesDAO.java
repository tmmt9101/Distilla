package com.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.Optional;

public class ProductTypesDAO implements Dao<ProductType, Integer> {
    private final DataSource source;
    private final static String insert  = "INSERT INTO ProductTypes (name) VALUES (?)";
    private final static String update  = "UPDATE ProductTypes SET name = ? WHERE id = ?";
    private final static String remove  = "DELETE FROM ProductTypes WHERE id = ?";
    private final static String getAll  = "SELECT * FROM ProductTypes";
    private final static String getById = "SELECT * FROM ProductTypes WHERE id = ?";

    public ProductTypesDAO(DataSource source) {
        this.source = source;
    }

    @Override
    public Optional<ProductType> getById(Integer id) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(getById)) {
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var productType = new ProductType(rs.getInt("id"), rs.getString("name"));
                    return Optional.of(productType);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public void save(ProductType productType) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(productType.isNew() ? insert : update)) {
            stmt.setString(1, productType.getName());
            if (productType.isNew()) {
                stmt.setInt(2, productType.getId());
            }
            stmt.executeUpdate();
        }
    }
z
    @Override
    public void remove(Integer id) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(remove)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<ProductType> getAll() throws SQLException {
        List<ProductType> productTypes = new ArrayList<>();
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(getAll);
             var rs = stmt.executeQuery()) {
            while (rs.next()) {
                var productType = new ProductType(rs.getInt("id"),
                                                  rs.getString("name"));
                productTypes.add(productType);
            }
        }
        return productTypes;
    }
}
