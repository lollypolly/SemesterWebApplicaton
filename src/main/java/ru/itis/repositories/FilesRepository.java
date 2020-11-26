package ru.itis.repositories;

import ru.itis.models.FileInfo;

public interface FilesRepository extends CrudRepository<FileInfo> {

    void save(Integer id,FileInfo entity);
}
