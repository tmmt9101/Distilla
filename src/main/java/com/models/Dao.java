package com.models;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface Dao<T, Id> {
    public Optional<T> getById(Id id)    throws SQLException;
    public void        save(T object)    throws SQLException;
    public void        remove(Id object) throws SQLException;
    public List<T>     getAll()          throws SQLException;
}
