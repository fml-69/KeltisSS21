package com.groupd.keltis.scenes.board.actors;


import com.groupd.keltis.Keltis;
import com.groupd.keltis.utils.ColorFigures;
import com.groupd.keltis.scenes.board.road_cards.Pointcard;
import com.groupd.keltis.utils.AssetPaths;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    private final Keltis keltis;
    private String nick;
    private ColorFigures color;

    private int wishingStones;
    private final ArrayList<Pointcard> pointCards = new ArrayList();
    private final ArrayList<Card> handCards = new ArrayList<Card>();

    private HashMap<String, Figure> figures;


    private boolean cheat;
    private boolean turn;
    private boolean hasAccused = false;
    private int scoreCheat=0 ;

    private boolean host;

    public boolean isHasAccused() {
        return hasAccused;
    }

    public void setHasAccused(boolean hasAccused) {
        this.hasAccused = hasAccused;
    }

    public void setCheat(boolean cheat) {
        this.cheat = cheat;
    }

    public Player() {
        keltis = new Keltis();
        //color = "testColor";
        figures = new HashMap<>();
    }

    public Player(Keltis keltis, String nick, ColorFigures color, boolean host) {
        this.keltis = keltis;
        this.color = color;

        this.nick = nick;
        this.host = host;

        this.wishingStones = 0;

        this.turn = false;
        this.cheat = false;
        initializePlayers();
    }

    public void initializePlayers(){
        figures = new HashMap<String, Figure>();
        switch (this.color){
            case BLUE:
                figures.put("blue1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),"blue1",1));
                figures.put("blue2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),"blue2",2));
                figures.put("blue3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),"blue3",3));
                figures.put("blue4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),"blue4",4));
                figures.put("blue5", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),"blue5",5));
                break;
            case RED:
                figures.put("red1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),"red1",1));
                figures.put("red2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),"red2",2));
                figures.put("red3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),"red3",3));
                figures.put("red4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),"red4",4));
                figures.put("red5", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED),"red5",5));
                break;
            case GREEN:
                figures.put("green1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),"green1",1));
                figures.put("green2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),"green2",2));
                figures.put("green3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),"green3",3));
                figures.put("green4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),"green4",4));
                figures.put("green5", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),"green5",5));
                break;
            case YELLOW:
                figures.put("yellow1", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),"yellow1",1));
                figures.put("yellow2", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),"yellow2",2));
                figures.put("yellow3", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),"yellow3",3));
                figures.put("yellow4", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),"yellow4",4));
                figures.put("yellow5", new Figure(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),"yellow5",5));
                break;
        }
    }


    public String getNick() {
        return nick;
    }

    public ColorFigures getColor() {
        return color;
    }

    public HashMap<String, Figure> getFigures() {
        return figures;
    }

    public void setNick(String nick){
        this.nick = nick;
    }

    public int getOverallScore(){
        int score = 0;
        score += getScoreFigureFieldPosition();
        score += getScoreWishingStones();
        score += getScorePointCards();
        score += getScoreCheat();
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

    public int getWishingStones() {
        return wishingStones;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public int getScoreCheat(){
        return scoreCheat;
    }

    public void addScoreCheat(int scoreCheat){
        this.scoreCheat+=scoreCheat;
    }
}
