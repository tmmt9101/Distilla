package com.models;

import java.sql.Timestamp;

public record User(Integer id,   String username,
                   String email, Timestamp creation) {
    public User(Integer id,   String username,
                String email, Timestamp creation) {
        this.id       = id;
        this.username = username;
        this.email    = email;
        this.creation = creation;
    }
    public User(String username, String email, Timestamp creation) {
        this(Integer.MIN_VALUE, username, email, creation);
    }

    public boolean isNew() {
        return this.id.equals(Integer.MIN_VALUE);
    }
}
