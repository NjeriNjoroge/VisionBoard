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

    private String FarmId;
    private String ServerId;
    private String Id;
    private String Secrect;
    private String Title;

public Category () {}

    public Category(String farm, String server, String id, String secret, String title) {

        this.FarmId = farm;
        this.Id = id;
        this.Secrect = secret;
        this.ServerId = server;
        this.Title = title;
    }

public String getFarm(){
    return FarmId;
}

public String getId() {
    return Id;
}

public String getServer(){
    return ServerId;
}

public String getTitle() {
    return Title;
}

public String getSecret(){
    return Secrect;
}

public String getImage(){
    String imageUrl  = "https://farm"+getFarm()+".staticflickr.com/"+getServer()+"/"+getId()+"_"+getSecret()+".jpg";
    return imageUrl;
}


}
