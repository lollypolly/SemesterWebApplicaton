package ru.itis.repositories;

import org.springframework.util.StringUtils;
import ru.itis.models.Image;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchRepositoryImpl implements SearchRepository {

    private DataSource dataSource;

    private final String SQL_FIND_IMAGE_BY_WORD = "select * from search where ?=any(keywords);";

    public SearchRepositoryImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<Image> findByWord(String word) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Image> getImagesByWord = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_IMAGE_BY_WORD);
            statement.setString(1, StringUtils.capitalize(word.toLowerCase()));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Image image = Image.builder()
                            .url(resultSet.getString("cat_id")).build();
                    getImagesByWord.add(image);
                }
            }

            return getImagesByWord;

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
    public void save(Image entity) {

    }

    @Override
    public Image findById(Long id) {
        return null;
    }

    @Override
    public List<Image> findAll() {
        return null;
    }
}
