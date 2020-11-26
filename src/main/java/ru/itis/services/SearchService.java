package ru.itis.services;

import org.json.JSONArray;
import org.json.JSONObject;

public interface SearchService {
    JSONArray getSearchCats(String word);
}
