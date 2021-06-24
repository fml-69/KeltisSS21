package com.groupd.keltis;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.vSyncEnabled = true;
		config.width = 1920 / 2;
		config.height = 1080 / 2;

		new LwjglApplication(new Keltis(), config);
	}
}
