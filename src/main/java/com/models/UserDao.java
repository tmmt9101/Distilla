package com.models;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.Optional;

public class UserDao implements Dao<User, Integer> {
    private final DataSource source;
    private final static String insert  = "INSERT INTO User (username, email, creation_time) VALUES (?, ?, ?)";
    private final static String update  = "UPDATE User SET username = ?, email = ?, creation_time = ? WHERE id = ?";
    private final static String remove  = "DELETE FROM User WHERE id = ?";
    private final static String getAll  = "SELECT * FROM User";
    private final static String getById = "SELECT * FROM User WHERE id = ?";

    public UserDao(DataSource source) {
        this.source = source;
    }

    @Override
    public List<User> getAll() throws SQLException {
        var result = new ArrayList<User>();
        try (var conn = source.getConnection();
             var stat = conn.prepareStatement(getAll);
             var rs = stat.executeQuery()) {
            while (rs.next()) {
                var user = new User(rs.getInt("id"), rs.getString("username"),
                                    rs.getString("email"), rs.getTimestamp("creation"));
                result.add(user);
            }
        }
        return result;
    }
    @Override
    public Optional<User> getById(Integer id) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(getById)) {
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var user = new User(rs.getInt("id"), rs.getString("username"),
                                        rs.getString("email"), rs.getTimestamp("creation"));
                    return Optional.of(user);
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
    public void save(User object) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(object.isNew() ? insert : update)) {
            stmt.setString(1,    object.username());
            stmt.setString(2,    object.email());
            stmt.setTimestamp(3, object.creation());
            if (object.isNew()) {
                stmt.setInt(4, object.id());
            }
            stmt.executeUpdate();
        }
    }
}
