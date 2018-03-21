package com.company.repositories;

import com.company.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM usery WHERE id = ?";
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM usery WHERE name = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM usery";
    private static final String SQL_INSERT_USER = "INSERT INTO usery(name, password) VALUES (?, ?)";
    private static final String SQL_UPDATE_BY_ID = "UPDATE usery" + " SET name = ?, password = ? WHERE id = ?";

    private Connection connection;

    public UserRepositoryImpl(DataSource dataSource){
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new LinkedList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                User newOne = new User(id, name, password);
                users.add(newOne);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return users;
    }

    @Override
    public void save(User model) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2,model.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void update(User model) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getPassword());
            preparedStatement.setLong(3, model.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public User find(long userId) {
        User tempUser;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                tempUser = new User(userId, name, password);
                return tempUser;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return null;
    }

    @Override
    public User findOneByName(String name) {
        User tempUser;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                Long userId = resultSet.getLong("id");
                tempUser = new User(userId, name, password);
                return tempUser;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return null;
    }
}
