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
        for(int i=1;i<=2;i++){
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_ZERO), "blueZero", "blue", 0));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_ONE), "blueOne", "blue", 1));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_TWO), "blueTwo", "blue", 2));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_THREE), "blueThree", "blue", 3));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_FOUR), "blueFour", "blue", 4));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_FIVE), "blueFive", "blue", 5));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_SIX), "blueSix", "blue", 6));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_SEVEN), "blueSeven", "blue", 7));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_EIGHT), "blueEight", "blue", 8));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_NINE), "blueNine", "blue", 9));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_TEN), "blueTen", "blue", 10));

            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_ZERO), "greenZero", "green", 0));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_ONE), "greenOne", "green", 1));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_TWO), "greenTwo", "green", 2));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_THREE), "greenThree", "green", 3));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_FOUR), "greenFour", "green", 4));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_FIVE), "greenFive", "green", 5));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_SIX), "greenSix", "green", 6));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_SEVEN), "greenSeven", "green", 7));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_EIGHT), "greenEight", "green", 8));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_NINE), "greenNine", "green", 9));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_TEN), "greenTen", "green", 10));

            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_ZERO), "purpleZero", "purple", 0));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_ONE), "purpleOne", "purple", 1));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_TWO), "purpleTwo", "purple", 2));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_THREE), "purpleThree", "purple", 3));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_FOUR), "purpleFour", "purple", 4));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_FIVE), "purpleFive", "purple", 5));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_SIX), "purpleSix", "purple", 6));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_SEVEN), "purpleSeven", "purple", 7));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_EIGHT), "purpleEight", "purple", 8));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_NINE), "purpleNine", "purple", 9));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_TEN), "purpleTen", "purple", 10));

            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_ZERO), "redZero", "red", 0));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_ONE), "redOne", "red", 1));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_TWO), "redTwo", "red", 2));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_THREE), "redThree", "red", 3));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_FOUR), "redFour", "red", 4));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_FIVE), "redFive", "red", 5));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_SIX), "redSix", "red", 6));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_SEVEN), "redSeven", "red", 7));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_EIGHT), "redEight", "red", 8));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_NINE), "redNine", "red", 9));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_RED_TEN), "redTen", "red", 10));

            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_ZERO), "yellowZero", "yellow", 0));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_ONE), "yellowOne", "yellow", 1));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_TWO), "yellowTwo", "yellow", 2));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_THREE), "yellowThree", "yellow", 3));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_FOUR), "yellowFour", "yellow", 4));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_FIVE), "yellowFive", "yellow", 5));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_SIX), "yellowSix", "yellow", 6));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_SEVEN), "yellowSeven", "yellow", 7));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_EIGHT), "yellowEight", "yellow", 8));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_NINE), "yellowNine", "yellow", 9));
            drawPile.add(new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_TEN), "yellowTen", "yellow", 10));
        }

    }
}

