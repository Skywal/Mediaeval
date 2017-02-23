package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Bloom on 14.02.2017.
 * менеджер станів гри (екранів)
 */

public class GameStateManager {
    /**
     * стек ігрових станів, останнім зайшов першим вийшов
     * */
    private Stack<State> states;

    public GameStateManager(){
        this.states = new Stack<State>();
    }

    /**
     * додає стан у вершину стеку
     * */
    public void push(State state){
        this.states.push(state);
    }

    /**
     * видаляє зі стеку останній еклкмент і очищає його ресурси
     * поміщає наступний елемент у вершину стеку
     * */
    public void set(State state){
        this.states.pop();
        this.states.push(state);
    }

    /**
     * оновлює тільки той екран що знаходиться на вершині стеку
     * */
    public void update(float deltaTime){
        this.states.peek().update(deltaTime); // повертає верхній елемен у стеці не видаляючи його
    }

    /**
     * промальвує верхній елемент у стеці
     * */
    public void render(SpriteBatch spriteBatch){
        this.states.peek().render(spriteBatch);
    }
}
