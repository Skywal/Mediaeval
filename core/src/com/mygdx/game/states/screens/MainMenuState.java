package com.mygdx.game.states.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MainGameClass;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.State;

/**
 * Created by Bloom on 14.02.2017.
 * екран головного меню
 */

public class MainMenuState extends State {

    /**фон*/
    private Texture background;
    /**кнопка старту*/
    private Texture startButton;
    /**кнопка опцій*/
    private Texture optionsButton;
    /**координати дотику*/
    private Vector3 touchPos;
    /**координати кнопки старту*/
    private Vector3 startButtonPos;
    /**координати кнопки опції*/
    private Vector3 optionsButtonPos;

    public MainMenuState(GameStateManager gameStateManager) {
        super(gameStateManager);

        background = new Texture("background.png");
        startButton = new Texture("start.png");
        optionsButton = new Texture("options.png");

        /** встановлюємо видове вікно та вирівнюємо по центру*/
        camera.setToOrtho(false, MainGameClass.WIDTH, MainGameClass.HEIGHT);

        touchPos = new Vector3();
        startButtonPos = new Vector3();
        optionsButtonPos = new Vector3();

        /**задаємо розміщення кнопкам на екрані*/
        startButtonPos.x = (MainGameClass.WIDTH / 2 - startButton.getWidth() / 2);
        startButtonPos.y = (MainGameClass.HEIGHT / 2);
        startButtonPos.z = 0;

        optionsButtonPos.x = (MainGameClass.WIDTH / 2 - optionsButton.getWidth() / 2);
        optionsButtonPos.y = (MainGameClass.HEIGHT / 2 - optionsButton.getHeight());
        optionsButtonPos.z = 0;
    }

    @Override
    public void handleInput() {
        /**Визначаємо координати дотику*/
        if(Gdx.input.isTouched())
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        /**перетворюємо координати дотику в координати світу (відлік від лівого нижнього кута)*/
            camera.unproject(touchPos);
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        /**визначаємо на яку кнопку було натиснуто*/
        if(isTouched(startButtonPos, startButton, touchPos)) {
            /**розпочинаємо гру*/
            MainGameClass.gameStateManager.push(new PlayState(gameStateManager));
        }
        if(isTouched(optionsButtonPos, optionsButton, touchPos)) {
            /**заходимо в меню опцій*/
            MainGameClass.gameStateManager.push(new OptionsState(gameStateManager));
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        /**розпочинаємо серію малювання*/
        spriteBatch.begin();

            spriteBatch.draw(background, 0, 0, background.getWidth(), background.getHeight());
            spriteBatch.draw(startButton, startButtonPos.x, startButtonPos.y);
            spriteBatch.draw(optionsButton, optionsButtonPos.x, optionsButtonPos.y);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        startButton.dispose();
        optionsButton.dispose();

        /**для виводу в консоль*/
        System.out.println("MainMenuState class disposed");
    }

    /**
     * для визначення чи був дотик до певної текстури
     * для кнопок
     * */
    public static boolean isTouched(Vector3 textureCoord, Texture texture, Vector3 touchPos){
        if(Gdx.input.justTouched()) {
            if (touchPos.x >= textureCoord.x && touchPos.x <= (textureCoord.x + texture.getWidth())
                    && touchPos.y >= textureCoord.y && touchPos.y <= (textureCoord.y + texture.getHeight())) {
                return true;
            }
        }
        return false;
    }
}
