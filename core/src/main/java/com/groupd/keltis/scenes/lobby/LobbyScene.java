package com.groupd.keltis.scenes.lobby;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.management.SceneManager;
import com.groupd.keltis.network.NetworkClient;
import com.groupd.keltis.network.events.JoinEvent;
import com.groupd.keltis.network.events.NetworkEvent;
import com.groupd.keltis.network.events.StartGameEvent;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.scenes.board.actors.Card;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.utils.AssetPaths;

import java.util.ArrayList;
import java.util.Collections;

public class LobbyScene extends AbstractScene {

    List<String> uIList;
    Array <String> playerList = new Array<>();
    private ArrayList<Card> drawPile = new ArrayList<>();

    public LobbyScene(Keltis keltis) {
        super(keltis);
        fillDrawPile();
        Collections.shuffle(drawPile);
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void onNetworkEvent(NetworkEvent event) {
        if(event instanceof JoinEvent){
            playerList.add(((JoinEvent) event).nick);
            uIList.setItems(playerList);

            // send game logic with player, with the color provided by server
            keltis.gameLogic.getPlayerArrayList().add(new Player(keltis, ((JoinEvent) event).nick, ((JoinEvent) event).playerColor, false));
        }
        else if(event instanceof StartGameEvent){
            fillHandcrds();
            keltis.sceneManager.setScene(SceneManager.GAMESTATE.PLAYING);
            //Set allowed turn of first player to true for everyone
            keltis.gameLogic.getPlayerArrayList().get(0).setTurn(true);
        }
    }


    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

    }


    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal(AssetPaths.MENU_ASSET));

        VerticalGroup vg = new VerticalGroup().space(3).pad(5).fill();
        vg.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(vg);


        uIList = new List<>(skin);
        vg.addActor(uIList);


        TextButton readyButton = new TextButton("Ready", skin);
        vg.addActor(readyButton);

        readyButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                NetworkClient.INSTANCE.sendEvent(new StartGameEvent());
                return true;
            }
        });

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
        NetworkClient.INSTANCE.receiveEvents();
    }

    public void fillHandcrds(){
        for(Player player:keltis.gameLogic.getPlayerArrayList()){
            for(int i=1;i<=8;i++) {
                player.getHandCards().add(drawPile.remove(drawPile.size() - 1));
            }
        }

        keltis.gameLogic.setDrawPile(drawPile);
    }

    public void fillDrawPile(){
        String s;
        for(int i=1;i<=2;i++){
            if(i==1){
                s="ONE";
            } else{
                s="TWO";
            }
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_ZERO), "blueZero"+s, "blue", 0));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_ONE), "blueOne"+s, "blue", 1));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_TWO), "blueTwo"+s, "blue", 2));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_THREE), "blueThree"+s, "blue", 3));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_FOUR), "blueFour"+s, "blue", 4));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_FIVE), "blueFive"+s, "blue", 5));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_SIX), "blueSix"+s, "blue", 6));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_SEVEN), "blueSeven"+s, "blue", 7));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_EIGHT), "blueEight"+s, "blue", 8));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_NINE), "blueNine"+s, "blue", 9));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_TEN), "blueTen"+s, "blue", 10));

            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_ZERO), "greenZero"+s, "green", 0));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_ONE), "greenOne"+s, "green", 1));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_TWO), "greenTwo"+s, "green", 2));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_THREE), "greenThree"+s, "green", 3));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_FOUR), "greenFour"+s, "green", 4));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_FIVE), "greenFive"+s, "green", 5));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_SIX), "greenSix"+s, "green", 6));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_SEVEN), "greenSeven"+s, "green", 7));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_EIGHT), "greenEight"+s, "green", 8));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_NINE), "greenNine"+s, "green", 9));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_TEN), "greenTen"+s, "green", 10));

            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_ZERO), "purpleZero"+s, "purple", 0));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_ONE), "purpleOne"+s, "purple", 1));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_TWO), "purpleTwo"+s, "purple", 2));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_THREE), "purpleThree"+s, "purple", 3));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_FOUR), "purpleFour"+s, "purple", 4));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_FIVE), "purpleFive"+s, "purple", 5));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_SIX), "purpleSix"+s, "purple", 6));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_SEVEN), "purpleSeven"+s, "purple", 7));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_EIGHT), "purpleEight"+s, "purple", 8));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_NINE), "purpleNine"+s, "purple", 9));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_TEN), "purpleTen"+s, "purple", 10));

            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_ZERO), "redZero"+s, "red", 0));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_ONE), "redOne"+s, "red", 1));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_TWO), "redTwo"+s, "red", 2));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_THREE), "redThree"+s, "red", 3));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_FOUR), "redFour"+s, "red", 4));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_FIVE), "redFive"+s, "red", 5));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_SIX), "redSix"+s, "red", 6));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_SEVEN), "redSeven"+s, "red", 7));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_EIGHT), "redEight"+s, "red", 8));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_NINE), "redNine"+s, "red", 9));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_TEN), "redTen"+s, "red", 10));

            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_ZERO), "yellowZero"+s, "yellow", 0));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_ONE), "yellowOne"+s, "yellow", 1));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_TWO), "yellowTwo"+s, "yellow", 2));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_THREE), "yellowThree"+s, "yellow", 3));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_FOUR), "yellowFour"+s, "yellow", 4));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_FIVE), "yellowFive"+s, "yellow", 5));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_SIX), "yellowSix"+s, "yellow", 6));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_SEVEN), "yellowSeven"+s, "yellow", 7));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_EIGHT), "yellowEight"+s, "yellow", 8));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_NINE), "yellowNine"+s, "yellow", 9));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_TEN), "yellowTen"+s, "yellow", 10));
        }

    }
}

