package com.trexter.solospaceminer.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.trexter.solospaceminer.LibGdxGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//configure the android launch class's config stuff
		config.useCompass = false; // conserve battery we dont need the compass only the accelerometer
		config.useAccelerometer = true; //we need this
		config.useImmersiveMode = true;
		initialize(new LibGdxGame(), config);
	}
}
