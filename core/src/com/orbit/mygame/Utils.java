package com.orbit.mygame;

import java.sql.Timestamp;

public class Utils {


    public static long getTimestamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }
}
