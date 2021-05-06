package com.groupd.keltis.scenes.board;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;



import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.accelerometer.ShakeDetector;
import com.groupd.keltis.scenes.AbstractScene;

import com.groupd.keltis.scenes.board.actors.Figure;

import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.RoadcardsList;

import com.groupd.keltis.utils.AssetPaths;
import com.groupd.keltis.scenes.board.actors.Player;

import java.util.HashMap;

public class Board extends AbstractScene {

    private OrthographicCamera camera;


    private final Image board;
    private final Image branches;
    private final Image hudBar;

    private HashMap<String, Player> player = new HashMap<>();
    private HashMap<String, Figure> playerHashMap = new HashMap<>();
    private int x = 1;

    public Board(final Keltis keltis){
        super(keltis);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT);
        this.stage = new Stage(new StretchViewport(Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT, camera));
        keltis.batch.setProjectionMatrix(camera.combined);

        Gdx.input.setInputProcessor(stage);

        board = new Image((Texture) keltis.assetManager.get(AssetPaths.BOARD_BACKGROUND));
        branches = new Image((Texture) keltis.assetManager.get(AssetPaths.BOARD_BRANCHES));
        hudBar = new Image((Texture) keltis.assetManager.get(AssetPaths.BOARD_HUD_BAR));
    }

    @Override
    public void update(float delta) {
        stage.act(delta);

        if(x % 200 == 0){
            Gdx.app.log("Spieler1 Punkte: ",  String.valueOf(player.get("Spieler1").getScore()));
            Gdx.app.log("Spieler2 Punkte: ",  String.valueOf(player.get("Spieler2").getScore()));
            Gdx.app.log("Spieler3 Punkte: ",  String.valueOf(player.get("Spieler3").getScore()));
            Gdx.app.log("Spieler4 Punkte: ",  String.valueOf(player.get("Spieler4").getScore()));
            Gdx.app.log("----------------", "-------------------------------");
        }
        x++;


        if(ShakeDetector.phoneIsShaking()) {
            ShakeDetector.wasShaken();

        }

    }


    @Override
    public void render(float delta) {
        super.render(delta);
        stage.draw();
    }

    @Override
    public void show() {
        stage.addActor(board);
        stage.addActor(branches);



        Player player1 = new Player(keltis);
        player1.setColor("blue");
        player1.initializePlayers();
        player.put("Spieler1", player1);
        playerHashMap.putAll(player1.getFigures());
        playerHashMap.get("blueBig").spritePos(565, 124);
        playerHashMap.get("blueSmall1").spritePos(785, 124);
        playerHashMap.get("blueSmall2").spritePos(1005, 124);
        playerHashMap.get("blueSmall3").spritePos(1225, 124);
        playerHashMap.get("blueSmall4").spritePos(1445, 124);

        Player player2 = new Player(keltis);
        player2.setColor("red");
        player2.initializePlayers();
        player.put("Spieler2", player2);
        playerHashMap.putAll(player2.getFigures());
        playerHashMap.get("redBig").spritePos(595, 124);
        playerHashMap.get("redSmall1").spritePos(815, 124);
        playerHashMap.get("redSmall2").spritePos(1035, 124);
        playerHashMap.get("redSmall3").spritePos(1255, 124);
        playerHashMap.get("redSmall4").spritePos(1475, 124);

        Player player3 = new Player(keltis);
        player3.setColor("green");
        player3.initializePlayers();
        player.put("Spieler3", player3);
        playerHashMap.putAll(player3.getFigures());
        playerHashMap.get("greenBig").spritePos(625, 124);
        playerHashMap.get("greenSmall1").spritePos(845, 124);
        playerHashMap.get("greenSmall2").spritePos(1065, 124);
        playerHashMap.get("greenSmall3").spritePos(1285, 124);
        playerHashMap.get("greenSmall4").spritePos(1505, 124);

        Player player4 = new Player(keltis);
        player4.setColor("yellow");
        player4.initializePlayers();
        player.put("Spieler4", player4);
        playerHashMap.putAll(player4.getFigures());
        playerHashMap.get("yellowBig").spritePos(655, 124);
        playerHashMap.get("yellowSmall1").spritePos(875, 124);
        playerHashMap.get("yellowSmall2").spritePos(1095, 124);
        playerHashMap.get("yellowSmall3").spritePos(1315, 124);
        playerHashMap.get("yellowSmall4").spritePos(1535, 124);

        for(Figure figure :playerHashMap.values()){
            stage.addActor(figure);
        }
        stage.addActor(hudBar);
        roadcardsList.assignRoadcards(keltis);
        for(Roadcards roadcards : roadcardsList.getRoadcardsArrayList()){
            stage.addActor(roadcards);
        }

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

    public boolean pressed(String player){
        return false;
    }


}
