package com.orbit.mygame.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Building {
    Sprite imageSprite;

    int gridspaceX;
    int gridspaceY;

    int positionX;
    int positionY;

    int inventarSlots;
    int upgradeSlots;

    public Building(){

    }

    public Building(Texture tex, int gridspaceX, int gridspaceY, int posX, int posY){
        this.imageSprite = new Sprite(tex);
        this.gridspaceX = gridspaceX;
        this.gridspaceY = gridspaceY;
        this.positionX = posX;
        this.positionY = posY;
        imageSprite.setAlpha(0.8f);
    }

    public int getGridspaceX(){
        return this.gridspaceX;
    }
    public int getGridspaceY(){
        return this.gridspaceY;
    }
    //returns array with first value occupied space x and second y
    public int[] getGridspace(){
        int[] space = new int[2];
        space[0] = this.gridspaceX;
        space[1] = this.gridspaceY;
        return space;
    }

    public void setSize(float tileWidthDiagonale){
        this.imageSprite.setSize(this.gridspaceX*tileWidthDiagonale,this.gridspaceY*tileWidthDiagonale);
    }
    public void setPosition(float[] position){
        float x = position[0]-(this.imageSprite.getWidth()/2);
        float y = position[1];
        this.imageSprite.setPosition( x, y );
    }

    public float getWidth(){
        return this.imageSprite.getWidth();
    }

    public void setOrigin(float x, float y){
        this.imageSprite.setOrigin(x,y);
    }
    public void draw(Batch batch){
        imageSprite.draw(batch);
    }

    public Texture getTexture(){
        return this.imageSprite.getTexture();
    }

    public Sprite getSprite(){
        return this.imageSprite;
    }
}
