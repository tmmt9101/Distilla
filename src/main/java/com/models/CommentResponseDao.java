package com.models;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.Optional;

public class CommentResponseDao implements Dao<CommentResponse, Integer> {
    private final DataSource source;
    private static final String getById = "SELECT * FROM CommentResponse WHERE id = ?";
    private static final String insert = "INSERT INTO CommentResponse (comment, response_to, publication) VALUES (?, ?, ?)";
    private static final String update = "UPDATE CommentResponse  comment = ?, response_to = ?, publication = ? SET WHERE id = ?";
    private static final String remove = "DELETE FROM  CommentResponse WHERE id = ?";
    private static final String getAll = "SELECT * FROM CommentResponse";

    public CommentResponseDao(DataSource source) {
        this.source = source;
    }
    @Override
    public List<CommentResponse> getAll() throws SQLException {
        var result = new ArrayList<CommentResponse>();
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(getAll);
             var rs   = stmt.executeQuery()) {
            while (rs.next()) {
                var commentResponse = new CommentResponse(rs.getInt("id"),
                                                          rs.getString("comment"),
                                                          rs.getInt("response_to"),
                                                          rs.getTimestamp("publication"));
                result.add(commentResponse);
            }
        }
        return result;
    }

    @Override
    public Optional<CommentResponse> getById(Integer id) throws SQLException {
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(getById)) {
            stmt.setInt(1, id);
            try (var rs = stmt.executeQuery()) {
                if (rs.next()) {
                    var commentResponse = new CommentResponse(rs.getInt("id"),
                                                              rs.getString("comment"),
                                                              rs.getInt("response_to"),
                                                              rs.getTimestamp("publication"));
                    return Optional.of(commentResponse);
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
    public void save(CommentResponse object) throws SQLException {
        int counter = 1;
        try (var conn = source.getConnection();
             var stmt = conn.prepareStatement(object.isNew() ? insert : update)) {
            stmt.setString(counter++, object.comment());
            stmt.setInt(counter++, object.response_to());
            stmt.setTimestamp(counter++, object.publication());
            if (!object.isNew()) {
                stmt.setInt(counter++, object.id());
            }
            stmt.executeUpdate();
        }
    }
}
