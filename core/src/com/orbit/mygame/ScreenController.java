package com.orbit.mygame;

import com.badlogic.gdx.Screen;

public class ScreenController {

    private final MyGame app;
    private Screen currentScreen, lastScreen;

    public ScreenController(final MyGame game){
        this.app = game;
    }

    public void render(float deltaTime){
        if( this.currentScreen != null ){
            this.currentScreen.render(deltaTime);
        }
    }
    public void resize(int w, int h){
        if( this.currentScreen != null ){
            this.currentScreen.resize(w,h);
        }

    }
    public void dispose(){
        if( lastScreen != null ) {
            lastScreen.dispose();
        }
        currentScreen.dispose();
    }

    public void switchTo(Screen screen){
        if( currentScreen != null ){
            currentScreen.hide();
            lastScreen = currentScreen;
        }
        currentScreen = screen;
        currentScreen.show();
    }
}
