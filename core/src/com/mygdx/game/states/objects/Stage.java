package com.mygdx.game.states.objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MainGameClass;

/**
 * Created by Bloom on 18.02.2017.
 * cцена
 */

public class Stage {


    private final int GROUND_Y_OFFSET = -100;

    private Texture background;
    private Texture ground;
    private Vector2 groundPosition1;

    public Stage(Camera camera) {
        background = new Texture("background.png");
        ground = new Texture("tile.png");
        groundPosition1 = new Vector2(camera.position.x-50, GROUND_Y_OFFSET);
    }

    public void draw(SpriteBatch batch, Camera camera){
        /**рухаємо фон разом із камерою*/
        batch.draw(background,camera.position.x - camera.viewportWidth/2
                ,0, background.getWidth(), background.getHeight());
        /**промальовуємо землю відразу на всьому проміжку*/
        for(int i =0; i < 100; i++)
            batch.draw(ground, i* ground.getWidth(), groundPosition1.y);
    }

    public void dispose(){
        background.dispose();
        ground.dispose();
    }
}
