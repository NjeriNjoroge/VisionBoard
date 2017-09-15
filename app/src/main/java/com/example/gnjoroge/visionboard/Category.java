package com.example.gnjoroge.visionboard;

/**
 * Created by gnjoroge on 9/15/17.
 */

public class Category {

    private String mFarmId;
    private String mServerId;
    private String mId;
    private String mSecrect;



    public Category(String farm, String server, String id, String secret) {

        this.mFarmId = farm;
        this.mId = id;
        this.mSecrect = secret;
        this.mServerId = server;
    }

public String getFarm(){
    return mFarmId;
}

public String getId(){
    return mId;
}

public String getServer(){
    return mServerId;
}

public String getSecret(){
    return mSecrect;
}
}
