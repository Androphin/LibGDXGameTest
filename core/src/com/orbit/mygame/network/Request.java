package com.orbit.mygame.network;

import com.badlogic.gdx.Net;

import java.util.HashMap;

public class Request {
    private HashMap<String, String> data;

    private Net.HttpRequest httpRequest;

    public Request(String type){
        this.httpRequest = new Net.HttpRequest("POST");

        switch( type ){
            case "register": break;
            case "login": break;
            case "update": break;
            case "commit": break;
        }
    }

    public void setURL(String url){
        this.httpRequest.setUrl(url);
    }

    public void register(){
        data.put("action", "create_account");
        //general data
        data.put("android_country","");
        data.put("app_version","");
        data.put("client_timestamp","");
        //profile data
        data.put("playername","");
        //for unique account creation

    }
}
