package ru.itis.services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public interface CatService {
    JSONArray getAllCats(int limit, int page);
    boolean imageLiked(int user_id, String cat_id);
    JSONObject getImageByID(String id);
    boolean likeImage(int user_id, String cat_id);
    List<String> getLikedImages(int user_id);
}
