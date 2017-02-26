package com.mygdx.game.states.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MainGameClass;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.State;
import com.mygdx.game.states.objects.Stage;

/**
 * Created by Bloom on 26.02.2017.
 * Переможний екран гри
 */

public class WinState extends State {

    /**переможний напис*/
    private Texture winLogo;
    /**Позиція напису*/
    private Vector2 logoPosition;
    /**Сцена*/
    private Stage stage;
    private TextureRegion winnerTexture;
    private Vector2 winnerPosition;

    public WinState(GameStateManager gameStateManager, TextureRegion winnerTexture, float posY) {
        super(gameStateManager);
        /**налаштовуємо камеру*/
        camera.setToOrtho(false, MainGameClass.WIDTH , MainGameClass.HEIGHT );

        winLogo = new Texture("winlogo.png");
        logoPosition = new Vector2( MainGameClass.WIDTH / 2 - winLogo.getWidth()/2, MainGameClass.HEIGHT/2);

        stage = new Stage(camera);

        this.winnerTexture = winnerTexture;
        //winnerPosition = new Vector2(MainGameClass.WIDTH / 2 - winnerTexture.getWidth()/2, posY);
        winnerPosition = new Vector2(MainGameClass.WIDTH / 2 - winnerTexture.getRegionWidth()/2, posY);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        /**встановлюємо матрицю проектування*/
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
            stage.draw(spriteBatch, camera);
            spriteBatch.draw(winnerTexture, winnerPosition.x, winnerPosition.y);
            spriteBatch.draw(winLogo,logoPosition.x, logoPosition.y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

        winLogo.dispose();
        //winnerTexture.dispose();
    }
}
