package com.orbit.mygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.orbit.mygame.MyGame;

public class IntroScreen implements Screen{

    final MyGame app;

    private float progress;
    private ShapeRenderer shapeRenderer;

    SpriteBatch batch;
    Texture texture;

    public IntroScreen(final MyGame app){
        this.app = app;
        this.progress = 0f;
        this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        this.batch = new SpriteBatch();
        //no assetmanager yet
        this.texture = new Texture(Gdx.files.internal("title.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw( texture,app.gui.getStage().getViewport().getWorldWidth()/2-texture.getWidth()/2, app.gui.getStage().getViewport().getWorldHeight()/2 );
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BROWN);
        shapeRenderer.rect(app.gui.getStage().getViewport().getWorldWidth()/2-152,app.gui.getStage().getViewport().getWorldHeight()/3-2, 304, 12);
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GOLDENROD);
        shapeRenderer.rect(app.gui.getStage().getViewport().getWorldWidth()/2-151,app.gui.getStage().getViewport().getWorldHeight()/3, 298*progress, 8);
        shapeRenderer.end();

        if( app.assets.mngr.update() ){
            app.screenCtrl.switchTo(new MainScreen(app));
        }
        progress = app.assets.mngr.getProgress();
    }

    @Override
    public void resize(int width, int height) {
        app.gui.getStage().getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        texture.dispose();
    }
}
