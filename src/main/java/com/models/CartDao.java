package com.models;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.Optional;


public class CartDao implements Dao<Cart, Integer> {
    private final DataSource source;
    private static final String getById = "SELECT * FROM Cart WHERE id = ?";
    private static final String insert  = "INSERT INTO Cart (user) VALUES (?)";
    private static final String update  = "UPDATE Cart SET user = ? WHERE id = ?";
    private static final String remove  = "DELETE FROM Cart WHERE id = ?";
    private static final String getAll  = "SELECT * FROM Cart";

    public CartDao(DataSource source) {
        this.source = source;
    }

    @Override
    public List<Cart> getAll() throws SQLException {
        var result = new ArrayList<Cart>();
        try (var conn = source.getConnection();
             var stat = conn.prepareStatement(getAll);
             var rs   = stat.executeQuery()) {
            while (rs.next()) {
                var cart = new Cart(rs.getInt("id"), rs.getInt("user"));
                result.add(cart);
            }
        }
        return result;
    }

    @Override
    public Optional<Cart> getById(Integer id) throws SQLException {
        try (var conn = source.getConnection();
             var stat = conn.prepareStatement(getById)) {
            stat.setInt(1, id);
            try (var rs = stat.executeQuery()) {
                if (rs.next()) {
                    var cart = new Cart(rs.getInt("id"), rs.getInt("user"));
                    return Optional.of(cart);
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public void remove(Integer object) throws SQLException {
        try (var conn = source.getConnection();
                var stat = conn.prepareStatement(remove)) {
            stat.setInt(1, object);
            stat.executeUpdate();
        }
    }

    @Override
    public void save(Cart object) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(object.isNew() ? insert : update)) {
            stmt.setInt(1, object.id());
            if (!object.isNew()) {
                stmt.setInt(2, object.id());
            }
        }
    }
}
