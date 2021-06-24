package com.groupd.keltis.scenes.board.road_cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Roadcards extends Actor {
    private Sprite sprite;
    private Position position;
    private String name;
    private int frameDelay = (int) Math.floor(Math.random()*(600));
    private float counter = 1;
    private float angle;
    private boolean countUp = true;
    private boolean startFlare = false;
    private Sprite flare = new Sprite(new Texture("road_cards/flare.png"));

    public Roadcards(String name, Texture texture, Position position){
        this.name = name;
        this.sprite = new Sprite(texture);
        this.position = position;
        spritePos(position.getX(), position.getY());
        setTouchable(Touchable.enabled);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Touch down asset with name ", "roadcard");
                return true;
            }
        });
    }

    public void spritePos(float x, float y){
        sprite.setPosition(x, y);
        flare.setPosition(x-10, y-10);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void act(float delta) { super.act(delta); }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);

        if(startFlare) {
            flare.setRotation(360*angle/100);
            angle++;
            flare.draw(batch,counter/60);

            if (countUp) {
                counter++;
            } else {
                counter--;
            }
            if (counter == 60) {
                countUp = false;
            }
            if (counter == 0) {
                angle=0;
                countUp = true;
                startFlare = false;
                flare.setRotation(0);
            }
        }
        if(frameDelay==600){
            frameDelay = 0;
            startFlare = true;
        } else{
            frameDelay++;
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public String getName() {
        return name;
    }
}
