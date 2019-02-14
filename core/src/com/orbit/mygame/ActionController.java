package com.orbit.mygame;


import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.orbit.mygame.ui.RegisterWindow;

import java.util.HashMap;

public class ActionController{

    final MyGame app;

    UserEventListener eventListener;

    public ActionController(final MyGame app){
        this.app = app;
    }

    public void createAccount( final RegisterWindow ref, String playername ) {
    }

    public void loginGoogle(){
        eventListener.signInGoogle();
    }
    public void loginFacebook(){
        eventListener.signInFacebook();
    }

    public void setEventListener(UserEventListener listener){
        this.eventListener = listener;
    }
}
