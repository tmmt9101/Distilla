package com.models;

import java.sql.Timestamp;

public record CommentResponse(Integer   id,
                              String    comment,
                              Integer   response_to,
                              Timestamp publication) {
    public CommentResponse(Integer id, String comment, Integer response_to, Timestamp publication) {
        this.id          = id;
        this.comment     = comment;
        this.response_to = response_to;
        this.publication = publication;
    }
    public CommentResponse(String comment, Integer response_to, Timestamp publication) {
        this(Integer.MIN_VALUE, comment, response_to, publication);
    }
    public boolean isNew() {
        return this.id.equals(Integer.MIN_VALUE);
    }
}
