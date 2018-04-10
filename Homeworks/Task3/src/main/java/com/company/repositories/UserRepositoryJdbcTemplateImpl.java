package com.company.repositories;

import com.company.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserRepositoryJdbcTemplateImpl implements UserRepository {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM usery WHERE id = ?";
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM usery WHERE name = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM usery";
    private static final String SQL_INSERT_USER = "INSERT INTO usery(name, password) VALUES (?, ?)";
    private static final String SQL_UPDATE_BY_ID = "UPDATE usery" + " SET name = ?, password = ? WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM humans WHERE id = ?";

    private JdbcTemplate template;

    public UserRepositoryJdbcTemplateImpl(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (ResultSet resultSet, int i) -> {
        Integer id = resultSet.getInt("id");
        String firstName = resultSet.getString("name");
        String password = resultSet.getString("password");
        User user = new User(id, firstName, password);
        return user;
    };

    @Override
    public List<User> findAll() {
        return template.query(SQL_FIND_ALL, userRowMapper);
    }

    @Override
    public void save(User model) {
        template.update(SQL_INSERT_USER, model.getName(), model.getPassword());
    }

    @Override
    public void update(User model) {
        template.update(SQL_UPDATE_BY_ID, model.getName(), model.getPassword());
    }

    @Override
    public void delete(User model) {
        List<User> result = template.query(SQL_FIND_BY_ID, userRowMapper, model.getId());
        if (result.size() > 0) {
            template.query(SQL_DELETE_BY_ID, userRowMapper, model.getId());
        } else {
            throw new IllegalArgumentException("There is no such human");
        }
    }

    @Override
    public User find(long userId) {
        List<User> result = template.query(SQL_FIND_BY_ID, userRowMapper, userId);
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public User findOneByName(String name) {
        List<User> result = template.query(SQL_FIND_BY_NAME, userRowMapper, name);
        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }
}
