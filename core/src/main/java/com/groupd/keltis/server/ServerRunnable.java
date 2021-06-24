package com.groupd.keltis.server;

import com.badlogic.gdx.Gdx;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.network.NetworkClientChannel;
import com.groupd.keltis.network.NetworkServer;
import com.groupd.keltis.network.NetworkServerInterface;
import com.groupd.keltis.network.events.CardDisplaySyncEvent;
import com.groupd.keltis.network.events.CheatQueryEvent;
import com.groupd.keltis.network.events.CheatScoreEvent;
import com.groupd.keltis.network.events.JoinEvent;
import com.groupd.keltis.network.events.MoveBecauseOfShamrockEvent;
import com.groupd.keltis.network.events.NextPlayerEvent;
import com.groupd.keltis.network.events.RoadcardsRemoveSyncEvent;
import com.groupd.keltis.network.events.RoadcardsSyncEvent;
import com.groupd.keltis.network.events.StartGameEvent;

import com.groupd.keltis.network.events.StopGameEvent;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.utils.ColorFigures;

import com.groupd.keltis.network.events.TurnEvent;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class ServerRunnable implements Runnable{

    private Keltis keltis;
    protected List<Player> playerList = new ArrayList<>();

    private boolean flag;


    public NetworkServer networkServer;



    public ServerRunnable(int port, CountDownLatch countDownLatch, Keltis keltis){
        this.keltis = keltis;
        networkServer = new NetworkServer(port, countDownLatch, this);
        flag = true;
    }



    //default constructor necessary for cheat tests
    public ServerRunnable(){
      
    }  


    public ServerRunnable(Keltis keltis, NetworkServerInterface networkServer){
        this.keltis = keltis;
        this.networkServer = (NetworkServer) networkServer;
        flag = true;
    }

    @Override
    public void run() {
        do {
            networkServer.receivePackets();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        } while (flag);
    }

    //called when the game should stop for all users
    public void stopGameFlag(){
        this.flag = false;
    }

    public void stopGame(StopGameEvent stopgameevent){
        networkServer.broadCast(stopgameevent);
    }


    // called when a player joins the game
    public void join(String nick){

        for(Player player:playerList){
           networkServer.sendEvent(nick, new JoinEvent(player.getNick(), player.getColor(),player.isHost()));
        }


        // first player added will be automatically host by boolean value of isEmpty()
        Player player = new Player(keltis, nick, playerColor() , playerList.isEmpty());
        networkServer.broadCast(new JoinEvent(nick, player.getColor(),player.isHost()));
        playerList.add(player);

    }

    public ColorFigures playerColor() {
        switch (playerList.size()) {
            case 0:
                return ColorFigures.BLUE;
            case 1:
                return ColorFigures.RED;
            case 2:
                return ColorFigures.GREEN;
            case 3:
                return ColorFigures.YELLOW;
            default:
        }
        return ColorFigures.BLUE;
    }


    // only Host can start the game
    public void onStartGame(StartGameEvent event, String nick){
        Player player = getPlayerNick(nick);
        if(player != null && player.isHost()){
            // disabled for easier development
            //if(playerList.size() >= 2 && playerList.size() <= 4){
                networkServer.broadCast(event);
            //}
        }
    }


    // access list of players
    public List<Player> getPlayerList() {
        return playerList;
    }

    // access player with specific nick
    public Player getPlayerNick(String nick){

        for(Player player:playerList){
            if(player.getNick().equals(nick)){
                return player;
            }
        }

        return null;
    }


    // can access nick of player who made for a turn
    public void onTurn(TurnEvent turnEvent) {
        networkServer.broadCast(turnEvent);
    }

    public void branchStackSync(CardDisplaySyncEvent cardDisplaySyncEvent) {
        networkServer.broadCast(cardDisplaySyncEvent);
    }

    public void nextPlayer(NextPlayerEvent nextPlayerEvent){
        networkServer.broadCast(nextPlayerEvent);
    }

    public void roadcardsSync(RoadcardsSyncEvent roadcardsSyncEvent){
        networkServer.broadCast(roadcardsSyncEvent);
    }

    public void roadcardsRemoveSync(RoadcardsRemoveSyncEvent roadcardsRemoveSyncEvent){
        networkServer.broadCast(roadcardsRemoveSyncEvent);
    }
    public void moveBecauseOfShamrock(MoveBecauseOfShamrockEvent moveBecauseOfShamrockEvent){
        networkServer.broadCast(moveBecauseOfShamrockEvent);
    }

    public void setPlayerCheat(boolean cheat, String nick){
        getPlayerNick(nick).setCheat(cheat);
    }




    public void checkCheat(String nick){
        boolean cheaterFound = false;
        for(Player player:playerList){
            if (player.getCheat() && !nick.equals(player.getNick())){
                cheaterFound = true;
                Gdx.app.log("Info","cheater found: " + cheaterFound);

            }
        }
        if (cheaterFound){
            for(Player player:playerList){
                if (player.getCheat() && !nick.equals(player.getNick())){
                    CheatQueryEvent cheatQueryEvent = new CheatQueryEvent();
                    cheatQueryEvent.setMessage("Du wurdest beim Schummeln erwischt und verlierst 4 Punkte.");
                    //cheatQueryEvent.setScore(-4);
                    CheatScoreEvent cheatScoreEvent = new CheatScoreEvent();
                    cheatScoreEvent.setScore(-4);
                    cheatScoreEvent.setNick(player.getNick());
                    networkServer.broadCast(cheatScoreEvent);
                    networkServer.sendEvent(player.getNick(),cheatQueryEvent);
                }
                else if (player.getNick().equals(nick)){
                    CheatQueryEvent cheatQueryEvent = new CheatQueryEvent();
                    cheatQueryEvent.setMessage("Du hast einen Spieler beim Schummeln erwischt und erhälst 1 Punkt als Belohnung.");
                    //cheatQueryEvent.setScore(1);
                    CheatScoreEvent cheatScoreEvent = new CheatScoreEvent();
                    cheatScoreEvent.setScore(1);
                    cheatScoreEvent.setNick(player.getNick());
                    networkServer.broadCast(cheatScoreEvent);
                    networkServer.sendEvent(player.getNick(),cheatQueryEvent);
                }
                else{
                    CheatQueryEvent cheatQueryEvent = new CheatQueryEvent();
                    cheatQueryEvent.setMessage("Ein Spieler wurde beim Schummeln erwischt.");
                    networkServer.sendEvent(player.getNick(),cheatQueryEvent);
                }
            }
        }
        else{
            Gdx.app.log("Info","cheater not found: " + cheaterFound);
            for(Player player:playerList){
                if(!nick.equals(player.getNick())){
                    CheatQueryEvent cheatQueryEvent = new CheatQueryEvent();
                    cheatQueryEvent.setMessage("Ein Spieler hat jemanden zu unrecht beschuldigt.");
                    Gdx.app.log("Info","cheater not found: " + player.getNick());
                    Gdx.app.log("Info","message: " + cheatQueryEvent.getMessage());
                    networkServer.sendEvent(player.getNick(),cheatQueryEvent);

                }
                else{
                    CheatQueryEvent cheatQueryEvent = new CheatQueryEvent();
                    //cheatQueryEvent.setScore(-1);
                    CheatScoreEvent cheatScoreEvent = new CheatScoreEvent();
                    cheatScoreEvent.setScore(-1);
                    cheatScoreEvent.setNick(player.getNick());
                    networkServer.broadCast(cheatScoreEvent);

                    cheatQueryEvent.setMessage("Du hast zu unrecht beschuldigt, dir wird 1 Punkt abgezogen." );
                    networkServer.sendEvent(player.getNick(),cheatQueryEvent);
                    Gdx.app.log("Info","cheater not found: " + player.getNick());
                    Gdx.app.log("Info","message: " + cheatQueryEvent.getMessage());

                }
            }
        }
    }



}

