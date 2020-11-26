package ru.itis.repositories;


import ru.itis.models.Image;

import java.util.List;

public interface SearchRepository extends CrudRepository<Image> {
    List<Image> findByWord(String word);
}
