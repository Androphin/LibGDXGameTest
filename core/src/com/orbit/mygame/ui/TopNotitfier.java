package com.orbit.mygame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TopNotitfier extends Table {
    float startX;
    float startY;

    Label lblText;
    Image img;
    Boolean showLoader;

    public TopNotitfier(Skin skin){
        super(skin);

        showLoader = true;
        lblText = new Label("", skin);
        img = new Image();

        //this.setFillParent(true);
        //this.center().top();
        this.pad(1f);
        this.add(img).pad(1f);
        this.add(lblText).pad(1f);
        this.background(new Image(new Texture(Gdx.files.internal("notification.png"))).getDrawable() );
        this.setDebug(true);
        this.pack();
    }
    public void show(String message){
        this.show(message, true);
    }
    public void show(String message, boolean showLoader){
        this.lblText.setText(message);
        this.showLoader = showLoader;
        this.addAction( new SequenceAction(Actions.moveTo(startX, startY-this.getPrefHeight(), 0.2f, Interpolation.sineOut)));
    }

    public void hide(){
        this.addAction( new SequenceAction(Actions.moveTo(startX, startY, 0.2f, Interpolation.sineIn)));
    }

    @Override
    public void setPosition(float x, float y){
        this.setX(x);
        this.setY(y);
        startX = x;
        startY = y;
    }
}
