package com.orbit.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.orbit.mygame.ui.TopNotitfier;

public class GUI implements Disposable {

    final MyGame app;

    final int VIRTUAL_SCREEN_WIDTH;
    final int VIRTUAL_SCREEN_HEIGHT;

    WidgetGroup[] layer;
    Stage stage;
    Skin skin;

    TopNotitfier topBarNotifier;

    public GUI(final MyGame app){
        this.app = app;
        VIRTUAL_SCREEN_WIDTH = app.constants.VIRTUAL_SCREEN_WIDTH;
        VIRTUAL_SCREEN_HEIGHT = app.constants.VIRTUAL_SCREEN_HEIGTH;

        //stage = new Stage(new FitViewport(800, 480)); //AR 16:10 1,6 Galaxy S2
        stage = new Stage(new FitViewport(VIRTUAL_SCREEN_WIDTH, VIRTUAL_SCREEN_HEIGHT)); //AR 16:9 1,7
        //stage = new Stage(new FitViewport(1920, 1080));
        //stage = new Stage(new FitViewport(2560, 1440)); //Galaxy S6
        //stage = new Stage(new FitViewport(3840, 2160));
        //stage = new Stage(new FitViewport(MyGame.V_WIDTH, MyGame.V_HEIGHT));
        //stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = app.assets.mngr.get("ui/default_uiskin.json", Skin.class);
        skin.add("Aldo", app.assets.mngr.get("fonts/AldotheApache.ttf"), BitmapFont.class);
        //skin.add("Kazmann", app.assets.mngr.get("fonts/KazmannSans.ttf"), BitmapFont.class);
        //skinSettings = this.app.assets.mngr.get("ui/settingsskin.json", Skin.class);

        topBarNotifier = new TopNotitfier(skin);
        topBarNotifier.setPosition(VIRTUAL_SCREEN_WIDTH/2-topBarNotifier.getPrefWidth()/2, VIRTUAL_SCREEN_HEIGHT);
        topBarNotifier.setZIndex(999);

        int layers = 3;
        layer = new WidgetGroup[layers];
        for(int l=0; l<layers; l++){
            layer[l] = new WidgetGroup();
            layer[l].setName("layer"+l);
            layer[l].setFillParent(true);
            stage.addActor( layer[l] );
        }
        layer[2].addActor(topBarNotifier);
    }

    public WidgetGroup getLayer(int index){
        return this.layer[index];
    }

    public Stage getStage(){
        return this.stage;
    }
    public Skin getSkin(){
        return this.skin;
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.remove("Aldo", BitmapFont.class);
        skin.dispose();
    }
}
