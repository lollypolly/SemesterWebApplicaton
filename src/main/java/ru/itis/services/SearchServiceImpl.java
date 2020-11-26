package ru.itis.services;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.itis.models.Image;
import ru.itis.repositories.SearchRepository;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SearchServiceImpl implements SearchService {

    private final String BASE_URL = "https://api.thecatapi.com/v1/images/%s";
    private final String API_KEY = "eb2eedc9-eda7-4c1d-9f50-6f07e0ed0cf4";

    private SearchRepository searchRepository;

    public SearchServiceImpl(SearchRepository searchRepository){
        this.searchRepository = searchRepository;
    }

    @Override
    public JSONArray getSearchCats(String word) {

        List<Image> imageList = searchRepository.findByWord(word);
        JSONArray arr = new JSONArray();

        for (Image image: imageList) {
            InputStreamReader inputStreamReader = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(String.format(BASE_URL, image.getUrl()));
                URLConnection uc = url.openConnection();

                uc.setRequestProperty("accept", "application/json");
                uc.setRequestProperty("x-api-key", API_KEY);

                inputStreamReader = new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8);
                reader = new BufferedReader(inputStreamReader);
                String jsonText = readAll(reader);


                arr.put(jsonText);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null) reader.close();
                    if (inputStreamReader != null) inputStreamReader.close();
                } catch (Exception e) {
                }
            }
        }

        return arr;
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
