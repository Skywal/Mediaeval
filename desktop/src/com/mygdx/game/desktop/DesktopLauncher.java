package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MainGameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		/**розширення вікна*/
		config.width = MainGameClass.WIDTH;
		config.height = MainGameClass.HEIGHT;

		new LwjglApplication(new MainGameClass(), config);
	}
}
