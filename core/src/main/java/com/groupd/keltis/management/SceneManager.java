package com.groupd.keltis.management;

import com.groupd.keltis.Keltis;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.scenes.board.Board;
import com.groupd.keltis.scenes.instruction.TextInstructionScreen;
import com.groupd.keltis.scenes.lobby.LobbyScene;
import com.groupd.keltis.scenes.login.EntryScene;
import com.groupd.keltis.scenes.menu.MenuScreen;
import com.groupd.keltis.scenes.menu.OptionsScreen;
import com.groupd.keltis.scenes.menu.IngameMenuScreen;

import java.util.HashMap;

public class SceneManager {

    private final Keltis keltis;
    private HashMap<GAMESTATE, AbstractScene> sceneHashMap;
    public enum GAMESTATE{LOGIN, LOBBY, PLAYING, MENU, SETTINGS, INGAME_MENU, TEXT_INSTRUCTIONS}

    private AbstractScene activeScene;

    public SceneManager(final Keltis keltis){
        this.keltis = keltis;
        sceneMapper();
        setScene(GAMESTATE.MENU);

    }

    private void sceneMapper(){
        this.sceneHashMap = new HashMap<GAMESTATE, AbstractScene>();
        this.sceneHashMap.put(GAMESTATE.PLAYING, new Board(keltis));
        this.sceneHashMap.put(GAMESTATE.MENU, new MenuScreen(keltis));
        this.sceneHashMap.put(GAMESTATE.SETTINGS, new OptionsScreen(keltis));
        this.sceneHashMap.put(GAMESTATE.INGAME_MENU, new IngameMenuScreen(keltis));
        this.sceneHashMap.put(GAMESTATE.LOGIN, new EntryScene(keltis));
        this.sceneHashMap.put(GAMESTATE.LOBBY, new LobbyScene(keltis));
        this.sceneHashMap.put(GAMESTATE.TEXT_INSTRUCTIONS, new TextInstructionScreen(keltis));
    }


    public void setScene(GAMESTATE scene){
        AbstractScene newScene = sceneHashMap.get(scene);
        keltis.setScreen(newScene);
        activeScene = newScene;

    }

    public void dispose(){
        for(AbstractScene abstractScene: sceneHashMap.values()){
            if(abstractScene!=null){
                abstractScene.dispose();
            }
        }
    }

    public AbstractScene getActiveScene(){
        return activeScene;
    }

}
