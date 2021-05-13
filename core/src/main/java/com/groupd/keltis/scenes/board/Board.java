package com.groupd.keltis.scenes.board;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.accelerometer.ShakeDetector;
import com.groupd.keltis.scenes.AbstractScene;

import com.groupd.keltis.scenes.board.actors.Figure;

import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.RoadcardsList;

import com.groupd.keltis.utils.AssetPaths;
import com.groupd.keltis.scenes.board.actors.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Board extends AbstractScene {

    private OrthographicCamera camera;


    private final Image board;
    private final Image branches;
    private final Image hudBar;

    private HashMap<String, Player> player = new HashMap<>();
    private HashMap<String, Figure> playerHashMap = new HashMap<>();
    private int x = 1;

    private RoadcardsList roadcardsList = new RoadcardsList();
    private ShamrockDialog shamrockDialog;

    private boolean isCheatingDialogShowing = false;

    public Board(final Keltis keltis){
        super(keltis);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT);
        this.stage = new Stage(new StretchViewport(Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT, camera));
        keltis.batch.setProjectionMatrix(camera.combined);


        board = new Image((Texture) keltis.assetManager.get(AssetPaths.BOARD_BACKGROUND));
        branches = new Image((Texture) keltis.assetManager.get(AssetPaths.BOARD_BRANCHES));
        hudBar = new Image((Texture) keltis.assetManager.get(AssetPaths.BOARD_HUD_BAR));
        shamrockDialog = new ShamrockDialog("Herzlichen Glückwunsch!", keltis.assetManager.get(AssetPaths.DIALOG_SKIN,Skin.class));

    }

    @Override
    public void update(float delta) {
        stage.act(delta);

        if(x % 200 == 0){
            //nur um die Funktionalität zu testen
            Roadcards.checkRoadcards("blue1",this.playerHashMap,this.roadcardsList,this.player, this);
            advanceFigure("blue1");
            Gdx.app.log("Spieler1 Punkte: ",  String.valueOf(player.get("player1").getScore()));
            Gdx.app.log("Spieler2 Punkte: ",  String.valueOf(player.get("player2").getScore()));
            Gdx.app.log("Spieler3 Punkte: ",  String.valueOf(player.get("player3").getScore()));
            Gdx.app.log("Spieler4 Punkte: ",  String.valueOf(player.get("player4").getScore()));
            Gdx.app.log("----------------", "-------------------------------");
        }
        x++;
    }

    private void checkShaking() {
        if(ShakeDetector.phoneIsShaking() && !isCheatingDialogShowing) {
            isCheatingDialogShowing = true;
            YesNoDialog dialog = new YesNoDialog("Schummelverdacht",
                    keltis.assetManager.get(AssetPaths.DIALOG_SKIN, Skin.class),
                    new YesNoDialog.Callback() {
                        @Override
                        public void result(boolean result) {
                            isCheatingDialogShowing = false;
                            // accuseOfCheating(result);
                        }
                    });

            showDialog(dialog, stage, 3);
        }
    }

    /*
    //Will be implemented on server side (currently just here till the server is ready)
    private void accuseOfCheating(boolean result) {
        Collection<Player> cheaters = getCheatingPlayers();

        if (cheaters.isEmpty()) {
            //return negative answer --> punish accusing player
        } else  {
            //return positive answer --> punish all cheaters and reward accusing player
        }
    }

    //Will be implemented on server side (currently just here till the server is ready)
    private Collection<Player> getCheatingPlayers() {
        List<Player> cheaters = new ArrayList<>();

        for (Player p : player.values()) {
            if (p.getCheat()) {
                cheaters.add(p);
            }
        }
        return cheaters;
    }
     */

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
        checkShaking();
    }

    public void showDialog(Dialog dialog, Stage stage, float scale) {
        dialog.show(stage);
        dialog.setScale(scale);
        dialog.setOrigin(Align.center);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        stage.addActor(board);
        stage.addActor(branches);

        roadcardsList.assignRoadcards(keltis);
        for(Roadcards roadcards : roadcardsList.getRoadcardsArrayList()){
            stage.addActor(roadcards);
        }

        Player player1 = new Player(keltis, "blue");
        player.put("player1", player1);
        playerHashMap.putAll(player1.getFigures());
        playerHashMap.get("blue1").spritePos(565, 124);
        playerHashMap.get("blue2").spritePos(785, 124);
        playerHashMap.get("blue3").spritePos(1005, 124);
        playerHashMap.get("blue4").spritePos(1225, 124);
        playerHashMap.get("blue5").spritePos(1445, 124);
        for(int i = 1; i<6; i++) {
            for (Figure figure : playerHashMap.values()) {
                if (figure.getName().equals("blue"+i)) {
                    stage.addActor(figure);
                }
            }
        }

        Player player2 = new Player(keltis, "red");
        player.put("player2", player2);
        playerHashMap.putAll(player2.getFigures());
        playerHashMap.get("red1").spritePos(595, 124);
        playerHashMap.get("red2").spritePos(815, 124);
        playerHashMap.get("red3").spritePos(1035, 124);
        playerHashMap.get("red4").spritePos(1255, 124);
        playerHashMap.get("red5").spritePos(1475, 124);
        for(int i = 1; i<6; i++) {
            for (Figure figure : playerHashMap.values()) {
                if (figure.getName().equals("red"+i)) {
                    stage.addActor(figure);
                }
            }
        }

        Player player3 = new Player(keltis, "green");
        player.put("player3", player3);
        playerHashMap.putAll(player3.getFigures());
        playerHashMap.get("green1").spritePos(625, 124);
        playerHashMap.get("green2").spritePos(845, 124);
        playerHashMap.get("green3").spritePos(1065, 124);
        playerHashMap.get("green4").spritePos(1285, 124);
        playerHashMap.get("green5").spritePos(1505, 124);
        for(int i = 1; i<6; i++) {
            for (Figure figure : playerHashMap.values()) {
                if (figure.getName().equals("green"+i)) {
                    stage.addActor(figure);
                }
            }
        }

        Player player4 = new Player(keltis, "yellow");
        player.put("player4", player4);
        playerHashMap.putAll(player4.getFigures());
        playerHashMap.get("yellow1").spritePos(655, 124);
        playerHashMap.get("yellow2").spritePos(875, 124);
        playerHashMap.get("yellow3").spritePos(1095, 124);
        playerHashMap.get("yellow4").spritePos(1315, 124);
        playerHashMap.get("yellow5").spritePos(1535, 124);
        for(int i = 1; i<6; i++) {
            for (Figure figure : playerHashMap.values()) {
                if (figure.getName().equals("yellow"+i)) {
                    stage.addActor(figure);
                }
            }
        }


        stage.addActor(hudBar);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void advanceFigure(String figure){
        playerHashMap.get(figure).moveUp();
    }

    public ShamrockDialog getShamrockDialog() {
        return shamrockDialog;
    }
}
