package com.groupd.keltis.scenes.board.actors;


import com.groupd.keltis.Keltis;
import com.groupd.keltis.utils.AssetPaths;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    private final Keltis keltis;
    private String name;
    private String color;

    private int wishingStones;

    private HashMap<String, Figure> figures;
    private ArrayList handCards;
    private ArrayList discardPile;

    private boolean cheat;

    public Player(Keltis keltis){
        this.wishingStones = 0;
        this.handCards = new ArrayList();
        this.discardPile = new ArrayList();
        this.cheat = false;
        this.keltis = keltis;
    }

    public void initializePlayers(){
        figures = new HashMap<String, Figure>();
        switch (this.color){
            case "blue":
                figures.put(this.color + "Big", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "Big",2));
                figures.put(this.color + "Small1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "Small1",1));
                figures.put(this.color + "Small2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "Small2",1));
                figures.put(this.color + "Small3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "Small3",1));
                figures.put(this.color + "Small4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "Small4",1));
                break;
            case "red":
                figures.put(this.color + "Big", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "Big",2));
                figures.put(this.color + "Small1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "Small1",1));
                figures.put(this.color + "Small2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "Small2",1));
                figures.put(this.color + "Small3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "Small3",1));
                figures.put(this.color + "Small4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "Small4",1));
                break;
            case "green":
                figures.put(this.color + "Big", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "Big",2));
                figures.put(this.color + "Small1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "Small1",1));
                figures.put(this.color + "Small2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "Small2",1));
                figures.put(this.color + "Small3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "Small3",1));
                figures.put(this.color + "Small4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "Small4",1));
                break;
            case "yellow":
                figures.put(this.color + "Big", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "Big",2));
                figures.put(this.color + "Small1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "Small1",1));
                figures.put(this.color + "Small2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "Small2",1));
                figures.put(this.color + "Small3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "Small3",1));
                figures.put(this.color + "Small4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "Small4",1));
                break;
        }
    }

    public void setColor(String color){
        switch(color){
            case "red": this.color = "red"; break;
            case "blue": this.color = "blue"; break;
            case "yellow": this.color = "yellow"; break;
            case "green": this.color = "green"; break;
            default: throw new InvalidParameterException();
        }
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public HashMap<String, Figure> getFigures() {
        return figures;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getScore(){
        int score = 0;
        for(Figure figure : figures.values()){
            score += figure.getScore();
        }
        score += getScoreWishingStones();
        return score;
    }

    private int getScoreWishingStones() {
        int score = 0;
        switch (this.wishingStones){
            case 0: score = -4; break;
            case 1: score = -3; break;
            case 2: score = 2; break;
            case 3: score = 3; break;
            case 4: score = 6; break;
            default: if(this.wishingStones >= 5) score = 10; break;
        }
        return score;
    }

    public boolean verifyEndCondition(){
        int count = 0;
        for(Figure figure: figures.values()){
            if(figure.getCurrentFieldPosition() >= 7){
                count++;
            }
        }
        return count == 5;
    }

    public void toggleCheat(){
        cheat = !cheat;
    }

    public boolean getCheat(){
        return cheat;
=======
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

    public int getCurrentFieldPosition() {
        return currentFieldPosition;
    }
}
