package com.company.repositories;

import com.company.models.Good;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GoodRepositoryJDBCImpl implements GoodRepositoryJDBC {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM goods WHERE id = ?";
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM goods WHERE name = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM goods";
    private static final String SQL_INSERT_USER = "INSERT INTO goods(name, amount) VALUES (?, ?)";
    private static final String SQL_UPDATE_BY_ID = "UPDATE goods" + " SET name = ?, amount = ? WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM goods WHERE id = ?";

    private Connection connection;


    public GoodRepositoryJDBCImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Good> findAll() {
        List<Good> goods = new LinkedList<Good>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int amount = resultSet.getInt("amount");
                Good newOne = new Good(id, name, amount);
                goods.add(newOne);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return goods;
    }

    @Override
    public void save(Good model) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setInt(2, model.getAmount());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void update(Good model) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setInt(2, model.getAmount());
            preparedStatement.setLong(3, model.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void delete(Good model) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setLong(1, model.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Good find(long Id) {
        Good tempGood;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int amount = resultSet.getInt("amount");
                String name = resultSet.getString("name");
                tempGood = new Good(Id, name, amount);
                return tempGood;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return null;
    }

    @Override
    public Good findOneByName(String name) {
        Good tempGood;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int amount = resultSet.getInt("amount");
                Long id = resultSet.getLong("id");
                tempGood = new Good(id, name, amount);
                return tempGood;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return null;
    }
}
