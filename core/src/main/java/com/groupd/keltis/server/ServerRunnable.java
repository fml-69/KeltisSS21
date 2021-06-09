package com.groupd.keltis.server;

import com.badlogic.gdx.Gdx;
import com.groupd.keltis.network.NetworkServer;
import com.groupd.keltis.network.events.CheatEvent;
import com.groupd.keltis.network.events.CheatQueryEvent;
import com.groupd.keltis.network.events.JoinEvent;
import com.groupd.keltis.network.events.StartGameEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class ServerRunnable implements Runnable{


    private List<Player> playerList = new ArrayList<>();

    private final NetworkServer networkServer;


    public ServerRunnable(int port, CountDownLatch countDownLatch){

        networkServer = new NetworkServer(port, countDownLatch, this);
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
        } while (true);
    }


    public void join(String nick){

        for(Player player:playerList){
           networkServer.sendEvent(nick, new JoinEvent(player.nick));
        }

        networkServer.broadCast(new JoinEvent(nick));
        // first player added will be automatically host by boolean value of isEmpty()
        playerList.add(new Player(nick, playerList.isEmpty()));

    }

    // only Host can start the game
    public void onStartGame(StartGameEvent event, String nick){
        Player player = getPlayerNick(nick);
        if(player != null && player.host){
            // disabled for easier development
            //if(playerList.size() >= 2 && playerList.size() <= 4){
                networkServer.broadCast(event);

            //}
        }
    }

/*
    public void onCheatEVENT(CheatEvent event, String nick){
        // überprüfen ob ein Spieler geschummelt hat
        for (Player player : playerList) {
            if () {
                if () {

                }
            }
        }
    }
*/


    // access list of players
    public List<Player> getPlayerList() {
        return playerList;
    }

    // access player with specific nick
    public Player getPlayerNick(String nick){

        for(Player player:playerList){
            if(player.nick.equals(nick)){
                return player;
            }
        }

        return null;
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
                    cheatQueryEvent.setMessage("Du wurdest beim Schummeln erwischt und wirst nun bestraft.");
                    networkServer.sendEvent(player.getNick(),cheatQueryEvent);
                }
                else if (player.getNick().equals(nick)){
                    CheatQueryEvent cheatQueryEvent = new CheatQueryEvent();
                    cheatQueryEvent.setMessage("Du hast einen Spieler beim Schummeln erwischt.");
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
                    cheatQueryEvent.setMessage("Du hast zu unrecht beschuldigt und wirst nun bestraft.");
                    networkServer.sendEvent(player.getNick(),cheatQueryEvent);
                    Gdx.app.log("Info","cheater not found: " + player.getNick());
                    Gdx.app.log("Info","message: " + cheatQueryEvent.getMessage());

                }
            }
        }
    }



}

