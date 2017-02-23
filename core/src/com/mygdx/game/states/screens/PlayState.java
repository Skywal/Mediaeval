package com.mygdx.game.states.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MainGameClass;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.State;
import com.mygdx.game.states.objects.RedKnight;
import com.mygdx.game.states.objects.Stage;
import com.mygdx.game.states.objects.WhiteKnight;

/**
 * Created by Bloom on 16.02.2017.
 * ігровий екран
 * реалізує механіку гри
 */

public class PlayState extends State {

    /**сцена*/
    private Stage stage;
    /**переможний напис*/
    private Texture winLogo;

    /**гравці*/
    private WhiteKnight whiteKnight;
    private RedKnight redKnight;

    /**написи*/
    private BitmapFont bitmapFont;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);

        stage = new Stage(camera);

        camera.setToOrtho(false, MainGameClass.WIDTH , MainGameClass.HEIGHT );

        whiteKnight = new WhiteKnight(5, 65);
        redKnight = new RedKnight(5000, 65);

        winLogo = new Texture("winlogo.png");

        bitmapFont = new BitmapFont();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            whiteKnight.run();
            //redKnight.run();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();

        whiteKnight.update(deltaTime);
        //redKnight.update(deltaTime);

        /**позиціонуємо камеру*/
        setCameraPosition(whiteKnight.getPosition());

        /**оновлюємо камеру*/
        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        /**встановлюємо матрицю проектування*/
        spriteBatch.setProjectionMatrix(camera.combined);
        /**розпочинаємо серію малювання*/
        spriteBatch.begin();
            /**малюємо сцену*/
            stage.draw(spriteBatch,camera);

            redKnight.draw(spriteBatch);
            whiteKnight.draw(spriteBatch);

            if(whiteKnight.spearHit(redKnight.getKnightHitBox()))
                spriteBatch.draw(winLogo, camera.position.x - winLogo.getWidth()/2, MainGameClass.HEIGHT/2);

            bitmapFont.draw(spriteBatch, "SCORE WHITE KNIGHT " + whiteKnight.getSCORE(),
                    camera.position.x - camera.viewportWidth/2 + 10, MainGameClass.HEIGHT -75);

            bitmapFont.draw(spriteBatch, "HitBox " + (whiteKnight.getSpearHitBox().x ),
                    camera.position.x - camera.viewportWidth/2 + 10, MainGameClass.HEIGHT -10);
            bitmapFont.draw(spriteBatch, "RedHitBox " + redKnight.getKnightHitBox().x + " " + redKnight.getKnightHitBox().y,
                    camera.position.x - camera.viewportWidth/2 + 10, MainGameClass.HEIGHT -25);
            bitmapFont.draw(spriteBatch, "Position " + redKnight.getPosition().x ,
                    camera.position.x - camera.viewportWidth/2 + 10, MainGameClass.HEIGHT -50);

        spriteBatch.end();
    }
    /**для того щоб виставляти камеру на різні позиції із можливістю переміщення */
    public void setCameraPosition(Vector2 position){
        camera.position.x = position.x +500;
    }
    @Override
    public void dispose() {
        whiteKnight.dispose();
        redKnight.dispose();
        bitmapFont.dispose();
        stage.dispose();
    }
}
