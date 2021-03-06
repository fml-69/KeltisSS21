package com.groupd.keltis.management;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.network.NetworkClient;
import com.groupd.keltis.network.events.CheatEvent;
import com.groupd.keltis.network.events.RoadcardsRemoveSyncEvent;
import com.groupd.keltis.network.events.RoadcardsSyncEvent;
import com.groupd.keltis.network.events.TurnEvent;
import com.groupd.keltis.scenes.board.Board;
import com.groupd.keltis.scenes.board.actors.Card;
import com.groupd.keltis.scenes.board.actors.CardDisplay;
import com.groupd.keltis.scenes.board.actors.Figure;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.scenes.board.road_cards.Pointcard;
import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.RoadcardsList;
import com.groupd.keltis.scenes.board.road_cards.Shamrock;
import com.groupd.keltis.scenes.board.road_cards.Wishstone;
import com.groupd.keltis.utils.ColorEnumsToString;
import com.groupd.keltis.utils.ColorPile;
import com.groupd.keltis.utils.JsonConverter;
import com.groupd.keltis.utils.RoadcardsToJson;


import java.util.ArrayList;

public class GameLogic {

    private String playerNick;

    private Board board;

    private ArrayList<Player> playerArrayList = new ArrayList<>();
    private ArrayList<Roadcards> roadCardsList;
    private ArrayList<Card> drawPile;

    private final ArrayList<Card> discardPile;
    private final ArrayList<Card> yellowDiscardPile;
    private final ArrayList<Card> blueDiscardPile;
    private final ArrayList<Card> redDiscardPile;
    private final ArrayList<Card> greenDiscardPile;
    private final ArrayList<Card> purpleDiscardPile;
    private boolean allowDraw = false;
    private boolean allowPlay = true;

    public GameLogic() {
        this.drawPile = new ArrayList<>();

        this.discardPile = new ArrayList<Card>();
        this.yellowDiscardPile = new ArrayList<Card>();
        this.blueDiscardPile = new ArrayList<Card>();
        this.redDiscardPile = new ArrayList<Card>();
        this.greenDiscardPile = new ArrayList<Card>();
        this.purpleDiscardPile = new ArrayList<Card>();
    }

    /**-------------------------------Turn Methods & SendTurn------------------------------------**/

    //Main Method to play. Call to set everything in motion
    public void sendTurnEvent(Player player, Card card, ColorPile colorPile) {
        if (player.getTurn()) {
            NetworkClient client = NetworkClient.INSTANCE;
            TurnEvent turnEvent = new TurnEvent(JsonConverter.convertToJson(new PlayerMove(player.getNick(),card.getName(),ColorEnumsToString.getPileColor(colorPile))));
            player.getHandCards().remove(card);
            client.sendEvent(turnEvent);
            allowDraw = true;
            Gdx.app.log("NETWORK", "TURN SENT");
        }
    }

    public void playCard(Player player, Card card, ColorPile colorPile) {
        addCardToPile(player, card, colorPile);
        addCardToPlayer(player, card);
        move(player, colorPile);
        if(player != getPlayer(playerNick)){
            drawPile.remove(drawPile.size() - 1);
        }
    }

    /**-------------------------------Set Turn of Next Player------------------------------------**/

    public void setTurnPlayer(Player player) {
        allowPlay = true;
        switch (playerArrayList.size()) {
            case 1:
                break;
            case 2:
                if (playerArrayList.indexOf(player) == 0) playerArrayList.get(1).setTurn(true);
                else playerArrayList.get(0).setTurn(true);
                player.setCheat(false);
                sendCheat(player);
                break;
            case 3:
                if (playerArrayList.indexOf(player) == 0) playerArrayList.get(1).setTurn(true);
                else if (playerArrayList.indexOf(player) == 1) playerArrayList.get(2).setTurn(true);
                else playerArrayList.get(0).setTurn(true);
                player.setCheat(false);
                sendCheat(player);
                break;
            case 4:
                if (playerArrayList.indexOf(player) == 0) playerArrayList.get(1).setTurn(true);
                else if (playerArrayList.indexOf(player) == 1) playerArrayList.get(2).setTurn(true);
                else if (playerArrayList.indexOf(player) == 2) playerArrayList.get(3).setTurn(true);
                else playerArrayList.get(0).setTurn(true);
                player.setCheat(false);
                sendCheat(player);
                break;
            default:
                throw new IllegalArgumentException("Number of Players isn't allowed");
        }
        player.setTurn(false);
    }

    /**-------------------------------Draw Card from DrawPile------------------------------------**/

    public void drawCard(Player player) {
        if(allowDraw) {
            for(CardDisplay cardDisplay: Board.getHandCardDisplayList()){
                if(cardDisplay.isEmpty()){
                    cardDisplay.setCard(drawPile.remove(drawPile.size() - 1));
                }
            }
            allowDraw = false;
        }
    }

    /**-----------------------------Add Card to Pile & Player------------------------------------**/

    private void addCardToPile(Player player, Card card, ColorPile colorPile) {
        switch (colorPile) {
            case RED:
                redDiscardPile.add(card);
                if (checkCheatNumber(redDiscardPile, card.getNumber())) {
                    player.setCheat(true);
                    sendCheat(player);
                }
                break;
            case BLUE:
                blueDiscardPile.add(card);
                if (checkCheatNumber(blueDiscardPile, card.getNumber())) {
                    player.setCheat(true);
                    sendCheat(player);
                }
                break;
            case GREEN:
                greenDiscardPile.add(card);
                if (checkCheatNumber(greenDiscardPile, card.getNumber())) {
                    player.setCheat(true);
                    sendCheat(player);
                }
                break;
            case YELLOW:
                yellowDiscardPile.add(card);
                if (checkCheatNumber(yellowDiscardPile, card.getNumber())) {
                    player.setCheat(true);
                    sendCheat(player);
                }
                break;
            case PURPLE:
                purpleDiscardPile.add(card);
                if (checkCheatNumber(purpleDiscardPile, card.getNumber())) {
                    player.setCheat(true);
                    sendCheat(player);
                }
                break;
            case DISCARD:
                discardPile.add(card);
            default:
                throw new IllegalArgumentException("No Cardpile with this Color");
        }
    }

    private void addCardToPlayer(Player player, Card card){
        switch (card.getCardColor()){
            case "green":
                player.addCardGreen(card);
                break;
            case "yellow":
                player.addCardYellow(card);
                break;
            case "red":
                player.addCardRed(card);
                break;
            case "blue":
                player.addCardBlue(card);
                break;
            case "purple":
                player.addCardPurple(card);
                break;
        }
    }

    /**----------------------------------------Move----------------------------------------------**/

    public void move(Player player, ColorPile colorPile) {
        String color = ColorEnumsToString.getPlayerColor(player.getColor());
        switch (colorPile) {
            case RED:
                Figure figure = player.getFigures().get(color + 3);
                moveFigure(player, figure);
                break;
            case BLUE:
                figure = player.getFigures().get(color + 4);
                moveFigure(player, figure);
                break;
            case YELLOW:
                figure = player.getFigures().get(color + 2);
                moveFigure(player, figure);
                break;
            case PURPLE:
                figure = player.getFigures().get(color+ 5);
                moveFigure(player, figure);
                break;
            case GREEN:
                figure = player.getFigures().get(color + 1);
                moveFigure(player, figure);
                break;
            default:
        }
    }

    private void moveFigure(Player player, Figure figure){
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

    /**-------------------------Check Cards on Field & sync roadcards-----------------------------*/

    public boolean checkIfCardIsOnField(Player player, Figure figure) {
        for (Roadcards roadcards : roadCardsList) {
            Gdx.app.log("checkIfCardIsOnField: ", "get checkIfCardIsOnField");
            if (figure.getBranch() == roadcards.getPosition().getBranch() &&
                    figure.getCurrentFieldPosition() == roadcards.getPosition().getField()) {

                checkCard(player, roadcards);
                if(roadcards instanceof Wishstone){
                    roadCardsList.remove(roadcards);
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkCard(Player player,Roadcards roadcards) {
        NetworkClient client = NetworkClient.INSTANCE;
        if (roadcards instanceof Wishstone) {
            Gdx.app.log("Wishstone: ", "get WishStone");
            player.addWishStone();
            roadcards.addAction(Actions.removeActor());
            RoadcardsRemoveSyncEvent roadcardsRemoveSyncEvent = new RoadcardsRemoveSyncEvent(JsonConverter.convertToJson(roadcards.getName()));
            client.sendEvent(roadcardsRemoveSyncEvent);
        } else if (roadcards instanceof Shamrock) {
            Gdx.app.log("Shamrock: ", "get Shamrock");
            if(getCurrentPlayer().getNick().equals(playerNick)){
                board.createBranchDialog(player);
            }
            return true;
        } else if (roadcards instanceof Pointcard) {
            Gdx.app.log("PointCard: ", "get PointCard");
            player.getPointCards().add((Pointcard) roadcards);
        }
        return false;
    }

    /**------------------------------Check ending Condition--------------------------------------**/

    public boolean verifyEndingCondition() {
        return checkDrawPileEmpty() || checkFigureEndingCondition();
    }

    //Check if drawPile is Empty
    private boolean checkDrawPileEmpty() {
        return drawPile.isEmpty();
    }

    //Check if five Figures of one Player are over field 7
    private boolean checkFigureEndingCondition() {
        int count = 0;
        for (Player player : playerArrayList) {
            count += player.verifyEndCondition();
        }
        return count >= 5;
    }
    /**-----------------------Create unique roadcards for all Player-----------------------------**/
    public void createRoadcards(Keltis keltis){
        NetworkClient client = NetworkClient.INSTANCE;
        for(Player player:playerArrayList){
            if(player.getNick().equals(playerNick)&&player.isHost()){
                RoadcardsList roadcardsList = new RoadcardsList();
                roadcardsList.assignRoadcards(keltis);
                board.setRoadcardsList(roadcardsList);
                roadCardsList = roadcardsList.getRoadcardsArrayList();

                ArrayList<RoadcardsStatus> roadcardsStatusArrayList = new ArrayList<>();
                for(Roadcards roadcards:roadCardsList){
                    roadcardsStatusArrayList.add(new RoadcardsStatus(roadcards.getName(),roadcards.getPosition().getName()));
                }
                RoadcardsSyncEvent roadcardsSyncEvent = new RoadcardsSyncEvent(RoadcardsToJson.convertToJson(roadcardsStatusArrayList));
                client.sendEvent(roadcardsSyncEvent);
            }
        }
    }
    /**-------------------------Get the Score of a certain Player--------------------------------**/

    public int getScoreOfPlayer(Player player) {
        return player.getOverallScore();
    }

    /**
     *      Check Cheat Condition
     */

    /**-----------------------------------Cheat Methods------------------------------------------**/

    //Check if the number is correct or if it was cheated
    public boolean checkCheatNumber(ArrayList<Card> pile, int numberCard) {
        boolean greater = false;
        if (pile.size() <= 2) {
            return false;
        }
        if(pile.get(0).getNumber() > pile.get(1).getNumber()){
            if(numberCard > pile.get(pile.size()-2).getNumber()){
                return true;
            }
        }
        else{
            if(numberCard < pile.get(pile.size()-2).getNumber()){
                return true;
            }
        }

        /*
        for (int i = 0; i < pile.size() - 1; i++) {
            // greater = pile.get(i).getNumber() < pile.get(i + 1).getNumber();
            greater |= pile.get(i).getNumber() > pile.get(i + 1).getNumber();
        }*/
        // if (greater && numberCard > pile.get(pile.size() - 1).getNumber()) {
        //     return true;
        //} else return !greater && numberCard < pile.get(pile.size() - 1).getNumber();
        return greater;
    }

    public boolean checkCheat(ArrayList<Player> player) {
        for (Player p : player) {
            if (p.getCheat()) {
                if (!p.isHasAccused()) {
                    return true;
                }
            }
        }
        return false;
    }

    //Send CheatEvent to Server
    public void sendCheat(Player player){
        CheatEvent cheatEvent = new CheatEvent();
        cheatEvent.setCheat(player.getCheat());
        cheatEvent.setNick(NetworkClient.INSTANCE.getNickName());
        NetworkClient.INSTANCE.sendEvent(cheatEvent);
    }

    /**-------------------------------Get own player name----------------------------------------**/

    public Player getPlayer(String playerNick){
        for(Player player:playerArrayList){
            if(player.getNick().equals(playerNick)){
                return player;
            }
        }
        return null;
    }

    /**------------------Get name of the player, which is currently playing----------------------**/

    public Player getCurrentPlayer(){
        Player player = null;
        for (Player x: playerArrayList) {
            if(x.getTurn()){
                player = x;
            }
        }
        return player;
    }

    /**-------------------------------DrawPile getter & setter-----------------------------------**/

    public ArrayList<Card> getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(ArrayList<Card> drawPile) {
        this.drawPile = drawPile;
    }

    /**------------------------------DiscardPile getters-----------------------------------------**/

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

    /**------------------------------roadCardsList Setter-----------------------------------**/

    public void setRoadCardsList(ArrayList<Roadcards> roadCardsList) {
        this.roadCardsList = roadCardsList;
    }

    /**---------------------------------Board Getter & Setter---------------------------------------------**/

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    /**------------------------------PlayerNick Getter & Setter----------------------------------**/

    public void setPlayerNick(String playerNick) {
        this.playerNick = playerNick;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    /**--------------------------PlayerArrayList Getter & Setter---------------------------------**/

    public void setPlayerArrayList(ArrayList<Player> playerArrayList) {
        this.playerArrayList = playerArrayList;
    }

    public ArrayList<Player> getPlayerArrayList() {
        return playerArrayList;
    }

    /**------------------------------allowPlay Getter & Setter-----------------------------------**/

    public boolean isAllowPlay() {
        return allowPlay;
    }

    public void setAllowPlay(boolean allowPlay) {
        this.allowPlay = allowPlay;
    }

    /**------------------------------allowDraw Getter & Setter-----------------------------------**/

    public boolean isAllowDraw() {
        return allowDraw;
    }

    public void setAllowDraw(boolean allowPlay) {
        this.allowDraw = allowDraw;
    }

    /**----------------------------------Cheat-Score-Update--------------------------------------**/

    public void cheatScoreUpdate(String playerNick, int score){
        for(Player player:playerArrayList){
            if(player.getNick().equals(playerNick)){
                player.addScoreCheat(score);
            }
        }

    }

}