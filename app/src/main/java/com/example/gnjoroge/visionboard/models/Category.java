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

    private String farm;
    private String server;
    private String id;
    private String secret;
    private String title;
    //private String imageUrl;
    private String pushId;


public Category () {}

    public Category(String farm, String server, String id, String secret, String title) {

        this.farm = farm;
        this.id = id;
        this.secret = secret;
        this.server = server;
        this.title = title;
        //this.imageUrl = setImage();
    }

public String getFarm(){
    return farm;
}

public String getId() {
    return id;
}

public String getServer(){
    return server;
}

public String getTitle() {
    return title;
}

public String getSecret(){
    return secret;
}

public String getImage(){
    String imageUrl  = "https://farm"+getFarm()+".staticflickr.com/"+getServer()+"/"+getId()+"_"+getSecret()+".jpg";
    return imageUrl;
}

public String getPushId() {
    return pushId;
}

public void setPushId(String pushId) {
    this.pushId = pushId;
}


}
