package com.orbit.mygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.orbit.mygame.MyGame;
import com.orbit.mygame.GUI;
import com.orbit.mygame.ui.RegisterWindow;
import com.orbit.mygame.ui.SettingsWindow;
import com.orbit.mygame.ui.WelcomeWindow;

public class MainScreen implements Screen {

	private final MyGame app;
	private final GUI gui;

    private TextButton btnRegister;
	private TextButton btnPlay;
	private TextButton btnSettings;
	private TextButton btnRanking;
	private TextButton btnHelp;
	private TextButton btnCredits;
    private TextButton btnProfile;
    private TextButton btnService;

	PerspectiveCamera pcam;
	ModelBatch modelBatch;
	Model modelSphere;
	Model modelPigments;
	ModelInstance sphereInstance;
	ModelInstance pigmentsInstance;
	Environment environment;

	boolean showRegisterWindow = false;
	RegisterWindow registerWindow;
	WelcomeWindow welcomeWindow;

	Pixmap blackScreenPixmap;
	Image blackScreen;

	boolean zoomIn = false;
	SpriteBatch batch;
	Texture tex;
	float texY;

	public MainScreen(final MyGame game) {
		this.app = game;
		this.gui = game.gui;

		blackScreenPixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
		blackScreenPixmap.setColor(Color.BLACK);
		blackScreenPixmap.fill();
		blackScreen = new Image( new Texture(blackScreenPixmap) );

		modelBatch = new ModelBatch();
		pcam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		pcam.position.set(5f,3f,5f);
		pcam.lookAt(0,0,0);
		pcam.position.y = 10f;
		pcam.near = 1f;
		pcam.far = 300f;
		pcam.update();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.1f, 0.1f, 0.1f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		ModelBuilder modelBuilder = new ModelBuilder();
		Material materialEarth = new Material( TextureAttribute.createDiffuse(new Texture(Gdx.files.internal("sphere_color.png"))) );
		materialEarth.set(TextureAttribute.createBump( new Texture(Gdx.files.internal("sphere_bump.png")) ));
		modelSphere = modelBuilder.createSphere(5f, 5f, 5f, 20, 20,
				materialEarth, VertexAttributes.Usage.Position|VertexAttributes.Usage.TextureCoordinates);
		sphereInstance = new ModelInstance(modelSphere);
		Texture tex = new Texture( Gdx.files.internal("sphere_pigments.png"), Pixmap.Format.RGBA8888, false);
		Material materialClouds = new Material(TextureAttribute.createDiffuse(tex));
		materialClouds.set(TextureAttribute.createBump(tex));
		materialClouds.set(new BlendingAttribute(true, GL20.GL_SRC_COLOR, GL20.GL_ONE_MINUS_SRC_COLOR, 1.0f));
		modelPigments = modelBuilder.createSphere(5.10f, 5.10f, 5.10f, 20, 20,
				materialClouds, VertexAttributes.Usage.Position|VertexAttributes.Usage.TextureCoordinates);
		pigmentsInstance = new ModelInstance(modelPigments);
	}
	
	@Override
	public void show(){

		//create Main Menu Buttons
		btnRegister = new TextButton( app.language.get("menubtnRegister"), gui.getSkin());

        btnPlay = new TextButton( app.language.get("menubtnStart"), gui.getSkin());
        btnPlay.setDisabled(true);
		btnSettings = new TextButton( app.language.get("menubtnSettings"), gui.getSkin());
        btnService = new TextButton( app.language.get("menubtnService"), gui.getSkin());
        btnService.setDisabled(true);
        btnHelp = new TextButton( app.language.get("menubtnHelp"), gui.getSkin());
        btnCredits = new TextButton( app.language.get("menubtnCredits"), gui.getSkin());

        btnProfile = new TextButton( app.language.get("menubtnProfile"), gui.getSkin());
		btnRanking = new TextButton( app.language.get("menubtnRanking"), gui.getSkin());

		//btn listeners
		setupListeners();

        Table widgetsTable = new Table(gui.getSkin());
        widgetsTable.setDebug(true);
        widgetsTable.pad(20f);
        widgetsTable.row().height(120);
        widgetsTable.add("Profil Preview").expandX();
        widgetsTable.add("Ranking Preview").expandX();

        Table mainMenuTable = new Table(gui.getSkin());
        //mainMenuTable.setDebug(true);
        mainMenuTable.pad(20f);
        mainMenuTable.defaults().expandX();
        mainMenuTable.background( new Image( (Texture)app.assets.mngr.get("bar.png") ).getDrawable() );
		//first row height
		mainMenuTable.row().height(50);
		if( app.player.registered() ) {
			mainMenuTable.add(btnSettings);
			mainMenuTable.add(btnService);
			mainMenuTable.add(btnPlay);
			mainMenuTable.add(btnHelp);
			mainMenuTable.add(btnCredits);
		}else{
			mainMenuTable.add(btnRegister);
		}

		Table menuRootTable = new Table(gui.getSkin());
		//menuRootTable.setDebug(true);
		menuRootTable.setFillParent(true);
		menuRootTable.add().expand().top(); //conqueror logo
		menuRootTable.row();
		menuRootTable.add(widgetsTable).expandX().fillX();
		menuRootTable.row();
		menuRootTable.add( mainMenuTable ).expandX().fillX();
		menuRootTable.bottom();
        gui.getStage().addActor(menuRootTable);
        //gui.getLayer(1).addActor(menuRootTable);

        //Settings Window
		//SettingsWindow settingsWindow = new SettingsWindow(gui.getSkin(), this.app.language);
		//size to screensize
		//position outside cam
		//gui.getStage().addActor(settingsWindow);

		//stage.addActor(blackScreen);

		welcomeWindow = new WelcomeWindow(app.actionCtrl, gui.getSkin(), app.language);
		welcomeWindow.setPosition(0, -app.constants.VIRTUAL_SCREEN_HEIGTH );
		gui.getStage().addActor(welcomeWindow);
		registerWindow = new RegisterWindow(app.actionCtrl, gui.getSkin(), app.language);
		registerWindow.setPosition(0, -app.constants.VIRTUAL_SCREEN_HEIGTH );
		gui.getStage().addActor(registerWindow);
		//gui.getLayer(1).addActor(registerWindow);

		//Register Form
		if(showRegisterWindow){
			//fade background to 80% black
			blackScreen.addAction(new SequenceAction(Actions.alpha(0.8f, 0.5f)));
			//fadein form

		}else{
			blackScreen.addAction(new SequenceAction(Actions.alpha(0f, 0.8f)));
		}

		batch = new SpriteBatch();
		tex = new Texture(Gdx.files.internal("title.png"));
		texY = app.gui.getStage().getViewport().getWorldHeight()/2;
	}

	private void update(float delta){
		gui.getStage().act(delta);
	}

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		Gdx.gl.glEnable(GL20.GL_BLEND);

		modelBatch.begin(pcam);
		sphereInstance.transform.rotate(Vector3.Y, delta/2);
		pigmentsInstance.transform.rotate(Vector3.Y, delta*2);
		modelBatch.render(sphereInstance, environment);
		modelBatch.render(pigmentsInstance, environment);
		modelBatch.end();

		texY = MathUtils.lerp(texY, app.gui.getStage().getViewport().getWorldHeight()-tex.getHeight(), 0.05f);
		batch.begin();
		batch.draw( tex, app.gui.getStage().getViewport().getWorldWidth()/2-tex.getWidth()/2, texY );
		batch.end();

		//move in
		pcam.position.y = MathUtils.lerp(pcam.position.y, 3f, 0.05f);
		//move in + animation
		if( zoomIn ) {
			pcam.position.set(pcam.position.x-delta/2, pcam.position.y-delta/4, pcam.position.z-delta/2);
			if( pcam.position.x <= 1f ){
				app.screenCtrl.switchTo(new GameScreen(app));
			}
		}
		pcam.update();

		update(delta);
		gui.getStage().draw();
	}

	@Override
	public void resize(int width, int height) {
        gui.getStage().getViewport().update(width, height);
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		modelBatch.dispose();
		modelSphere.dispose();
		modelPigments.dispose();
		blackScreenPixmap.dispose();
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


	private void setupListeners() {
		btnRegister.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				//fade registerWindow in
				registerWindow.slideIn();
				this.cancel();
			}
		});

		btnPlay.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				zoomIn = true;
				//slide out main menu elements

				this.cancel();
			}
		});
		btnSettings.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

			}
		});
	}
}
