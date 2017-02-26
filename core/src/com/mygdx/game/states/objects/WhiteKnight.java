package com.mygdx.game.states.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Bloom on 16.02.2017.
 * об'єкт рицарь
 */

public class WhiteKnight {
    /** коефіцієнт швидкості персонажу по горизонталі*/
    private final int SPEEDX = -50;
    /**Сила удару і по суміснусті рахунок гравця*/
    private static int score;
    /**позиція на карті*/
    private Vector2 position;
    /**швидкість персонажу по x*/
    private Vector2 velocity;
    /**межі вершника для зіткнень зі списом*/
    private Rectangle knightHitBox;
    /** для зіткнень із вершником  кінчик спису*/
    private Rectangle spearHitBox;
    /**анімація*/
    private Animation animation;
    /**текстура з анімацією*/
    private Texture animatedTexture;

    public WhiteKnight(int x, int y){

        animatedTexture = new Texture("horsemana.png");
        animation = new Animation(new TextureRegion(animatedTexture), 3, 0.2f);

        score = 0;
        position = new Vector2();
        velocity = new Vector2(0, 0);

        position.x = x;
        position.y = y;

        /**для визначення удару по рицарі*/
        knightHitBox = new Rectangle(position.x, position.y, position.x+220, animatedTexture.getHeight());

        /**чим буде бити*/
        spearHitBox = new Rectangle(position.x, position.y, animatedTexture.getWidth() / 3,animatedTexture.getHeight() );



    }
    /**оновлення ігрового стану*/
    public void update(float deltaTime){

        animation.update(deltaTime);
        if(position.x > 0)
            velocity.add(SPEEDX, 0);
        /**множить на скаляр*/
        velocity.scl(deltaTime);
        position.add(velocity.x, velocity.y);

        if(position.x < 0)
            position.x = 0;

        /**із часом швидкість падає і рицар повертається на початок*/
        velocity.scl(1/deltaTime);
        /**рахунок ведеться в залежності від швидкості*/
        scoreCount();
        /**переміщаємо прямокутники із рицарем*/
        knightHitBox.setPosition(position.x, position.y);
        spearHitBox.setPosition(position.x, position.y);
    }
    /**рух рицаря*/
    public void run(){
        velocity.x = 600;
    }
    /**промальовка текстури*/
    public void draw(SpriteBatch batch){
        /*batch.draw(animation.getFrame(),position.x, position.y, texture.getWidth(),texture.getHeight(), 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);*/
        batch.draw(animation.getFrame(),position.x, position.y);
    }
    /**обрахунок рахунку*/
    private void scoreCount(){
        if(velocity.x > 0)
        score += velocity.x;
        else score -= 100;
        if(score <= 0)
            score = 0;
    }
    /**вивільнення ресурсів*/
    public void dispose(){

        animatedTexture.dispose();
    }
    /**перевірка чи спис потрапив в ціль*/
    public boolean spearHit(Rectangle target){
        if(spearHitBox.overlaps(target))
            return true;
        return false;
    }
    /**для доступу ззовні*/
    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return animatedTexture;
    }

    public Rectangle getKnightHitBox() {
        return knightHitBox;
    }

    public Rectangle getSpearHitBox() {
        return spearHitBox;
    }
    public int getScore(){
        return score;
    }
    /**повернення певного кадру анімації*/
    public TextureRegion getFrame(int frm){
        return animation.getFrame(frm);
    }
}
