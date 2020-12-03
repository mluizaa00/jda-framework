package com.luizaprestes.example.adapter;

import com.luizaprestes.framework.database.adapter.SQLAdapter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAdapter implements SQLAdapter<String> {

    @Override
    public String read(ResultSet set) throws SQLException {
        return set.getString("name");
    }

    @Override
    public void insert(PreparedStatement statement, String value) throws SQLException {
        statement.setString(1, value);
    }

    @Override
    public void update(PreparedStatement statement, String value) throws SQLException {
        statement.setString(1, value);
    }
}
