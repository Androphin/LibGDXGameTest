package com.orbit.mygame;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AndroidTestService implements TestService {
	private final Service cs;

	public AndroidTestService(){
		this.cs = new Service() {
			@Override
			public IBinder onBind(Intent intent) {
				return null;
			}
		};
	}
}
