package com.mygdx.game.states.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MainGameClass;

/**
 * Created by Bloom on 16.02.2017.
 * об'єкт червоний рицарь
 */

public class RedKnight {
    /** коефіцієнт швидкості персонажу по горизонталі*/
    public static final int SPEEDX = 50;
    /**Сила удару і по суміснусті рахунок гравця*/
    public static int score;
    /**позиція на карті*/
    private Vector2 position;
    /**швидкість персонажу по x*/
    private Vector2 velocity;
    /**текстура*/
    private Texture texture;
    /**межі вершника для зіткнень зі списом*/
    private Rectangle knightHitBox;
    /** для зіткнень із вершником  кінчик спису*/
    private Rectangle spearHitBox;
    /**анімація*/
    private Animation animation;
    /**текстура з анімацією*/
    private Texture animatedTexture;

    public RedKnight(int x, int y){

        animatedTexture = new Texture("antagonista.png");
        animation = new Animation(new TextureRegion(animatedTexture), 3, 0.2f);

        score = 0;
        velocity = new Vector2(0, 0);
        texture = new Texture("antagonist.png");
        position = new Vector2();
        position.x = x;
        position.y = y;

        /**для визначення удару по рицарі*/ /**для червоного відобразити по вертикалі всі координати*/
        knightHitBox = new Rectangle(position.x + 370, position.y, texture.getWidth(), texture.getHeight());
        //knightHitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        /**чим буде бити*/
        spearHitBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight() );

    }
    /**оновлення ігрового стану*/
    public void update(float deltaTime){

        if((position.x+texture.getWidth()) < MainGameClass.WIDTH)
            velocity.add(SPEEDX, 0);
        /**множить на скаляр*/
        velocity.scl(deltaTime);
        position.add(velocity.x, velocity.y);

        /**тільки якщо гравець грає*/
       /* if((position.x+texture.getWidth()) > MainGameClass.WIDTH)
            position.x = MainGameClass.WIDTH - texture.getWidth();*/

        /**із часом швидкість падає і рицар повертається на початок*/
        velocity.scl(1/deltaTime);
        /**рахунок ведеться в залежності від швидкості*/
        scoreCount();
        /**переміщаємо прямокутники із рицарем*/
        knightHitBox.setPosition(position.x + 350, position.y);
        spearHitBox.setPosition(position.x, position.y);
    }
    /**рух рицаря*/
    public void run(){
        velocity.x = -600;
    }
    /**промальовка текстури*/
    public void draw(SpriteBatch batch){
        batch.draw(texture,position.x, position.y, texture.getWidth(),texture.getHeight(), 0, 0,
                texture.getWidth(), texture.getHeight(), true, false);
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
        texture.dispose();
    }
    /**для доступу ззовні*/
    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
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
