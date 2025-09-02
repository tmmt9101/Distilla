package com.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.Optional;


public class ProductTagsDao implements Dao<ProductTag, Integer> {
    private final DataSource source;
    private static final String getById = "SELECT * FROM ProductTags WHERE id = ?";
    private static final String insert  = "INSERT INTO ProductTags (product, type) VALUES (?, ?)";
    private static final String update  = "UPDATE ProductTags SET product = ?, type = ? WHERE id = ?";
    private static final String remove  = "DELETE FROM ProductTags WHERE id = ?";
    private static final String getAll  = "SELECT * FROM ProductTags";

    public ProductTagsDao(DataSource source) {
        this.source = source;
    }
    @Override
    public Optional<ProductTag> getById(Integer id) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(getById)) {
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var productTag = new ProductTag(rs.getInt("id"),
                                                    rs.getInt("product"),
                                                    rs.getInt("type"));
                    return Optional.of(productTag);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public void save(ProductTag productTag) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(productTag.isNew() ? insert : update)) {
            stmt.setInt(1, productTag.productId());
            stmt.setInt(2, productTag.typeId());
            if (!productTag.isNew()) {
                stmt.setInt(3, productTag.id());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void remove(Integer id) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(remove)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<ProductTag> getAll() throws SQLException {
        List<ProductTag> productTags = new ArrayList<>();
        try (Connection conn = source.getConnection();
             var stmt = conn.prepareStatement(getAll);
             var rs = stmt.executeQuery()) {
            while (rs.next()) {
                ProductTag productTag = new ProductTag(rs.getInt("id"),
                                                       rs.getInt("product"),
                                                       rs.getInt("type"));
                productTags.add(productTag);
            }
        }
        return productTags;
    }
}
