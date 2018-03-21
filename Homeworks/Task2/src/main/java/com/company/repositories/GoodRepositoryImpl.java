package com.company.repositories;

import com.company.models.GoodModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GoodRepositoryImpl implements GoodRepository {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM goods WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM goods";
    private static final String SQL_INSERT_USER = "INSERT INTO goods(name, amount) VALUES (?, ?)";
    private static final String SQL_UPDATE_BY_ID = "UPDATE goods" + " SET name = ?, amount = ? WHERE id = ?";


    private Connection connection;


    public GoodRepositoryImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<GoodModel> findAll() {
        List<GoodModel> goods = new LinkedList<GoodModel>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int amount = resultSet.getInt("amount");
                GoodModel newOne = new GoodModel(id, name, amount);
                goods.add(newOne);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return goods;
    }

    @Override
    public void save(GoodModel model) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setInt(2,model.getAmount());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void update(GoodModel model) {
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
    public GoodModel find(long Id) {
        GoodModel tempGood;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, Id);
            ResultSet resultSet =  preparedStatement.executeQuery();
            if (resultSet.next()) {
                int amount = resultSet.getInt("amount");
                String name = resultSet.getString("name");
                tempGood = new GoodModel(Id, name, amount);
                return tempGood;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        return null;
    }
}
