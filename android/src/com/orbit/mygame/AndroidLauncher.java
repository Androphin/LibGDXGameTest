package com.orbit.mygame;

import android.content.Context;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.hideStatusBar = true;
		config.useImmersiveMode = true;

		Context appContext = this.getContext();
		initialize(new MyGame( new AndroidDeviceData(appContext), new AndroidTestService() ), config);
	}
}
