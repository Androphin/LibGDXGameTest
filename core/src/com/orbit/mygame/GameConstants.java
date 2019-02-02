package com.orbit.mygame;

public class GameConstants {
    /***
     General
     ***/
    public static final String GAME_VERSION = "1.0.0";
    public static final String PREFERENCE_NAME = "MyGameSettings";

    /***
     * Graphics
     */
    public static final int DEFAULT_FPS = 60;
    public static final int VIRTUAL_SCREEN_WIDTH = 800;
    public static final int VIRTUAL_SCREEN_HEIGTH = 450;
    public static final float DEFAULT_ASPECT_RATIO = 1.7f; //16:9

    /***
    Network
     ***/
    //Server
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 10123;

    public static final int PROTOCOL_VERSION = 1;

    public static final int UPDATE_REQUEST_INTERVAL = 30000; //ms
    /***
     Optimizations
     ***/
}
