package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.FileInfo;

import javax.sql.DataSource;
import java.util.List;

public class FilesRepositoryImpl implements FilesRepository {

    private DataSource dataSource;

    //language=SQL
    private final static String SQL_INSERT = "insert into files (id, storage_file_name, original_file_name, type, size) " +
            "values (?,?, ?, ?, ?) on conflict(id) do update set id=?,storage_file_name=?,original_file_name=?, type=?, size=?";

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "select * from files where id = ?";

    private JdbcTemplate jdbcTemplate;

    public FilesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<FileInfo> fileRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .originalFileName(row.getString("original_file_name"))
                    .storageFileName(row.getString("storage_file_name"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .build();


    @Override
    public void save(FileInfo entity) {

    }

    public void save(Integer id, FileInfo entity) {
        jdbcTemplate.update(SQL_INSERT,id, entity.getStorageFileName(), entity.getOriginalFileName(),
                entity.getType(),
                entity.getSize(),id, entity.getStorageFileName(), entity.getOriginalFileName(),
                entity.getType(),
                entity.getSize());
    }

    @Override
    public FileInfo findById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, fileRowMapper, id);
    }

    @Override
    public List<FileInfo> findAll() {
        return null;
    }
}
