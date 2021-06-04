package com.groupd.keltis.server;

import com.groupd.keltis.network.NetworkServer;
import com.groupd.keltis.network.events.JoinEvent;
import com.groupd.keltis.network.events.StartGameEvent;
import com.groupd.keltis.network.events.TurnEvent;

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


    // called when a player joins the game
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


    // can access nick of player who made for a turn
    public void onTurn(TurnEvent turnEvent) {
        networkServer.broadCast(turnEvent);

    }

}

