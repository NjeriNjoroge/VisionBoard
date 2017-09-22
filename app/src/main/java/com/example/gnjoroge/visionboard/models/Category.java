package com.example.gnjoroge.visionboard.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.parceler.Parcel;

import java.io.IOException;
import java.net.URL;

/**
 * Created by gnjoroge on 9/15/17.
 */

@Parcel
public class Category {

    private String mFarmId;
    private String mServerId;
    private String mId;
    private String mSecrect;


public Category () {}

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




public String getImage(){
    String imageUrl  = "https://farm"+getFarm()+".staticflickr.com/"+getServer()+"/"+getId()+"_"+getSecret()+".jpg";
    return imageUrl;
}


}
