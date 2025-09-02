package com.models;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.Optional;


public class CartItemDao implements Dao<CartItem, Integer> {
    private final DataSource source;
    private static final String getById = "SELECT * FROM CartItem WHERE id = ?";
    private static final String insert  = "INSERT INTO CartItem (cart, product, quantity) VALUES (?, ?, ?)";
    private static final String update  = "UPDATE CartItem SET cart = ?, product = ?, quantity = ? WHERE id = ?";
    private static final String remove  = "DELETE FROM CartItem WHERE id = ?";
    private static final String getAll  = "SELECT * FROM CartItem";

    public CartItemDao(DataSource source) {
        this.source = source;
    }
    @Override
    public List<CartItem> getAll() throws SQLException {
        var result = new ArrayList<CartItem>();
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(getAll);
             var rs   = stmt.executeQuery()) {
            while (rs.next()) {
                var cartItem = new CartItem(rs.getInt("id"),
                                            rs.getInt("cart"),
                                            rs.getInt("product"),
                                            rs.getInt("quantity"));
                result.add(cartItem);
            }
        }
        return result;
    }
    @Override
    public Optional<CartItem> getById(Integer id) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(getById)) {
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var cartItem = new CartItem(rs.getInt("id"),
                                                rs.getInt("cart"),
                                                rs.getInt("product"),
                                                rs.getInt("quantity"));
                    return Optional.of(cartItem);
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
    public void save(CartItem object) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(object.isNew() ? insert : update)) {
            stmt.setInt(1, object.cart());
            stmt.setInt(2, object.product());
            stmt.setInt(3, object.quantity());
            if (!object.isNew()) {
                stmt.setInt(4, object.id());
            }
            stmt.executeUpdate();
        }
    }
}
