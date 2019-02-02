package com.orbit.mygame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.I18NBundle;
import com.orbit.mygame.screens.IntroScreen;

public class MyGame implements ApplicationListener {

    private static MyGame app;

    public static final int V_WIDTH = 800;
    public static final int V_HEIGHT = 480;

    //public static final int V_WIDTH = 1280;
    //public static final int V_HEIGHT = 720;

    //public static final int V_WIDTH = 1600;
    //public static final int V_HEIGHT = 941;

    //public static final int V_WIDTH = 1920;
    //public static final int V_HEIGHT = 1080;

    private boolean gamePaused;
    private final DeviceData deviceData;
	private final TestService tsi;

    public GameConstants constants;
	public Preferences settings;
    public Assets assets;
    public I18NBundle language;
    public NetworkManager network;
    public GUI gui;
    public Notifications notifications;

    public Player player;

    //Controllers
    public ScreenController screenCtrl;
    public ActionController actionCtrl;

    public MyGame(DeviceData deviceData, TestService tsi){
        this.deviceData = deviceData;
        this.tsi = tsi;
    }

	public void create() {
        this.app = this;
        this.constants = new GameConstants();
        this.notifications = new Notifications();
        this.network = new NetworkManager(app);
        this.player = new Player();
        this.gamePaused = false;
        this.assets = new Assets();
        this.actionCtrl = new ActionController(app);
        this.screenCtrl = new ScreenController(app);
        //Introscreen for loading assets
        this.screenCtrl.switchTo(new IntroScreen(app));
        app.assets.mngr.finishLoading();
        //GUI
        this.gui = new GUI(app);
        //Apply language
        this.language = assets.mngr.get("languages/MyGame", I18NBundle.class);
        //Load Settings
        this.settings = Gdx.app.getPreferences( constants.PREFERENCE_NAME );
        //Setup Screen/Viewport
        //this.screenCtrl.switchTo(new MainScreen(app));

        //check firstrun
        if( app.settings.getBoolean("appFirstrunIsOver") == false ) {
            //set firstrun is over
            //app.settings.putBoolean("appFirstrunIsOver", true);
        }else{

        }
	}

	public DeviceData getDeviceData(){
        return this.deviceData;
    }

    public void resize(int w, int h){
        this.screenCtrl.resize(w,h);
    }

	public void render() {
        this.screenCtrl.render(Gdx.graphics.getDeltaTime());
	}

    public void pause() {
        this.gamePaused = true;
    }

    public void resume() {
        this.gamePaused = false;
        //Reload removed assets from memory
        assets.mngr.update(); // better switch to a loading screen
    }

	public void dispose() {
        //clear screen
        screenCtrl.dispose();
        //clear gui
        gui.dispose();
        //Clear Assets and AssetMngr itself
        assets.mngr.dispose();
        //save changed settings
        app.settings.flush();
	}
}
