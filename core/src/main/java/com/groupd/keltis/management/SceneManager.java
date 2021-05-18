package com.groupd.keltis.management;

import com.groupd.keltis.Keltis;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.scenes.board.Board;
import com.groupd.keltis.scenes.lobby.LobbyScene;
import com.groupd.keltis.scenes.login.EntryScene;
import com.groupd.keltis.scenes.menu.MenuScreen;

import java.util.HashMap;

public class SceneManager {
    private final Keltis keltis;
    private HashMap<GAMESTATE, AbstractScene> sceneHashMap;
    public enum GAMESTATE{LOGIN, LOBBY, PLAYING, MENU, SETTINGS}

    public SceneManager(final Keltis keltis){
        this.keltis = keltis;
        sceneMapper();
        setScene(GAMESTATE.LOGIN);

    }

    private void sceneMapper(){
        this.sceneHashMap = new HashMap<GAMESTATE, AbstractScene>();
        this.sceneHashMap.put(GAMESTATE.PLAYING, new Board(keltis));
        this.sceneHashMap.put(GAMESTATE.MENU, new MenuScreen(keltis));
        this.sceneHashMap.put(GAMESTATE.LOGIN, new EntryScene(keltis));
        this.sceneHashMap.put(GAMESTATE.LOBBY, new LobbyScene(keltis));


    }

    public void setScene(GAMESTATE scene){
        keltis.setScreen(sceneHashMap.get(scene));
    }

    public void dispose(){
        for(AbstractScene abstractScene: sceneHashMap.values()){
            if(abstractScene!=null){
                abstractScene.dispose();
            }
        }
    }
}
