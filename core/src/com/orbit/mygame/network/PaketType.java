package com.orbit.mygame.network;

public class PaketType {
    public static int PROTOCOL_INCOMPATIBLE = 1;
    public static int SERVER_OVERLOAD = 2;
    public static int PAKET_RECEIVED_INVALID = 3;

    public static int REGISTER = 100;
    public static int REGISTER_SUCCESS = 101;
    public static int REGISTER_FAILURE = 102;

    public static int LOGIN = 200;
    public static int LOGIN_SUCCESS = 201;
    public static int LOGIN_FAILURE = 202;
    public static int LOGIN_LOCK = 203;
    public static int LOGIN_OVERLOAD = 204;

    public static int UPDATE = 300;
    public static int UPDATE_SUCCESS = 301;
    public static int UPDATE_FAILURE = 302;

    public static int COMMIT = 400;
    public static int COMMIT_SUCCESS = 401;
    public static int COMMIT_FAILURE = 402;
    public static int COMMIT_INVALID = 403;

    public static int CHECK = 500;
    public static int CHECK_SUCCESS = 501;
    public static int CHECK_FAILURE = 502;

}
