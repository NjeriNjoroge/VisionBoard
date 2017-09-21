package com.example.gnjoroge.visionboard.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;

/**
 * Created by gnjoroge on 9/15/17.
 */

public class Category {

    private String mFarmId;
    private String mServerId;
    private String mId;
    private String mSecrect;

    //private String mTitle;



    public Category(String farm, String server, String id, String secret) {

        this.mFarmId = farm;
        this.mId = id;
        this.mSecrect = secret;
        this.mServerId = server;
        //this.mTitle = title;
    }

public String getFarm(){
    return mFarmId;
}

public String getId() {
    return mId;
}

public String getServer(){
    return mServerId;
}

public String getSecret(){
    return mSecrect;
}

////public String getTitle(){
//        return mTitle;
//    }


public String getImage(){
    String imageUrl  = "https://farm"+getFarm()+".staticflickr.com/"+getServer()+"/"+getId()+"_"+getSecret()+"_s.jpg";
    return imageUrl;
}
//public Bitmap getImage(){
//    String imageUrl  = "https://farm"+getFarm()+".staticflickr.com/"+getServer()
//            +"/"+getId()+"_"+getSecret()+"_m.jpg";
//    try {
//        URL url = new URL(imageUrl);
//        return BitmapFactory.decodeStream(url.openStream());
//    } catch (IOException e) {
//        e.printStackTrace();
//        return null;
//    }
//}



}
