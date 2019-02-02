package com.orbit.mygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.orbit.mygame.MyGame;
import com.orbit.mygame.game.Gameworld;

public class GameScreen implements Screen {
	
	final MyGame app;

	float screenAspectRatio = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();

	OrthographicCamera cameraHUD;
	Viewport viewport;

	Gameworld gameworld;

	Texture tile_grass;
	SpriteBatch batch;

	Pixmap whiteScreenPixmap;
	Image whiteScreen;

	public class OrthoCamController extends InputAdapter {
		final OrthographicCamera camera;
		final Plane xzPlane = new Plane(new Vector3(0, 1, 0), 0);
		final Vector3 intersection = new Vector3();
		Sprite lastSelectedTile = null;

		public OrthoCamController (OrthographicCamera camera) {
			this.camera = camera;
		}
		final Vector3 curr = new Vector3();
		final Vector3 last = new Vector3(-1, -1, -1);
		final Vector3 delta = new Vector3();
		@Override public boolean touchDragged (int x, int y, int pointer) {
			Ray pickRay = cam.getPickRay(x, y);
			Intersector.intersectRayPlane(pickRay, xzPlane, curr);

			if(!(last.x == -1 && last.y == -1 && last.z == -1)) {
				pickRay = cam.getPickRay(last.x, last.y);
				Intersector.intersectRayPlane(pickRay, xzPlane, delta);
				delta.sub(curr);
				cam.position.add(delta.x, delta.y, delta.z);
			}
			last.set(x, y, 0);
			return false;
		}

		@Override public boolean touchUp(int x, int y, int pointer, int button) {
			last.set(-1, -1, -1);
			return false;
		}
	}
	OrthographicCamera cam;
	OrthoCamController ctrl;

	final Sprite buildingSprite;

	public GameScreen(final MyGame app){
		this.app = app;

		gameworld = new Gameworld();

		buildingSprite = new Sprite(new Texture((Gdx.files.internal("building.png"))));
		buildingSprite.flip(false,true);
		tile_grass = new Texture( Gdx.files.internal("tile_grass.png"));

		whiteScreenPixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
		whiteScreenPixmap.setColor(Color.WHITE);
		whiteScreenPixmap.fill();
		whiteScreen = new Image( new Texture(whiteScreenPixmap) );

		cam = new OrthographicCamera();
		viewport = new FitViewport(gameworld.getWorldWidth()+50, gameworld.getWorldHeight()+50, cam);
		cam.position.set(gameworld.getWorldHeight()/2, gameworld.getWorldHeight()/2, 0);
		cam.near = 1;
		cam.far = 1600;
		cam.update();

		ctrl = new OrthoCamController(cam);
		Gdx.input.setInputProcessor(ctrl);

		batch = new SpriteBatch();

		app.gui.getStage().addActor(whiteScreen);
		whiteScreen.addAction(new SequenceAction(Actions.alpha(0f, 1.8f)));
	}
	
	@Override
	public void show() {
		cam.update();
	}

	private void update(float delta){
		app.gui.getStage().act(delta);
	}

	public void render(float delta) {
		Gdx.gl.glClearColor( 0.154f, 0.200f, 0.184f, 1f );
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		batch.begin();
		batch.setProjectionMatrix(cam.combined);
		batch.end();

		update(delta);
		app.gui.getStage().draw();
		cam.update();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		cam.position.set(cam.viewportWidth/2, cam.viewportHeight/2, 0);
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
		tile_grass.dispose();
		batch.dispose();
		whiteScreenPixmap.dispose();
	}

}
