package com.groupd.keltis.scenes.board.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Player extends Actor {

    private Sprite sprite;
    private boolean moveUp = false;
    private float lastPosY;
    private String name;
    private int currentFieldPosition;

    public Player(Texture texture, final String name){
        sprite = new Sprite(texture);
        lastPosY = sprite.getY();
        this.name = name;
        currentFieldPosition = 0;

        spritePos(sprite.getX(), sprite.getY());

        setTouchable(Touchable.enabled);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
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
        if(moveUp && sprite.getY()+5<lastPosY+93 && currentFieldPosition<10){
            sprite.setY(sprite.getY() + 5);
        } else if(moveUp){
            currentFieldPosition++;
            lastPosY = sprite.getY();
            moveUp = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public void moveUp(){
        moveUp = true;
    }
    public String getName(){
        return this.name;
    }
}
