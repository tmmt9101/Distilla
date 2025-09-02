package com.models;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.Optional;

public class ProductDao implements Dao<Product, Integer> {
    private final DataSource source;
    private final static String getAll = "SELECT * FROM Product;";
    private final static String insert =
        """
        INSERT INTO Product(name, description, alcohol_percentage, available, quantity)
        VALUES (?, ?, ?, ?, ?);
        """;
    private final static String update =
        """
        UPDATE Product
        SET id = ?,
            name = ?,
            description = ?,
            alcohol_percentage = ?,
            available = ?,
            quantity = ?
        WHERE id = ?;
        """;
    private final static String remove =
        """
        DELETE FROM Product
        WHERE id = ?;
        """;
    public ProductDao(DataSource source) {
        this.source = source;
    }

    public ArrayList<Product> getAll() throws SQLException {
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
                                         Integer.valueOf(result.getInt(6))));
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
                                               Integer.valueOf(result.getInt(6))));
            }
            return Optional.empty();
        }
    }
    public void remove(Product object) throws SQLException {
        this.remove(object.id());
    }
    public void remove(Integer object) throws SQLException {
        try (var conn      = source.getConnection();
             var statement = conn.prepareStatement(remove)) {
            statement.setInt(1, object.intValue());
        }
    }
    public void save(Product object) throws SQLException {
        try (var conn = source.getConnection()) {
            int counter = 1;
            try (var statement = conn.prepareStatement(object.isNew() ? insert : update)) {
                if (!object.isNew()) {
                    statement.setInt(counter++, object.id());
                }
                statement.setString  (counter++, object.name());
                statement.setString  (counter++, object.description());
                statement.setFloat   (counter++, object.alcohol_percentage());
                statement.setBoolean (counter++, object.available().booleanValue());
                statement.setInt     (counter++, object.quantity());
                statement.executeUpdate();
            }
        }
    }
}
