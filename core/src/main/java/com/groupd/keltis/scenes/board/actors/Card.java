package com.groupd.keltis.scenes.board.actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Card extends Actor {

    private Sprite sprite;
    private String name;
    private String color;
    private int value;

    public Card(Texture texture, final String name, int value){
        sprite = new Sprite(texture);
        this.name = name;
        this.value = value;
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
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    public String getName(){
        return this.name;
    }

    public Card(String color, int value) {
        this.color = color;
        this.value = value;
    }

    public String getCardColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumber() {
        return value;
    }

    public void setNumber(int value) {
        this.value = value;
    }

}
