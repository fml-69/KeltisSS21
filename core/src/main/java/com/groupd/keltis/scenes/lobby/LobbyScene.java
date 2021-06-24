package com.groupd.keltis.scenes.lobby;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.management.RoadcardsStatus;
import com.groupd.keltis.management.SceneManager;
import com.groupd.keltis.network.NetworkClient;
import com.groupd.keltis.network.events.JoinEvent;
import com.groupd.keltis.network.events.NetworkEvent;
import com.groupd.keltis.network.events.RoadcardsSyncEvent;
import com.groupd.keltis.network.events.StartGameEvent;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.scenes.board.actors.Card;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.scenes.board.road_cards.Position;
import com.groupd.keltis.scenes.board.road_cards.RoadcardsList;
import com.groupd.keltis.utils.AssetPaths;
import com.groupd.keltis.utils.CardHelper;
import com.groupd.keltis.utils.JsonConverter;
import com.groupd.keltis.utils.RoadcardsToJson;

import java.util.ArrayList;
import java.util.Collections;

public class LobbyScene extends AbstractScene {

    List<String> uIList;
    Array <String> playerList = new Array<>();
    private float scale = 0.5f;


    private ArrayList drawPileNames = new ArrayList<>();
    private CardHelper cardHelper = new CardHelper(keltis);

    public LobbyScene(Keltis keltis) {
        super(keltis);

        ScreenViewport sv = new ScreenViewport();
        sv.setUnitsPerPixel(scale);
        stage = new Stage(sv);

    }

    @Override
    public void onNetworkEvent(NetworkEvent event) {
        if(event instanceof JoinEvent){
            playerList.add(((JoinEvent) event).nick);
            uIList.setItems(playerList);

            // send game logic with player, with the color provided by server
            keltis.gameLogic.getPlayerArrayList().add(new Player(keltis, ((JoinEvent) event).nick, ((JoinEvent) event).playerColor, ((JoinEvent) event).host));
        }else if(event instanceof StartGameEvent){
            drawPileNames = JsonConverter.convertToArrayList(((StartGameEvent) event).getJson());
            convertStringsToCards();
            fillHandcards();

            keltis.sceneManager.setScene(SceneManager.GAMESTATE.PLAYING);

            //Set allowed turn of first player to true for everyone
            keltis.gameLogic.getPlayerArrayList().get(0).setTurn(true);
        } else if(event instanceof RoadcardsSyncEvent){
            ArrayList<RoadcardsStatus> roadcardsStatusArrayList = RoadcardsToJson.convertToObject(((RoadcardsSyncEvent) event).getJson());
            ArrayList<Position> positionArrayList = new ArrayList<>();

            int i = 0;
            for(RoadcardsStatus roadcardsStatus:roadcardsStatusArrayList){
                positionArrayList.add(keltis.positionHelper.getPositionHashMap().get(roadcardsStatus.getPosition()));
                i++;
            }
            RoadcardsList roadcardsList = new RoadcardsList();
            roadcardsList.addRoadcards(keltis,positionArrayList);
            keltis.gameLogic.getBoard().setRoadcardsList(roadcardsList);
            keltis.gameLogic.setRoadCardsList(roadcardsList.getRoadcardsArrayList());
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

        VerticalGroup vg = new VerticalGroup().space(15).pad(15).fill();
        vg.setBounds(0, 0, Gdx.graphics.getWidth()*scale, Gdx.graphics.getHeight()*scale);
        stage.addActor(vg);

       // vg.setTransform(true);
       //  vg.setSize(40,10);


        uIList = new List<>(skin);
        vg.addActor(uIList);


        TextButton readyButton = new TextButton("Ready", skin);
        vg.addActor(readyButton);


        readyButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                keltis.gameLogic.createRoadcards(keltis);
                fillStringPile();
                Collections.shuffle(drawPileNames);
                String json = JsonConverter.convertToJson(drawPileNames);
                NetworkClient.INSTANCE.sendEvent(new StartGameEvent(json));
                return true;
            }
        });

       // BitmapFont font = new BitmapFont(Gdx.files.internal("default.fnt"));
       // font.getData().setScale(5,5);
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

    public void fillHandcards(){
        for(int j = 0; j < keltis.gameLogic.getPlayerArrayList().size();j++){
            for(int i=1;i<=8;i++) {
                keltis.gameLogic.getPlayerArrayList().get(j).getHandCards().add(keltis.gameLogic.getDrawPile().remove(keltis.gameLogic.getDrawPile().size() - 1));
            }
        }

        //keltis.gameLogic.setDrawPile(drawPile);
    }
    public void convertStringsToCards(){
        for(Object string:drawPileNames){
            keltis.gameLogic.getDrawPile().add((Card) cardHelper.getCardHashmap().get(string));
        }
    }


    public void fillStringPile(){
        for(int i=1;i<=2;i++){
            drawPileNames.add("blueZero");
            drawPileNames.add("blueOne");
            drawPileNames.add("blueTwo");
            drawPileNames.add("blueThree");
            drawPileNames.add("blueFour");
            drawPileNames.add("blueFive");
            drawPileNames.add("blueSix");
            drawPileNames.add("blueSeven");
            drawPileNames.add("blueEight");
            drawPileNames.add( "blueNine");
            drawPileNames.add( "blueTen");

            drawPileNames.add( "greenZero");
            drawPileNames.add( "greenOne");
            drawPileNames.add( "greenTwo");
            drawPileNames.add("greenThree");
            drawPileNames.add( "greenFour");
            drawPileNames.add("greenFive");
            drawPileNames.add("greenSix");
            drawPileNames.add( "greenSeven");
            drawPileNames.add("greenEight");
            drawPileNames.add("greenNine");
            drawPileNames.add( "greenTen");

            drawPileNames.add("purpleZero");
            drawPileNames.add("purpleOne");
            drawPileNames.add("purpleTwo");
            drawPileNames.add( "purpleThree");
            drawPileNames.add("purpleFour");
            drawPileNames.add("purpleFive");
            drawPileNames.add( "purpleSix");
            drawPileNames.add("purpleSeven");
            drawPileNames.add( "purpleEight");
            drawPileNames.add( "purpleNine");
            drawPileNames.add("purpleTen");

            drawPileNames.add( "redZero");
            drawPileNames.add( "redOne");
            drawPileNames.add("redTwo");
            drawPileNames.add("redThree");
            drawPileNames.add( "redFour");
            drawPileNames.add( "redFive");
            drawPileNames.add( "redSix");
            drawPileNames.add("redSeven");
            drawPileNames.add("redEight");
            drawPileNames.add( "redNine");
            drawPileNames.add("redTen");

            drawPileNames.add("yellowZero");
            drawPileNames.add("yellowOne");
            drawPileNames.add("yellowTwo");
            drawPileNames.add("yellowThree");
            drawPileNames.add( "yellowFour");
            drawPileNames.add( "yellowFive");
            drawPileNames.add("yellowSix");
            drawPileNames.add( "yellowSeven");
            drawPileNames.add("yellowEight");
            drawPileNames.add( "yellowNine");
            drawPileNames.add( "yellowTen");
        }
    }
}

