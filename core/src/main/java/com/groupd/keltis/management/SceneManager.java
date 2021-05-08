package com.groupd.keltis.management;

import com.groupd.keltis.Keltis;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.scenes.board.Board;
import com.groupd.keltis.scenes.menu.MenuScreen;

import java.util.HashMap;

public class SceneManager {
    private final Keltis keltis;
    private HashMap<GAMESTATE, AbstractScene> sceneHashMap;
    public enum GAMESTATE{LOGIN, PLAYING, MENU, SETTINGS}

    public SceneManager(final Keltis keltis){
        this.keltis = keltis;
        sceneMapper();
        setScene(GAMESTATE.MENU);
    }

    private void sceneMapper(){
        this.sceneHashMap = new HashMap<GAMESTATE, AbstractScene>();
        this.sceneHashMap.put(GAMESTATE.PLAYING, new Board(keltis));
        this.sceneHashMap.put(GAMESTATE.MENU, new MenuScreen(keltis));
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
