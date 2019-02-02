package com.orbit.mygame.menus;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class MainMenuButton extends Actor {
    private String label;
    private int layout;
    private int action;
    private boolean state;

    public MainMenuButton(String label){
        this.label = label;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public void setAction(){

    }

}
