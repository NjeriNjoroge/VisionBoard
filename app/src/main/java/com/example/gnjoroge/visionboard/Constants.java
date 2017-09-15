package com.example.gnjoroge.visionboard;

/**
 * Created by gnjoroge on 9/15/17.
 */

//file references the Flickr credentials from gradle.properties

public class Constants {

    public static final String FLICKR_CONSUMER_KEY = BuildConfig.FLICKR_CONSUMER_KEY;

    public static final String FLICKR_CONSUMER_SECRET = BuildConfig.FLICKR_CONSUMER_SECRET;

    //building a request
    public static final String FLICKR_BASE_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=" + FLICKR_CONSUMER_KEY + "&text=health";


    public static final String FLICKR_CATEGORY_QUERY_PARAMETER = "category";

}
