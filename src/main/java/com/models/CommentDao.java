package com.models;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.Optional;

public class CommentDao implements Dao<Comment, Integer> {
    private final DataSource source;
    private static final String getById = "SELECT * FROM Comment WHERE id = ?";
    private static final String insert  = "INSERT INTO Comment (commenter, comment, publication) VALUES (?, ?, ?)";
    private static final String update  = "UPDATE Comment commenter = ?, comment = ?, publication = ? SET WHERE id = ?";
    private static final String remove  = "DELETE FROM Comment WHERE id = ?";
    private static final String getAll  = "SELECT * FROM Comment";

    public CommentDao(DataSource source) {
        this.source = source;
    }

    @Override
    public List<Comment> getAll() throws SQLException {
        var result = new ArrayList<Comment>();
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(getAll);
             var rs   = stmt.executeQuery()) {
            var comment = new Comment(rs.getInt("id"),
                                      rs.getInt("commentor"),
                                      rs.getString("comment"),
                                      rs.getTimestamp("publication"));
            result.add(comment);
        }
        return result;
    }

    @Override
    public Optional<Comment> getById(Integer id) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(getById)) {
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var comment = new Comment(rs.getInt("id"),
                                              rs.getInt("commentor"),
                                              rs.getString("comment"),
                                              rs.getTimestamp("publication"));
                    return Optional.of(comment);
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
    public void save(Comment object) throws SQLException {
        int counter = 1;
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(object.isNew() ? insert : update)) {
            stmt.setInt(counter++, object.commenter());
            stmt.setString(counter++, object.comment());
            stmt.setTimestamp(counter++, object.publication());
            if (!object.isNew()) {
                stmt.setInt(counter++, object.id());
            }
            stmt.executeUpdate();
        }
    }
}
