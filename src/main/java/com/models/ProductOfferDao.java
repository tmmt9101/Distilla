package com.models;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.Optional;


public class ProductOfferDao implements Dao<ProductOffer, Integer> {
    private final DataSource source;
    private static final String getById = "SELECT * FROM ProductOfffer WHERE id = ?";
    private static final String insert  = "INSERT INTO ProductOffer (product, product_price, description) VALUES (?, ?, ?)";
    private static final String update  = "UPDATE ProductOffer product = ?, product_price = ?, description = ? SET WHERE id = ?";
    private static final String remove  = "DELETE FROM ProductOffer WHERE id = ?";
    private static final String getAll  = "SELECT * FROM ProductOffer";

    public ProductOfferDao(DataSource source) {
        this.source = source;
    }
    @Override
    public List<ProductOffer> getAll() throws SQLException {
        var result = new ArrayList<ProductOffer>();
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(getAll);
             var rs   = stmt.executeQuery()) {
            while (rs.next()) {
                var productOffer = new ProductOffer(rs.getInt("id"),
                                                    rs.getInt("product"),
                                                    rs.getInt("product_price"),
                                                    rs.getString("description"));
                result.add(productOffer);
            }
        }
        return result;
    }
    @Override
    public Optional<ProductOffer> getById(Integer id) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(getById)) {
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var productOffer = new ProductOffer(rs.getInt("id"),
                                                        rs.getInt("product"),
                                                        rs.getInt("product_price"),
                                                        rs.getString("description"));
                    return Optional.of(productOffer);
                }
                return Optional.empty();
            }
        }
    }
    @Override
    public void remove(Integer object) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(remove)) {
            stmt.setInt(1, object);
            stmt.executeUpdate();
        }
    }
    @Override
    public void save(ProductOffer object) throws SQLException {
        int counter = 1;
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(object.isNew() ? insert : update)) {
            stmt.setInt(counter++, object.product());
            stmt.setInt(counter++, object.product_price());
            stmt.setString(counter++, object.description());
            if (!object.isNew()) {
                stmt.setInt(counter++, object.id());
            }
            stmt.executeUpdate();
        }
    }
}
