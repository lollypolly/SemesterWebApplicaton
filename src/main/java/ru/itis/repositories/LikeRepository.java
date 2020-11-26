package ru.itis.repositories;

import java.util.List;

public interface LikeRepository{
    Boolean like(int user_id,String cat_id);
    Boolean isLiked(int user_id, String cat_id);
    List<String> findImageId(int user_id);
}
