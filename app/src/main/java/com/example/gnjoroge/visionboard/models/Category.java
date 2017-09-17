package com.example.gnjoroge.visionboard.models;

/**
 * Created by gnjoroge on 9/15/17.
 */

public class Category {

    private String mFarmId;
    private String mServerId;
    private String mId;
    private String mSecrect;
   // private String mImage;



    public Category(String farm, String server, String id, String secret) {

        this.mFarmId = farm;
        this.mId = id;
        this.mSecrect = secret;
        this.mServerId = server;
        //this.mImage = image;
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
    String imageUrl  = "https://farm"+getFarm()+".staticflickr.com/"+getServer()+"/"+getId()+"_"+getSecret()+"_m.jpg";
    return imageUrl;
}


}