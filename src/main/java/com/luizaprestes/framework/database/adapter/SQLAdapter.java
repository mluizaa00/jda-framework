package com.luizaprestes.framework.database.adapter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SQLAdapter<T> {

    T read(ResultSet set) throws SQLException;

    void insert(PreparedStatement statement, T value) throws SQLException;

    void update(PreparedStatement statement, T value) throws SQLException;
}
