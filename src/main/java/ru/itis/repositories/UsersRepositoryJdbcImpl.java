package ru.itis.repositories;

import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource dataSource;

    //language=SQL
    private final static String SQL_INSERT = "insert into service_user(first_name, last_name, username, email, hash_password) " +
            "values (?, ?, ?, ?, ?)";
    private final static String SQL_SELECT_All = "select * from service_user";
    private final static String SQL_SELECT_USERS_BY_EMAIL = SQL_SELECT_All + " where email =?";
    private final static String SQL_INSERT_IMAGE ="insert into files (id, storage_file_name, original_file_name, type, size) values (?,?,?,?,?);";

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(User entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getLastName());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getUsername());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getHashPassword());
            int affectedRows = statement.executeUpdate();//запускает

            if (affectedRows == 0) {
                throw new SQLException("Problem with insert user");
            }

            generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getInt("id"));

                statement=connection.prepareStatement(SQL_INSERT_IMAGE);
                statement.setInt(1,entity.getId());
                statement.setString(2,"7a39288a-9f1d-4e7d-a272-91ffe3382fb6");
                statement.setString(3,"main.png");
                statement.setString(4,"image/png");
                statement.setLong(5,13683);
                statement.execute();
            } else {
                throw new SQLException("Problem with retrieve id");
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException throwables) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                }
            }
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        Optional<User> getUserByEmail = Optional.empty();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_USERS_BY_EMAIL);
            statement.setString(1,email);

            try (ResultSet isUserByEmail = statement.executeQuery()) {
                if (isUserByEmail.next()) {
                    User user = User.builder().id(isUserByEmail.getInt("id"))
                            .lastName(isUserByEmail.getString("last_name"))
                            .firstName(isUserByEmail.getString("first_name"))
                            .username(isUserByEmail.getString("username"))
                            .email(isUserByEmail.getString("email"))
                            .hashPassword(isUserByEmail.getString("hash_password")).build();
                    getUserByEmail = Optional.of(user);
                }
            }

        return getUserByEmail;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                }
            }
        }
    }

    @Override
    public User findById(Long id) {
        return null;
    }


    @Override
    public Optional<Object> getUserByID(Long id) {
        return Optional.empty();
    }
}
