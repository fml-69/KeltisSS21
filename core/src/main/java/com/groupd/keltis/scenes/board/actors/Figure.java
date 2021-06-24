package com.groupd.keltis.scenes.board.actors;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Figure extends Actor {

    private final Sprite sprite;
    private boolean moveUp = false;
    private float lastPosY;
    private final String name;
    private int currentFieldPosition;
    private int score;
    private int branch;

    public Figure(Texture texture, final String name, int branch){
        sprite = new Sprite(texture);
        this.name = name;
        this.currentFieldPosition = 0;
        this.score = 0;
        this.branch = branch;
        spritePos(sprite.getX(), sprite.getY());
        setTouchable(Touchable.disabled);
    }

    public void spritePos(float x, float y){
        sprite.setPosition(x, y);
        lastPosY = sprite.getY();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(moveUp && sprite.getY()+5<lastPosY+93 && currentFieldPosition<9){
            sprite.setY(sprite.getY() + 5);
        } else if(moveUp && currentFieldPosition<9){
            currentFieldPosition++;
            lastPosY = sprite.getY();
            moveUp = false;
            calculateScore();
        }
    }

    public void calculateScore(){
        switch(currentFieldPosition){
            case 1: this.score = -4; break;
            case 2: this.score = -3; break;
            case 3: this.score = -2; break;
            case 4: this.score = 1; break;
            case 5: this.score = 2; break;
            case 6: this.score = 3; break;
            case 7: this.score = 6; break;
            case 8: this.score = 7; break;
            case 9: this.score = 10; break;
            default: this.score = 0; break;
        }
    }

    public int getScore(){
        return score;
    }

    public int getCurrentFieldPosition() {
        return currentFieldPosition;
    }

    public void setCurrentFieldPosition(int currentFieldPosition) {
        this.currentFieldPosition = currentFieldPosition;
    }

    public void setLastPosY(float lastPosY) {
        this.lastPosY = lastPosY;
    }

    public float getLastPosY() {
        return lastPosY;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public void moveUp(){ moveUp = true; }

    public boolean getMoveUp(){
        return moveUp;
    }

    public String getName(){
        return this.name;
    }

    public int getBranch() {
        return branch;
    }


    public Sprite getSprite() {
        return sprite;
    }
}