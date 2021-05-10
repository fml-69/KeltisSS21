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

    public Player(Keltis keltis, String color) {
        this.wishingStones = 0;
        this.handCards = new ArrayList();
        this.discardPile = new ArrayList();
        this.cheat = false;
        this.keltis = keltis;
        this.color = color;
        initializePlayers();
    }

    public void initializePlayers(){
        figures = new HashMap<String, Figure>();
        switch (this.color){
            case "blue":
                figures.put(this.color + "1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "1"));
                figures.put(this.color + "2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "2"));
                figures.put(this.color + "3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "3"));
                figures.put(this.color + "4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "4"));
                figures.put(this.color + "5", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "5"));
                break;
            case "red":
                figures.put(this.color + "1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "1"));
                figures.put(this.color + "2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "2"));
                figures.put(this.color + "3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "3"));
                figures.put(this.color + "4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "4"));
                figures.put(this.color + "5", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "5"));
                break;
            case "green":
                figures.put(this.color + "1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "1"));
                figures.put(this.color + "2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "2"));
                figures.put(this.color + "3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "3"));
                figures.put(this.color + "4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "4"));
                figures.put(this.color + "5", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "5"));
                break;
            case "yellow":
                figures.put(this.color + "1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "1"));
                figures.put(this.color + "2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "2"));
                figures.put(this.color + "3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "3"));
                figures.put(this.color + "4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "4"));
                figures.put(this.color + "5", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "5"));
                break;
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

    public boolean getCheat() {
        return cheat;
    }
}
