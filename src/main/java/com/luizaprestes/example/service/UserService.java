package com.luizaprestes.example.service;

import com.luizaprestes.example.adapter.UserAdapter;
import com.luizaprestes.framework.database.service.SQLService;
import com.luizaprestes.framework.utils.SQLReader;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class UserService extends SQLService<String, String> {

    public UserService(DataSource dataSource) {
        super(dataSource, new UserAdapter(), new SQLReader());
    }

    @Override
    public void createTable() {
        try(
          final PreparedStatement statement = connection.prepareStatement(reader.getSql("create_table"))
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(String key, String value) {
        try(
          final PreparedStatement statement = connection.prepareStatement(reader.getSql("insert_user"))
        ) {
            adapter.insert(statement, value);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String key, String value) {

    }

    @Override
    public void delete(String key) {

    }

    @Override
    public String getById(String key) {
        return null;
    }

    @Override
    public Collection<String> getAll() {
        return null;
    }
}
