package com.groupd.keltis.management;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.groupd.keltis.scenes.board.Board;
import com.groupd.keltis.scenes.board.actors.Card;
import com.groupd.keltis.scenes.board.actors.Figure;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.scenes.board.road_cards.Pointcard;
import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.Shamrock;
import com.groupd.keltis.scenes.board.road_cards.Wishstone;


import java.util.ArrayList;

public class GameLogic {

    private ArrayList<Player> playerArrayList = new ArrayList<>();
    private ArrayList<Roadcards> roadCardsList;


    private ArrayList<Card> drawPile;

    private final ArrayList<Card> discardPile;
    private final ArrayList<Card> yellowDiscardPile;
    private final ArrayList<Card> blueDiscardPile;
    private final ArrayList<Card> redDiscardPile;
    private final ArrayList<Card> greenDiscardPile;
    private final ArrayList<Card> purpleDiscardPile;

    private int turn = 0;

    private Board board;

    public GameLogic() {
        this.drawPile = new ArrayList<>();

        this.discardPile = new ArrayList<Card>();
        this.yellowDiscardPile = new ArrayList<Card>();
        this.blueDiscardPile = new ArrayList<Card>();
        this.redDiscardPile = new ArrayList<Card>();
        this.greenDiscardPile = new ArrayList<Card>();
        this.purpleDiscardPile = new ArrayList<Card>();
    }
    //Main Method to play
    //Call to set everything in motion
    public void playCard(Player player, Card card, String colorPile) {
        if (player.getTurn() || turn == 0) {
            //player.getHandCards().remove(card);
            addCardToPile(player, card, colorPile);
            move(player, colorPile);
            setTurnPlayer(player);
            //drawCard(player);
            // TODO: 15.05.2021  send Data to other players
        }
    }

    public void setTurnPlayer(Player player) {
        switch (playerArrayList.size()) {
            case 2:
                if (playerArrayList.indexOf(player) == 0) playerArrayList.get(1).setTurn(true);
                else playerArrayList.get(0).setTurn(true);
                break;
            case 3:
                if (playerArrayList.indexOf(player) == 0) playerArrayList.get(1).setTurn(true);
                else if (playerArrayList.indexOf(player) == 1) playerArrayList.get(2).setTurn(true);
                else playerArrayList.get(0).setTurn(true);
                break;
            case 4:
                if (playerArrayList.indexOf(player) == 0) playerArrayList.get(1).setTurn(true);
                else if (playerArrayList.indexOf(player) == 1) playerArrayList.get(2).setTurn(true);
                else if (playerArrayList.indexOf(player) == 2) playerArrayList.get(3).setTurn(true);
                else playerArrayList.get(0).setTurn(true);
                break;
            default:
                throw new IllegalArgumentException("Number of Players isn't allowed");
        }
        turn++;
        player.setTurn(false);
    }

    public void drawCard(Player player) {
        Card card = drawPile.remove(drawPile.size() - 1);
        player.getHandCards().add(card);
    }

    public void addCardToPile(Player player, Card card, String colorPile) {
        switch (colorPile) {
            case "red":
                redDiscardPile.add(card);
                if (checkCheat(redDiscardPile, card, colorPile)) player.toggleCheat();
                break;
            case "blue":
                blueDiscardPile.add(card);
                if (checkCheat(blueDiscardPile, card, colorPile)) player.toggleCheat();
                break;
            case "green":
                greenDiscardPile.add(card);
                if (checkCheat(greenDiscardPile, card, colorPile)) player.toggleCheat();
                break;
            case "yellow":
                yellowDiscardPile.add(card);
                if (checkCheat(yellowDiscardPile, card, colorPile)) player.toggleCheat();
                break;
            case "purple":
                purpleDiscardPile.add(card);
                if (checkCheat(purpleDiscardPile, card, colorPile)) player.toggleCheat();
                break;
            case "discard":
                discardPile.add(card);
            default:
                throw new IllegalArgumentException("No Cardpile with this Color");
        }
    }
    /**
     *      Move and CheckRoadCards
     */
    public void move(Player player, String colorPile) {
        switch (colorPile) {
            case "red":
                Figure figure = player.getFigures().get(player.getColor() + 3);
                moveFigure(player, figure);
                break;
            case "blue":
                figure = player.getFigures().get(player.getColor() + 4);
                moveFigure(player, figure);
                break;
            case "yellow":
                figure = player.getFigures().get(player.getColor() + 2);
                moveFigure(player, figure);
                break;
            case "purple":
                figure = player.getFigures().get(player.getColor() + 5);
                moveFigure(player, figure);
                break;
            case "green":
                figure = player.getFigures().get(player.getColor() + 1);
                moveFigure(player, figure);
                break;
            default:
        }
    }
    public void moveFigure(Player player, Figure figure){
        board.pause();
        figure.moveUp();
        Thread thread = new Thread(){
            private boolean exitThread = false;
            @Override
            public void run() {
                while (!exitThread){
                    waitForMoveFigure();
                }
            }
            public void waitForMoveFigure(){
                if(!figure.getMoveUp()){
                    checkIfCardIsOnField(player, figure);
                    exitThread=true;
                }
            }
        };
        thread.start();
        board.resume();
    }

    public boolean checkIfCardIsOnField(Player player, Figure figure) {
        for (Roadcards roadcards : roadCardsList) {
            Gdx.app.log("checkIfCardIsOnField: ", "get checkIfCardIsOnField");
            if (figure.getBranch() == roadcards.getPosition().getBranch() &&
                    figure.getCurrentFieldPosition() == roadcards.getPosition().getField()) {

                checkCard(player, figure, roadcards);
                if(roadcards instanceof Wishstone){
                    roadCardsList.remove(roadcards);
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkCard(Player player, Figure figure, Roadcards roadcards) {
        if (roadcards instanceof Wishstone) {
            Gdx.app.log("Wishstone: ", "get WishStone");
            player.addWishStone();
            roadcards.addAction(Actions.removeActor());
        } else if (roadcards instanceof Shamrock) {
            Gdx.app.log("Shamrock: ", "get Shamrock");
            board.showDialog(board.getShamrockDialog(),board.stage,3);
            float delay = 2; // seconds
            Timer.schedule(new Timer.Task(){
                @Override
                public void run() {
                    board.getShamrockDialog().hide();
                    moveFigure(player,figure);
                }
            }, delay);
            return true;
        } else if (roadcards instanceof Pointcard) {
            Gdx.app.log("PointCard: ", "get PointCard");
            player.getPointCards().add((Pointcard) roadcards);
        }
        return false;
    }
    /**
     *      Check ending Condition
     */
    public boolean verifyEndingCondition() {
        return checkDrawPileEmpty() || checkFigureEndingCondition();
    }

    public boolean checkDrawPileEmpty() {
        return drawPile.isEmpty();
    }

    public boolean checkFigureEndingCondition() {
        for (Player player : playerArrayList) {
            if (player.verifyEndCondition()) {
                return true;
            }
        }
        return false;
    }
    /**
     *      Set the Player Array
     */
    public boolean checkCheat(ArrayList<Card> pile, Card card, String color) {
        return checkCheatNumber(pile, card.getNumber()) || checkCheatColor(card.getCardColor(), color);
    }

    //Check if it was cheated with the order of the numbers
    public boolean checkCheatNumber(ArrayList<Card> pile, int numberCard) {
        boolean greater = false;
        if (pile.size() <= 2) {
            return false;
        }
        for (int i = 0; i < pile.size() - 1; i++) {
            greater = pile.get(i).getNumber() < pile.get(i + 1).getNumber();
        }
        if (greater && numberCard > pile.get(pile.size() - 1).getNumber()) {
            return true;
        } else return !greater && numberCard < pile.get(pile.size() - 1).getNumber();
    }

    //Check if it was cheated with the color of the cards
    public boolean checkCheatColor(String colorCard, String colorPile) {
        return colorCard.equals(colorPile);
    }
    /**
     *      Set the Player Array
     */
    public void setPlayerArrayList(ArrayList<Player> playerArrayList) {
        this.playerArrayList = playerArrayList;
    }
    /**
     *      Get the Score of a certain Player
     */
    public int getScoreOfPlayer(Player player) {
        return player.getOverallScore();
    }
    /**
     *      DrawPile getter & setter
     */
    public ArrayList<Card> getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(ArrayList<Card> drawPile) {
        this.drawPile = drawPile;
    }

    public void setRoadCardsList(ArrayList<Roadcards> roadCardsList) {
        this.roadCardsList = roadCardsList;
    }
    /**
     *      DiscardPile getters
     */
    public ArrayList<Card> getYellowDiscardPile() {
        return yellowDiscardPile;
    }

    public ArrayList<Card> getBlueDiscardPile() {
        return blueDiscardPile;
    }

    public ArrayList<Card> getRedDiscardPile() {
        return redDiscardPile;
    }

    public ArrayList<Card> getGreenDiscardPile() {
        return greenDiscardPile;
    }

    public ArrayList<Card> getPurpleDiscardPile() {
        return purpleDiscardPile;
    }
    /**
     *      Board setter
     */
    public void setBoard(Board board) {
        this.board = board;
    }
}