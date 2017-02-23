package com.mygdx.game.states.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Bloom on 17.02.2017.
 * анімація
 */

public class Animation {
    /**кадри анімації*/
    //TextureRegion - прямокутна область текстури
    private Array<TextureRegion> frames;
    /**тривалість відображення одного кадру анімації*/
    private float maxFrameTime;
    /**час відображення поточного кадру анімації*/
    private float currentFrameTime;
    /**кількість кадрів анімації*/
    private int frameCount;
    /**окремий кадр анімації*/
    private int frame;

    public Animation(TextureRegion textureRegion, int frameCount, float animationDuration){
        frames = new Array<TextureRegion>();
        /**ширина кадру анімації*/
        int frameWidth = textureRegion.getRegionWidth() / frameCount;
        for(int i=0; i<frameCount; i++){
            frames.add(new TextureRegion(textureRegion, i * frameWidth, 0, frameWidth, textureRegion.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = animationDuration / frameCount;
        /**початок анімації*/
        frame = 0;
    }
    /**оновлення анімації*/
    public void update(float deltaTime){
        currentFrameTime += deltaTime;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime=0;
        }
        if(frame >= frameCount)
            frame=0;
    }
    /**для отримання поточного кадру анімації*/
    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
