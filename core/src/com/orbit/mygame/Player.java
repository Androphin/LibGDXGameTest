package com.orbit.mygame;

import com.badlogic.gdx.Gdx;

public class Player {
    private boolean loggedin = false;

    public Player(){

    }

    /*
    Account on the phone detected
     */
    public boolean registered(){
        if( Gdx.files.internal("registered").exists() ){
            return true;
        }
        return false;
    }
    /*
    Check if player has logged in and has a valid token lease
     */
    public boolean hasToken(){
        return false;
    }
    public boolean validToken(){
        return false;
    }
    public boolean hasValidToken() { return false; }
    public boolean loggedin(){
        return false;
    }
}
