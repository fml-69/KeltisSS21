package com.groupd.keltis.cheating;

import com.groupd.keltis.network.NetworkServer;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.server.ServerRunnable;

import org.mockito.Mockito;

import java.util.ArrayList;

//used to create a mocked version of our NetworkServer class
public class ServerRunnableTestable extends ServerRunnable {

    public ServerRunnableTestable() {
        this.networkServer = Mockito.mock(NetworkServer.class);
    }

    public void setPlayers(ArrayList<Player> players) {
        this.playerList = players;
    }

    public void setPlayerCheatStatus(String playerName, boolean isCheating) {
        getPlayerNick(playerName).setCheat(true);
    }

}
