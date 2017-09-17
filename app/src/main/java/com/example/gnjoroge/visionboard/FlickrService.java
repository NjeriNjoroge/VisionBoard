package com.example.gnjoroge.visionboard;

/**
 * Created by gnjoroge on 9/15/17.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class FlickrService {


    //takes the keyword user inputs and a callback to execute the API
    public static void findPhotos(String category, Callback callback) {

        //Creating a New Request with OkHttp
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.FLICKR_BASE_URL)
                .newBuilder();
        urlBuilder.addQueryParameter(Constants.FLICKR_CATEGORY_QUERY_PARAMETER, category);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Category> processResults(Response response) {
        ArrayList<Category> categories = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject flickrJSON = new JSONObject(jsonData);
                JSONArray photosJSON = flickrJSON.getJSONObject("photos").getJSONArray("photo");
                for (int i = 0; i < photosJSON.length(); i++) {
                    JSONObject boardJSON = photosJSON.getJSONObject(i);
                    String farm = boardJSON.getString("farm");
                    String id = boardJSON.getString("id");
                    String secret = boardJSON.getString("secret");
                    String server = boardJSON.getString("server");

//JSONArray photoJSON = flickrJSON.getJSONObject("photos").getJSONArray("photo");


//                    ArrayList<String> photo = new ArrayList<>();
//                    JSONArray photoJSON = boardJSON.getJSONObject("photos")
//                            .getJSONArray("photo");
//                    for (int i = 0; i < photoJSON.length(); i++) {
//                        photo.add(photoJSON.get(i).toString());
//                    }

                    Category category = new Category(farm, id, secret, server);
                    categories.add(category);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return categories;
    }

}