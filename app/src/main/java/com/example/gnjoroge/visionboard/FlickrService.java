package com.example.gnjoroge.visionboard;

/**
 * Created by gnjoroge on 9/15/17.
 */
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class FlickrService {

    //takes the keyword user inputs and a callback to execute the API
    public static void findPhotos(String category, Callback callback) {

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


}
