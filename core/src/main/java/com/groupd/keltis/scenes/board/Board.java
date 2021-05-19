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
import com.groupd.keltis.management.GameLogic;
import com.groupd.keltis.scenes.AbstractScene;

import com.groupd.keltis.scenes.board.actors.Card;
import com.groupd.keltis.scenes.board.actors.Figure;

import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.RoadcardsList;

import com.groupd.keltis.utils.AssetPaths;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.utils.PositioningConstants;

import java.util.ArrayList;


import java.util.Collection;

import java.util.HashMap;
import java.util.List;

public class Board extends AbstractScene {

    private OrthographicCamera camera;

    private final Image board;
    private final Image branches;
    private final Image hudBar;

    private HashMap<String, Player> playerHashMap = new HashMap<>();
    private HashMap<String, Figure> figuresHashMap = new HashMap<>();

    private int x = 1;

    private final GameLogic gameLogic = new GameLogic();

    private RoadcardsList roadcardsList = new RoadcardsList();

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

        //GameLogic setDrawPile
        gameLogic.setPlayerArrayList(player);
        gameLogic.setRoadCardsList(roadcardsList.getRoadcardsArrayList());
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

        if(x % 180 == 0){
            gameLogic.playCard(player.get(0),new Card("blue", 5), "blue");

            Gdx.app.log("----------------", "-------------------------------");
        }
        if(x % 275 == 0){
            gameLogic.playCard(player.get(1),new Card("blue", 6), "red");

            Gdx.app.log("----------------", "-------------------------------");
        }
        if(x % 350 == 0){
            gameLogic.playCard(player.get(2),new Card("yellow", 5), "yellow");

            Gdx.app.log("----------------", "-------------------------------");
        }
        if(x % 520 == 0){
            gameLogic.playCard(player.get(3),new Card("purple", 6), "green");

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
        Gdx.app.log("Spieler1 Punkte: ",  String.valueOf(player.get(0).getOverallScore()));
        Gdx.app.log("Spieler2 Punkte: ",  String.valueOf(player.get(1).getOverallScore()));
        Gdx.app.log("----------------", "-------------------------------");

        if(gameLogic.verifyEndingCondition()){
            //Gdx.app.exit();
        }
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

        Card emptyBranchStackGreen = new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_GREEN), "emptyBranchStackGreen", -1);
        emptyBranchStackGreen.spritePos(PositioningConstants.CARD_BRANCHSTACK_GREEN.x, PositioningConstants.CARD_BRANCHSTACK_GREEN.y);
        stage.addActor(emptyBranchStackGreen);

        Card emptyBranchStackYellow = new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_YELLOW), "emptyBranchStackYellow", -1);
        emptyBranchStackYellow.spritePos(PositioningConstants.CARD_BRANCHSTACK_YELLOW.x, PositioningConstants.CARD_BRANCHSTACK_YELLOW.y);
        stage.addActor(emptyBranchStackYellow);

        Card emptyBranchStackRed = new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_RED), "emptyBranchStackRed", -1);
        emptyBranchStackRed.spritePos(PositioningConstants.CARD_BRANCHSTACK_RED.x, PositioningConstants.CARD_BRANCHSTACK_RED.y);
        stage.addActor(emptyBranchStackRed);

        Card emptyBranchStackBlue = new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_BLUE), "emptyBranchStackBlue", -1);
        emptyBranchStackBlue.spritePos(PositioningConstants.CARD_BRANCHSTACK_BLUE.x, PositioningConstants.CARD_BRANCHSTACK_BLUE.y);
        stage.addActor(emptyBranchStackBlue);

        Card emptyBranchStackPurple = new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_PURPLE), "emptyBranchStackPurple", -1);
        emptyBranchStackPurple.spritePos(PositioningConstants.CARD_BRANCHSTACK_PURPLE.x, PositioningConstants.CARD_BRANCHSTACK_PURPLE.y);
        stage.addActor(emptyBranchStackPurple);



        Card emptyPublicStackGreen = new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_GREEN), "emptyPublicStackGreen", -1);
        emptyPublicStackGreen.spritePos(PositioningConstants.CARD_PUBLICSTACK_GREEN.x, PositioningConstants.CARD_PUBLICSTACK_GREEN.y);
        stage.addActor(emptyPublicStackGreen);

        Card emptyPublicStackYellow = new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_YELLOW), "emptyPublicStackYellow", -1);
        emptyPublicStackYellow.spritePos(PositioningConstants.CARD_PUBLICSTACK_YELLOW.x, PositioningConstants.CARD_PUBLICSTACK_YELLOW.y);
        stage.addActor(emptyPublicStackYellow);

        Card emptyPublicStackRed = new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_RED), "emptyPublicStackRed", -1);
        emptyPublicStackRed.spritePos(PositioningConstants.CARD_PUBLICSTACK_RED.x, PositioningConstants.CARD_PUBLICSTACK_RED.y);
        stage.addActor(emptyPublicStackRed);

        Card emptyPublicStackBlue = new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_BLUE), "emptyPublicStackBlue", -1);
        emptyPublicStackBlue.spritePos(PositioningConstants.CARD_PUBLICSTACK_BLUE.x, PositioningConstants.CARD_PUBLICSTACK_BLUE.y);
        stage.addActor(emptyPublicStackBlue);

        Card emptyPublicStackPurple = new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_PURPLE), "emptyPublicStackPurple", -1);
        emptyPublicStackPurple.spritePos(PositioningConstants.CARD_PUBLICSTACK_PURPLE.x, PositioningConstants.CARD_PUBLICSTACK_PURPLE.y);
        stage.addActor(emptyPublicStackPurple);

        Card drawStack = new Card(keltis.assetManager.get(AssetPaths.CARD_BACK), "drawStack", -1);
        drawStack.spritePos(PositioningConstants.CARD_DRAWSTACK.x, PositioningConstants.CARD_DRAWSTACK.y);
        stage.addActor(drawStack);

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
