package com.orbit.mygame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.I18NBundle;
import com.orbit.mygame.ActionController;

public class WelcomeWindow extends Table {
    float startX, startY;

    private ActionController actionController;

    ScrollPane scrollPane;
    Table formTable;
    Label lblTitle;
    Label lblText;
    TextButton btnGoogle;
    TextButton btnFacebook;
    TextButton btnSignIn;
    TextButton btnCreateAccount;
    TextButton btnTermsOfService;
    TextButton btnPrivacyPolicy;

    public WelcomeWindow(final ActionController actionController, Skin skin, I18NBundle language){
        super(skin);
        this.actionController = actionController;

        formTable = new Table(skin);
        Image bg = new Image(new Texture(Gdx.files.internal("450p/bg_center.png")));

        scrollPane = new ScrollPane(formTable, skin);
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        scrollPaneStyle.background = bg.getDrawable();
        scrollPane.setStyle(scrollPaneStyle);
        scrollPane.setupFadeScrollBars(0f, 0f);

        lblTitle = new Label( language.get("welcomeTitle"), skin);
        lblText = new Label( language.get("welcomeText")+language.get("welcomeLegal")  , skin);
        lblText.setWrap(true);
        btnGoogle = new TextButton( language.get("welcomeBtnGoogle"), skin);
        btnFacebook = new TextButton( language.get("welcomeBtnFacebook"), skin);
        btnSignIn = new TextButton( language.get("welcomeBtnSignIn"), skin);
        btnSignIn.setSize(20, 10);
        btnCreateAccount = new TextButton( language.get("welcomeBtnCreateAccount"), skin);
        btnTermsOfService =  new TextButton( language.get("registerBtnTermsOfService"), skin);
        btnPrivacyPolicy = new TextButton( language.get("registerBtnPrivacy"), skin);

        btnGoogle.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                actionController.loginGoogle();
                this.cancel();
            }
        });
        btnFacebook.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                actionController.loginFacebook();
                this.cancel();
            }
        });
        btnSignIn.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                this.cancel();
            }
        });
        btnCreateAccount.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //how to trigger event for slide-in animation on RegisterWindow?
                //?????
                this.cancel();
            }
        });
        btnTermsOfService.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                this.cancel();
            }
        });
        btnPrivacyPolicy.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                this.cancel();
            }
        });

        //formTable.defaults().expand();
        formTable.row().left();
        formTable.add(lblTitle).colspan(2);
        formTable.row();
        formTable.add(lblText).colspan(2).fill();
        formTable.row();
        formTable.add(btnGoogle).colspan(2);
        formTable.row();
        formTable.add(btnFacebook).colspan(2);
        formTable.row();
        formTable.add(btnSignIn).colspan(2);
        formTable.row();
        formTable.add(btnCreateAccount).colspan(2);
        formTable.row();
        formTable.add(btnTermsOfService);
        formTable.add(btnPrivacyPolicy);
        formTable.setDebug(true);

        this.setFillParent(true);
        //this.setDebug(true);
        this.padTop(150f).padLeft(120f).padRight(120f);
        this.add( scrollPane ).grow();
        this.pack();
    }

    public boolean getLegalInfoAccepted(){
        return true;
    }

    public void lockButtons(boolean lock){

    }

    public void slideIn(){
        this.addAction( new SequenceAction(Actions.moveTo(0, 0, 0.5f, Interpolation.circle)));
    }
    public void slideOut(float x, float y){
        this.addAction( new SequenceAction(Actions.moveTo(startX, startY, 0.5f, Interpolation.circle)));
    }

    @Override
    public void setPosition(float x, float y){
        this.setX(x);
        this.setY(y);
        startX = x;
        startY = y;
    }
}
