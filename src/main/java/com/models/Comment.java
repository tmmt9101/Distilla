package com.models;

import java.sql.Timestamp;

public record Comment(Integer id,
                      Integer commenter,
                      String comment,
                      Timestamp publication) {
    public Comment(Integer id, Integer commenter,
                   String comment, Timestamp publication) {
        this.id          = id;
        this.commenter   = commenter;
        this.comment     = comment;
        this.publication = publication;
    }
    public Comment(Integer commenter,
                   String comment, Timestamp publication) {
        this(Integer.MIN_VALUE, commenter, comment, publication);
    }
    public boolean isNew() {
        return this.id.equals(Integer.MIN_VALUE);
    }
}
