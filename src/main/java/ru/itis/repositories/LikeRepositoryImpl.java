package ru.itis.repositories;

import org.springframework.util.StringUtils;
import ru.itis.models.Image;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeRepositoryImpl implements LikeRepository {

    private DataSource dataSource;

    private final String SQL_SELECT_LIKE = "select \"like\" from like_image where user_id = ? and id = ?;";
    private final String SQL_DELETE_LIKE = "delete from like_image where user_id = ? and id = ?;";
    private final String SQL_INSERT_LIKE = "insert into like_image (user_id, id, \"like\") values (?, ?, ?)";
    private final String SQL_SELECT_LIKED_IMAGE = "select id from like_image where user_id =?;";

    public LikeRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Boolean like(int user_id, String cat_id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            if (isLiked(user_id, cat_id)) {
                statement = connection.prepareStatement(SQL_DELETE_LIKE);
                statement.setInt(1, user_id);
                statement.setString(2, cat_id);

                int affected = statement.executeUpdate();
                if (affected > 0) return false;
                else throw new SQLException("Something wrong with update value in like-image DB");
            } else {
                statement = connection.prepareStatement(SQL_INSERT_LIKE);
                statement.setInt(1, user_id);
                statement.setString(2, cat_id);
                statement.setBoolean(3, true);
                int affected = statement.executeUpdate();
                if (affected > 0) return true;
                else throw new SQLException("Something wrong with update value in like-image DB");
            }
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
    public Boolean isLiked(int user_id, String cat_id) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_LIKE)) {
            statement.setInt(1, user_id);
            statement.setString(2, cat_id);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<String> findImageId(int user_id) {

        List<String> arr = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_LIKED_IMAGE)) {
            statement.setInt(1, user_id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()){
                    arr.add(resultSet.getString("id"));
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return arr;
    }
}
