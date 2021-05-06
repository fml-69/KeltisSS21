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
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.RoadcardsList;
import com.groupd.keltis.utils.AssetPaths;

import java.util.HashMap;


public class Board extends AbstractScene {

    private OrthographicCamera camera;
    private Image board;
    private Image branches;
    private Image hudBar;
    private Player playerBlue1;
    private Player playerGreen1;
    private Player playerRed1;
    private Player playerYellow1;
    private Player playerBlue2;
    private Player playerGreen2;
    private Player playerRed2;
    private Player playerYellow2;
    private Player playerBlue3;
    private Player playerGreen3;
    private Player playerRed3;
    private Player playerYellow3;
    private Player playerBlue4;
    private Player playerGreen4;
    private Player playerRed4;
    private Player playerYellow4;
    private Player playerBlue5;
    private Player playerGreen5;
    private Player playerRed5;
    private Player playerYellow5;
    private HashMap<String, Player> playerHashMap = new HashMap<>();
    private int x = 0;
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
        stage.addActor(hudBar);
        roadcardsList.assignRoadcards(keltis);
        for(Roadcards roadcards : roadcardsList.getRoadcardsArrayList()){
            stage.addActor(roadcards);
        }
        playerBlue1 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE), "playerBlue1");
        playerHashMap.put("playerBlue1", playerBlue1);
        playerBlue1.spritePos(565, 124);
        playerGreen1 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN), "playerGreen1");
        playerHashMap.put("playerGreen1", playerGreen1);
        playerGreen1.spritePos(595, 124);
        playerRed1 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED), "playerRed1");
        playerHashMap.put("playerRed1", playerRed1);
        playerRed1.spritePos(625, 124);
        playerYellow1 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW), "playerYellow1");
        playerHashMap.put("playerYellow1", playerYellow1);
        playerYellow1.spritePos(655, 124);

        playerBlue2 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE), "playerBlue2");
        playerHashMap.put("playerBlue2", playerBlue2);
        playerBlue2.spritePos(785, 124);
        playerGreen2 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN), "playerGreen2");
        playerHashMap.put("playerGreen2", playerGreen2);
        playerGreen2.spritePos(815, 124);
        playerRed2 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED), "playerRed2");
        playerHashMap.put("playerRed2", playerRed2);
        playerRed2.spritePos(845, 124);
        playerYellow2 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW), "playerYellow2");
        playerHashMap.put("playerYellow2", playerYellow2);
        playerYellow2.spritePos(875, 124);

        playerBlue3 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE), "playerBlue3");
        playerHashMap.put("playerBlue3", playerBlue3);
        playerBlue3.spritePos(1005, 124);
        playerGreen3 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN), "playerGreen3");
        playerHashMap.put("playerGreen3", playerGreen3);
        playerGreen3.spritePos(1035, 124);
        playerRed3 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED), "playerRed3");
        playerHashMap.put("playerRed3", playerRed3);
        playerRed3.spritePos(1065, 124);
        playerYellow3 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW), "playerYellow3");
        playerHashMap.put("playerYellow3", playerYellow3);
        playerYellow3.spritePos(1095, 124);

        playerBlue4 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE), "playerBlue4");
        playerHashMap.put("playerBlue4", playerBlue4);
        playerBlue4.spritePos(1225, 124);
        playerGreen4 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN), "playerGreen4");
        playerHashMap.put("playerGreen4", playerGreen4);
        playerGreen4.spritePos(1255, 124);
        playerRed4 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED), "playerRed4");
        playerHashMap.put("playerRed4", playerRed4);
        playerRed4.spritePos(1285, 124);
        playerYellow4 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW), "playerYellow4");
        playerHashMap.put("playerYellow4", playerYellow4);
        playerYellow4.spritePos(1315, 124);

        playerBlue5 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE), "playerBlue5");
        playerHashMap.put("playerBlue5", playerBlue5);
        playerBlue5.spritePos(1445, 124);
        playerGreen5 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN), "playerGreen5");
        playerHashMap.put("playerGreen5", playerGreen5);
        playerGreen5.spritePos(1475, 124);
        playerRed5 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_RED), "playerRed5");
        playerHashMap.put("playerRed4", playerRed4);
        playerRed5.spritePos(1505, 124);
        playerYellow5 = new Player(keltis.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW), "playerYellow5");
        playerHashMap.put("playerYellow5", playerYellow5);
        playerYellow5.spritePos(1535, 124);

        stage.addActor(playerBlue1);
        stage.addActor(playerGreen1);
        stage.addActor(playerRed1);
        stage.addActor(playerYellow1);
        stage.addActor(playerBlue2);
        stage.addActor(playerGreen2);
        stage.addActor(playerRed2);
        stage.addActor(playerYellow2);
        stage.addActor(playerBlue3);
        stage.addActor(playerGreen3);
        stage.addActor(playerRed3);
        stage.addActor(playerYellow3);
        stage.addActor(playerBlue4);
        stage.addActor(playerGreen4);
        stage.addActor(playerRed4);
        stage.addActor(playerYellow4);
        stage.addActor(playerBlue5);
        stage.addActor(playerGreen5);
        stage.addActor(playerRed5);
        stage.addActor(playerYellow5);
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
    public void advancePlayer(String player){
        playerHashMap.get(player).moveUp();
    }
}
