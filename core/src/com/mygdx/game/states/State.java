package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Bloom on 14.02.2017.
 * ігрові стани
 */

public abstract class State {
    /** видима частина гри*/
    protected OrthographicCamera camera; // protected можна отримати доступ із похідного класу
    /**положення курсору на екрані*/
    protected Vector3 mouse;
    /**менеджер станів гри для керування вікнами*/
    protected GameStateManager gameStateManager;

    public State (GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        this.camera = new OrthographicCamera();
        this.mouse = new Vector3();
    }
    /**
     * оброюник користувацького вводу
     * */
    public abstract void handleInput();
    /**
     * оновлення стану через певні проміжки часу
     * */
    public abstract void update(float deltaTime);
    /**
     * промальвка стану
     * */
    public abstract void render(SpriteBatch spriteBatch);
    /**
     * вивільненн ресурсів
     * */
    public abstract void dispose();

}
