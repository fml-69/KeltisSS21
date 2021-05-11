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

import com.groupd.keltis.scenes.board.actors.Card;
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

    private HashMap<String, Player> playerHashMap = new HashMap<>();
    private HashMap<String, Figure> figuresHashMap = new HashMap<>();
    private int x = 1;

    private RoadcardsList roadcardsList = new RoadcardsList();

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
            advanceFigure("blue1");
            Gdx.app.log("Spieler1 Punkte: ",  String.valueOf(playerHashMap.get("player1").getScore()));
            Gdx.app.log("Spieler2 Punkte: ",  String.valueOf(playerHashMap.get("player2").getScore()));
            Gdx.app.log("Spieler3 Punkte: ",  String.valueOf(playerHashMap.get("player3").getScore()));
            Gdx.app.log("Spieler4 Punkte: ",  String.valueOf(playerHashMap.get("player4").getScore()));
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

        roadcardsList.assignRoadcards(keltis);
        for(Roadcards roadcards : roadcardsList.getRoadcardsArrayList()){
            stage.addActor(roadcards);
        }

        Player player1 = new Player(keltis, "blue");
        playerHashMap.put("player1", player1);
        figuresHashMap.putAll(player1.getFigures());
        figuresHashMap.get("blue1").spritePos(565, 124);
        figuresHashMap.get("blue2").spritePos(785, 124);
        figuresHashMap.get("blue3").spritePos(1005, 124);
        figuresHashMap.get("blue4").spritePos(1225, 124);
        figuresHashMap.get("blue5").spritePos(1445, 124);
        for(int i = 1; i<6; i++) {
            for (Figure figure : figuresHashMap.values()) {
                if (figure.getName().equals("blue"+i)) {
                    stage.addActor(figure);
                }
            }
        }

        Player player2 = new Player(keltis, "red");
        playerHashMap.put("player2", player2);
        figuresHashMap.putAll(player2.getFigures());
        figuresHashMap.get("red1").spritePos(595, 124);
        figuresHashMap.get("red2").spritePos(815, 124);
        figuresHashMap.get("red3").spritePos(1035, 124);
        figuresHashMap.get("red4").spritePos(1255, 124);
        figuresHashMap.get("red5").spritePos(1475, 124);
        for(int i = 1; i<6; i++) {
            for (Figure figure : figuresHashMap.values()) {
                if (figure.getName().equals("red"+i)) {
                    stage.addActor(figure);
                }
            }
        }

        Player player3 = new Player(keltis, "green");
        playerHashMap.put("player3", player3);
        figuresHashMap.putAll(player3.getFigures());
        figuresHashMap.get("green1").spritePos(625, 124);
        figuresHashMap.get("green2").spritePos(845, 124);
        figuresHashMap.get("green3").spritePos(1065, 124);
        figuresHashMap.get("green4").spritePos(1285, 124);
        figuresHashMap.get("green5").spritePos(1505, 124);
        for(int i = 1; i<6; i++) {
            for (Figure figure : figuresHashMap.values()) {
                if (figure.getName().equals("green"+i)) {
                    stage.addActor(figure);
                }
            }
        }

        Player player4 = new Player(keltis, "yellow");
        playerHashMap.put("player4", player4);
        figuresHashMap.putAll(player4.getFigures());
        figuresHashMap.get("yellow1").spritePos(655, 124);
        figuresHashMap.get("yellow2").spritePos(875, 124);
        figuresHashMap.get("yellow3").spritePos(1095, 124);
        figuresHashMap.get("yellow4").spritePos(1315, 124);
        figuresHashMap.get("yellow5").spritePos(1535, 124);
        for(int i = 1; i<6; i++) {
            for (Figure figure : figuresHashMap.values()) {
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
        figuresHashMap.get(figure).moveUp();
    }


}
