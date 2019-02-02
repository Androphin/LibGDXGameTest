package com.orbit.mygame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.orbit.mygame.ActionController;

//extend von WidgetGroup oder Group eventuell passender!
public class RegisterWindow extends Table {
    float startX;
    float startY;

    ActionController actionController;

    I18NBundle language;

    Table formTable;

    ScrollPane scrollPane;

    Label lblRegisterTitle;
    TextField txtPlayername;
    TextButton btnCreate;
    TextButton btnPrivacyPolicy;

    private final RegisterWindow reference;

    public RegisterWindow(final ActionController actionController, Skin skin, I18NBundle language){
        super(skin);
        this.actionController = actionController;
        this.language = language;
        this.reference = this;

        formTable = new Table(skin);
        Image bg = new Image(new Texture(Gdx.files.internal("450p/bg_center.png")));
        formTable.background( bg.getDrawable() );

        scrollPane = new ScrollPane(formTable, skin);

        lblRegisterTitle = new Label(language.get("registerTitle"), skin);
        txtPlayername = new TextField("", skin);
        txtPlayername.setMaxLength(32);
        txtPlayername.setMessageText( language.get("registerLabelPlayername") );
        txtPlayername.setAlignment(Align.center);
        txtPlayername.getStyle().font = skin.getFont("Aldo");
        btnCreate = new TextButton(language.get("registerBtnCreateAccount"), skin);
        btnCreate.getLabelCell().fill();
        btnCreate.getLabel().setStyle(new Label.LabelStyle(skin.getFont("Aldo"), Color.BLACK));
        btnPrivacyPolicy = new TextButton(language.get("registerBtnPrivacy"), skin);

        btnCreate.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //slideOut(startX, startY);
                if( !btnCreate.isDisabled() ){
                    lockButtons(true);
                    validateInput();
                    actionController.createAccount( reference, txtPlayername.getText() );
                }
                this.cancel();
            }
        });

        btnPrivacyPolicy.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //URL intend
                this.cancel();
            }
        });

        formTable.row().expand();
        formTable.add(lblRegisterTitle).center();
        formTable.row();
        formTable.add( new Image(new Texture(Gdx.files.internal("450p/avatar.png"))) );
        formTable.row();
        formTable.add(txtPlayername).fill().padLeft(150f).padRight(150f);
        formTable.row();
        formTable.add(btnCreate).height(50f);
        formTable.row().pad(20f);
        formTable.add( btnPrivacyPolicy );
        formTable.setDebug(true);

        this.setFillParent(true);
        //this.setDebug(true);
        this.padTop(50f).padLeft(100f).padRight(100f);
        this.add( scrollPane ).grow();
        this.pack();
    }

    public void validateInput(){
        txtPlayername.getText();
    }

    public void lockButtons(boolean lock){
        txtPlayername.setDisabled(lock);
        btnCreate.setDisabled(lock);
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
