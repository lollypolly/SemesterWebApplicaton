package ru.itis.services;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.itis.repositories.LikeRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CatServiceImpl implements CatService {

    private final String BASE_URL = "https://api.thecatapi.com/v1/images/search?limit=%d&page=%d";
    private final String BY_ID = "https://api.thecatapi.com/v1/images/%s";
    private final String API_KEY = "eb2eedc9-eda7-4c1d-9f50-6f07e0ed0cf4";

    private LikeRepository likeRepository;

    public CatServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public JSONArray getAllCats(int limit, int page) {
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(String.format(BASE_URL, limit, page));
            URLConnection uc = url.openConnection();

            uc.setRequestProperty("accept", "application/json");
            uc.setRequestProperty("x-api-key", API_KEY);

            inputStreamReader = new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8);
            reader = new BufferedReader(inputStreamReader);
            String jsonText = readAll(reader);


            return new JSONArray(jsonText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (inputStreamReader != null) inputStreamReader.close();
            } catch (Exception e) {
            }
        }

        return null;
    }

    @Override
    public boolean imageLiked(int user_id, String cat_id) {
        return likeRepository.isLiked(user_id, cat_id);
    }


    @Override
    public JSONObject getImageByID(String id) {
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(String.format(BY_ID, id));
            URLConnection uc = url.openConnection();

            uc.setRequestProperty("accept", "application/json");
            uc.setRequestProperty("x-api-key", API_KEY);

            inputStreamReader = new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8);
            reader = new BufferedReader(inputStreamReader);
            String jsonText = readAll(reader);


            return new JSONObject(jsonText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
                if (inputStreamReader != null) inputStreamReader.close();
            } catch (Exception e) {
            }
        }

        return null;
    }

    @Override
    public boolean likeImage(int user_id, String cat_id) {
        return likeRepository.like(user_id, cat_id);
    }

    @Override
    public List<String> getLikedImages(int user_id) {
        return likeRepository.findImageId(user_id);
    }


    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


}
