package com.orbit.mygame.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.I18NBundle;

public class SettingsWindow extends Table{

    I18NBundle language;
    Skin skin;
    Table rootTable, headerTbl, navBtnTbl;
    Label windowTitle;
    ImageButton btnExit;
    TextButton btnGame, btnGraphics, btnNetwork, btnHud, btnProfile, btnNotifications, btnSocialMedia;
    ScrollPane settingsContent;
    Table scrollpaneInnerTblGame = new Table();

    SelectBox<String> aspectRatio;

    public SettingsWindow(Skin skin, I18NBundle lang){
        super(skin);
        this.language = lang;
    }

    public SettingsWindow(){
        this.rootTable = new Table(skin);
        this.headerTbl = new Table(skin);
        this.navBtnTbl = new Table(skin);
        rootTable.setFillParent(true);
        rootTable.top().left();
        rootTable.defaults().top().left();
        headerTbl.defaults();
        navBtnTbl.defaults().fillX();
        rootTable.setDebug(true);
        //navBtnTbl.setDebug(true);

        //window
        this.windowTitle = new Label(this.language.get("settingsTitle"),skin);
        windowTitle.setFontScale(2f);
        this.btnExit = new ImageButton(skin, "exit");
        TextureRegionDrawable separatorH = new TextureRegionDrawable( skin.getRegion("separator-horizontal") );
        TextureRegionDrawable separatorV = new TextureRegionDrawable( skin.getRegion("separator-vertical") );
        //nav buttons
        this.btnProfile = new TextButton(this.language.get("settingsNavBtnProfile"), skin);
        this.btnGame = new TextButton(this.language.get("settingsNavBtnGame"), skin);
        this.btnGraphics = new TextButton(this.language.get("settingsNavBtnGraphics"), skin);
        this.btnNotifications = new TextButton(this.language.get("settingsNavBtnNotifications"), skin);
        this.btnSocialMedia = new TextButton(this.language.get("settingsNavBtnSocialMedia"), skin);

        //default scrollpane content
        this.settingsContent = new ScrollPane(this.scrollpaneInnerTblGame, skin);


        //header
        rootTable.row().colspan(3);
        headerTbl.add( windowTitle ).expandX().left();
        headerTbl.add(btnExit).pad(5);
        rootTable.add(headerTbl).fillX();
        rootTable.row().colspan(3);
        rootTable.add(new Image(separatorH));
        //row
        rootTable.row().expandY();
        //right content (nav buttons)
        navBtnTbl.add(btnProfile).fillX();
        navBtnTbl.row();
        navBtnTbl.add(btnGame);
        navBtnTbl.row();
        navBtnTbl.add(btnGraphics);
        navBtnTbl.row();
        navBtnTbl.add(btnNotifications);
        navBtnTbl.row();
        navBtnTbl.add(btnSocialMedia);
        rootTable.add(navBtnTbl);
        //vert. separator
        rootTable.add(new Image(separatorV));
        //left content (settings content)
        rootTable.add(settingsContent).expandX();


        //Game Settings

        //Graphic Settings

        //Network Settings

        //HUD Settings



        //listeners

    }

    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
    }

    public void switchScrollpaneContent(Actor type){
        this.settingsContent.setActor(type);
        //recalculate size?
    }
}
