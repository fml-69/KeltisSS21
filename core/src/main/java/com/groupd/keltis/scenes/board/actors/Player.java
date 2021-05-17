package com.groupd.keltis.scenes.board.actors;


import com.groupd.keltis.Keltis;
import com.groupd.keltis.scenes.board.road_cards.Pointcard;
import com.groupd.keltis.utils.AssetPaths;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    private final Keltis keltis;
    private String name;
    private final String color;

    private int wishingStones;
    private final ArrayList<Pointcard> pointCards = new ArrayList();
    private final ArrayList<Card> handCards = new ArrayList<Card>();

    private HashMap<String, Figure> figures;



    private boolean cheat;
    private boolean turn;

    public Player(Keltis keltis, String name , String color) {
        this.keltis = keltis;
        this.color = color;

        this.name = name;

        this.wishingStones = 0;

        this.turn = false;
        this.cheat = false;
        initializePlayers();
    }

    public void initializePlayers(){
        figures = new HashMap<String, Figure>();
        switch (this.color){
            case "blue":
                figures.put(this.color + "1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "1",1));
                figures.put(this.color + "2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "2",2));
                figures.put(this.color + "3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "3",3));
                figures.put(this.color + "4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "4",4));
                figures.put(this.color + "5", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),this.color + "5",5));
                break;
            case "red":
                figures.put(this.color + "1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "1",1));
                figures.put(this.color + "2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "2",2));
                figures.put(this.color + "3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "3",3));
                figures.put(this.color + "4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "4",4));
                figures.put(this.color + "5", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),this.color + "5",5));
                break;
            case "green":
                figures.put(this.color + "1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "1",1));
                figures.put(this.color + "2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "2",2));
                figures.put(this.color + "3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "3",3));
                figures.put(this.color + "4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "4",4));
                figures.put(this.color + "5", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),this.color + "5",5));
                break;
            case "yellow":
                figures.put(this.color + "1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "1",1));
                figures.put(this.color + "2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "2",2));
                figures.put(this.color + "3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "3",3));
                figures.put(this.color + "4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "4",4));
                figures.put(this.color + "5", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),this.color + "5",5));
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

    public int getOverallScore(){
        int score = 0;
        score += getScoreFigureFieldPosition();
        score += getScoreWishingStones();
        score += getScorePointCards();
        return score;
    }
    private int getScoreFigureFieldPosition(){
        int score = 0;
        for(Figure figure : figures.values()){
            score += figure.getScore();
        }
        return score;
    }

    private int getScorePointCards(){
        int score = 0;
        for(Pointcard pointcard:pointCards){
            score += pointcard.getPoints();
        }
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
    public void addWishStone(){
        this.wishingStones += 1;
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

    public ArrayList getHandCards() {
        return handCards;
    }




    public ArrayList<Pointcard> getPointCards(){
        return pointCards;
    }

    public boolean getTurn(){
        return turn;
    }
    public void setTurn(boolean turn){
        this.turn = turn;
    }
}
