package com.groupd.keltis.scenes.board.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Card{

    private Sprite sprite;
    private String name;
    private String color;
    private int value;
    private Texture texture;

    public Card(Texture texture, final String name, String color, int value){
        this.texture = texture;
        sprite = new Sprite(texture);
        this.name = name;
        this.color = color;
        this.value = value;
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

    public Texture getTexture(){
        return this.texture;
    }
}
