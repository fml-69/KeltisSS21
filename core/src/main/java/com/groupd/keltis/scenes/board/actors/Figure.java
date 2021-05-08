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
    private int scoreFactor;
    private int count = 0;

    public Figure(Texture texture, final String name){
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        sprite = new Sprite(texture);
        lastPosY = sprite.getY();
        this.name = name;
        this.currentFieldPosition = 0;
        this.score = 0;
        spritePos(sprite.getX(), sprite.getY());

        setTouchable(Touchable.enabled);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                moveUp();
                Gdx.app.log("Touch down asset with name ", name);
                return true;
            }
        });
    }

    public Figure(Texture texture, final String name, int scoreFactor){
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        sprite = new Sprite(texture);
        lastPosY = 124;
        this.name = name;
        this.currentFieldPosition = 0;
        this.score = 0;
        this.scoreFactor = scoreFactor;
        spritePos(sprite.getX(), sprite.getY());

        setTouchable(Touchable.enabled);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                moveUp();
                Gdx.app.log("Touch down asset with name ", name);
                return true;
            }
        });
    }

    public void spritePos(float x, float y){
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(count < 2){
            Gdx.app.log("spriteY ",  String.valueOf(sprite.getY()));
            Gdx.app.log("lastPosY ",  String.valueOf(lastPosY+93));
            Gdx.app.log("currentfield ",  String.valueOf(currentFieldPosition));
            count++;
        }
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
            case 1: score = -4; break;
            case 2: score = -3; break;
            case 3: score = -2; break;
            case 4: score = 1; break;
            case 5: score = 2; break;
            case 6: score = 3; break;
            case 7: score = 6; break;
            case 8: score = 7; break;
            case 9: score = 10; break;
            default: score = 0; break;
        }
        this.score *= scoreFactor;
    }

    public int getScore(){
        return score;
    }


    public int getCurrentFieldPosition() {
        return currentFieldPosition;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public void moveUp(){
        moveUp = true;
    }
    public boolean getMoveUp(){
        return moveUp;
    }

    public String getName(){
        return this.name;
    }
}