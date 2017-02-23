package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.screens.MainMenuState;

/**
 * головний клас який запускає всі інші
 * вхідна точка в програму
 * */
public class MainGameClass extends Game {

	/** ширина ігрової області*/
	public static final int WIDTH = 1000;
	/** висота ігрової області*/
	public static final int HEIGHT = 600;

	/** надає текстуру та координати для малювання фігур*/
	SpriteBatch batch;
	public static GameStateManager gameStateManager;

	
	@Override
	public void create () {
		batch = new SpriteBatch();

		gameStateManager = new GameStateManager();
		gameStateManager.push(new MainMenuState(gameStateManager)); // встановлюємо перший екран

		Gdx.gl.glClearColor(1, 0, 0, 1);
	}

	@Override
	public void render () {
		/**пов'язано із наслідуванням від Game*/
		super.render();
		/**очищає буфер*/
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/**оновлюємо перший в стан в стеці*/
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		/**малюємо перший стан в стеці*/
		gameStateManager.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();

		/**для виводу в консоль*/
		System.out.println("MainGameClass disposed");
	}
}
