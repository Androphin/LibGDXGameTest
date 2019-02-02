package com.orbit.mygame;

import java.util.ArrayList;

public class Notifications {
    ArrayList<String> notifications;

    public Notifications(){
        this.notifications = new ArrayList<String>();
    }

    public void add(String str){
        System.out.println("Notification: "+str);
        this.notifications.add(str);
    }

    public ArrayList<String> getAll(){
        return this.notifications;
    }
}
