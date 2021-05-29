package com.groupd.keltis.scenes.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.accelerometer.ShakeDetector;
import com.groupd.keltis.management.GameLogic;
import com.groupd.keltis.scenes.AbstractScene;

import com.groupd.keltis.scenes.board.actors.Card;
import com.groupd.keltis.scenes.board.actors.CardDisplay;
import com.groupd.keltis.scenes.board.actors.Figure;

import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.RoadcardsList;

import com.groupd.keltis.utils.AssetPaths;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.utils.PositioningConstants;

import java.util.ArrayList;


import java.util.HashMap;


public class Board extends AbstractScene {

    private OrthographicCamera camera;

    private static CardDisplay highlightedCardDisplay;
    public enum State
        {
        RUN,
        PAUSE
    }

    private final Image board;
    private final Image branches;
    private final Image hudBar;

    private State state;

    private ArrayList<Player> player = new ArrayList<>();
    private HashMap<String, Figure> playerHashMap = new HashMap<>();
    private int x = 1;


    private Label player1;
    private Label player2;
    private Label player3;
    private Label player4;


    private Image playerPicture1;
    private Image playerPicture2;
    private Image playerPicture3;
    private Image playerPicture4;
    private RoadcardsList roadcardsList = new RoadcardsList();
    private ShamrockDialog shamrockDialog;

    private boolean isCheatingDialogShowing = false;

    public Board(final Keltis keltis) {
        super(keltis);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT);
        this.stage = new Stage(new StretchViewport(Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT, camera));
        keltis.batch.setProjectionMatrix(camera.combined);


        board = new Image((Texture) keltis.assetManager.get(AssetPaths.BOARD_BACKGROUND));
        branches = new Image((Texture) keltis.assetManager.get(AssetPaths.BOARD_BRANCHES));
        hudBar = new Image((Texture) keltis.assetManager.get(AssetPaths.BOARD_HUD_BAR));

        playerPicture1 = new Image((Texture) keltis.assetManager.get(AssetPaths.PLAYER_PICTURE));
        playerPicture2 = new Image((Texture) keltis.assetManager.get(AssetPaths.PLAYER_PICTURE));
        playerPicture3 = new Image((Texture) keltis.assetManager.get(AssetPaths.PLAYER_PICTURE));
        playerPicture4 = new Image((Texture) keltis.assetManager.get(AssetPaths.PLAYER_PICTURE));

        //GameLogic setDrawPile
        keltis.gameLogic.setPlayerArrayList(player);
        keltis.gameLogic.setRoadCardsList(roadcardsList.getRoadcardsArrayList());



        shamrockDialog = new ShamrockDialog("Herzlichen Glückwunsch!", keltis.assetManager.get(AssetPaths.DIALOG_SKIN,Skin.class));


        //GameLogic setDrawPile

        keltis.gameLogic.setBoard(this);


    }

    @Override
    public void update(float delta) {
        switch (state){
            case RUN:
                stage.act(delta);


                if(x % 180 == 0){
                    keltis.gameLogic.playCard(player.get(0),new Card("blue", 5), "blue");

                    Gdx.app.log("----------------", "-------------------------------");
                }
                if(x % 275 == 0){
                    keltis.gameLogic.playCard(player.get(1),new Card("blue", 6), "red");

                    Gdx.app.log("----------------", "-------------------------------");
                }
                if(x % 350 == 0){
                    keltis.gameLogic.playCard(player.get(2),new Card("yellow", 5), "yellow");


                    Gdx.app.log("----------------", "-------------------------------");
                }
                if(x % 520 == 0) {
                    keltis.gameLogic.playCard(player.get(3), new Card("purple", 6), "green");
                }
                x++;
                break;
            case PAUSE:


                break;
        }

    }

    private void checkShaking(ArrayList<Player> player) {
        if (ShakeDetector.phoneIsShaking() && !isCheatingDialogShowing) {
            isCheatingDialogShowing = true;
            YesNoDialog dialog = new YesNoDialog("Schummelverdacht",
                    keltis.assetManager.get(AssetPaths.DIALOG_SKIN, Skin.class),
                    new YesNoDialog.Callback() {
                        @Override
                        public void result(boolean result) {
                            if (result) {
                                accuseOfCheating();
                            }
                            isCheatingDialogShowing = false;

                        }
                    });

            showDialog(dialog, stage, 3);
        }
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        if (keltis.gameLogic.verifyEndingCondition()) {
            //Gdx.app.exit();
        }
        player1.setText(keltis.gameLogic.getPlayerArrayList().get(0).getName() + ": " + keltis.gameLogic.getPlayerArrayList().get(0).getOverallScore());
        player2.setText(keltis.gameLogic.getPlayerArrayList().get(1).getName() + ": " + keltis.gameLogic.getPlayerArrayList().get(1).getOverallScore());
        player3.setText(keltis.gameLogic.getPlayerArrayList().get(2).getName() + ": " + keltis.gameLogic.getPlayerArrayList().get(2).getOverallScore());
        player4.setText(keltis.gameLogic.getPlayerArrayList().get(3).getName() + ": " + keltis.gameLogic.getPlayerArrayList().get(3).getOverallScore());

        stage.draw();
        checkShaking(player);
    }

    public void showDialog(Dialog dialog, Stage stage, float scale) {
        dialog.show(stage);
        dialog.setScale(scale);
        dialog.setOrigin(Align.center);
    }

    public void accuseOfCheating() {

        InfoDialog infoDialog = new InfoDialog("Schummelverdacht",
                keltis.assetManager.get(AssetPaths.DIALOG_SKIN, Skin.class),
                checkCheat());

        showDialog(infoDialog, stage, 3);
    }

    public boolean checkCheat() {
        for (Player p : player) {
            if (p.getCheat()) {
                if (!p.isHasAccused()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        state = State.RUN;

        stage.addActor(board);
        stage.addActor(branches);

        roadcardsList.assignRoadcards(keltis);
        for (Roadcards roadcards : roadcardsList.getRoadcardsArrayList()) {
            stage.addActor(roadcards);
        }

        Player player1 = new Player(keltis, "player1", "blue");
        player.add(player1);
        playerHashMap.putAll(player1.getFigures());
        playerHashMap.get("blue1").spritePos(565, 124);
        playerHashMap.get("blue2").spritePos(785, 124);
        playerHashMap.get("blue3").spritePos(1005, 124);
        playerHashMap.get("blue4").spritePos(1225, 124);
        playerHashMap.get("blue5").spritePos(1445, 124);
        for (int i = 1; i < 6; i++) {
            for (Figure figure : playerHashMap.values()) {
                if (figure.getName().equals("blue" + i)) {
                    stage.addActor(figure);
                }
            }
        }

        Player player2 = new Player(keltis, "player2", "red");
        player.add(player2);
        playerHashMap.putAll(player2.getFigures());
        playerHashMap.get("red1").spritePos(595, 124);
        playerHashMap.get("red2").spritePos(815, 124);
        playerHashMap.get("red3").spritePos(1035, 124);
        playerHashMap.get("red4").spritePos(1255, 124);
        playerHashMap.get("red5").spritePos(1475, 124);
        for (int i = 1; i < 6; i++) {
            for (Figure figure : playerHashMap.values()) {
                if (figure.getName().equals("red" + i)) {
                    stage.addActor(figure);
                }
            }
        }

        Player player3 = new Player(keltis, "player3", "green");
        player.add(player3);
        playerHashMap.putAll(player3.getFigures());
        playerHashMap.get("green1").spritePos(625, 124);
        playerHashMap.get("green2").spritePos(845, 124);
        playerHashMap.get("green3").spritePos(1065, 124);
        playerHashMap.get("green4").spritePos(1285, 124);
        playerHashMap.get("green5").spritePos(1505, 124);
        for (int i = 1; i < 6; i++) {
            for (Figure figure : playerHashMap.values()) {
                if (figure.getName().equals("green" + i)) {
                    stage.addActor(figure);
                }
            }
        }

        Player player4 = new Player(keltis, "player4", "yellow");
        player.add(player4);
        playerHashMap.putAll(player4.getFigures());
        playerHashMap.get("yellow1").spritePos(655, 124);
        playerHashMap.get("yellow2").spritePos(875, 124);
        playerHashMap.get("yellow3").spritePos(1095, 124);
        playerHashMap.get("yellow4").spritePos(1315, 124);
        playerHashMap.get("yellow5").spritePos(1535, 124);
        for (int i = 1; i < 6; i++) {
            for (Figure figure : playerHashMap.values()) {
                if (figure.getName().equals("yellow" + i)) {
                    stage.addActor(figure);
                }
            }
        }



        CardDisplay branchStackGreen = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_GREEN), "branchStackGreen", "green", false);
        branchStackGreen.spritePos(PositioningConstants.CARD_BRANCHSTACK_GREEN.x, PositioningConstants.CARD_BRANCHSTACK_GREEN.y);
        stage.addActor(branchStackGreen);

        CardDisplay branchStackYellow = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_YELLOW), "branchStackYellow", "yellow", false);
        branchStackYellow.spritePos(PositioningConstants.CARD_BRANCHSTACK_YELLOW.x, PositioningConstants.CARD_BRANCHSTACK_YELLOW.y);
        stage.addActor(branchStackYellow);

        CardDisplay branchStackRed = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_RED), "branchStackRed", "red", false);
        branchStackRed.spritePos(PositioningConstants.CARD_BRANCHSTACK_RED.x, PositioningConstants.CARD_BRANCHSTACK_RED.y);
        stage.addActor(branchStackRed);

        CardDisplay branchStackBlue = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_BLUE), "branchStackBlue", "blue", false);
        branchStackBlue.spritePos(PositioningConstants.CARD_BRANCHSTACK_BLUE.x, PositioningConstants.CARD_BRANCHSTACK_BLUE.y);
        stage.addActor(branchStackBlue);

        CardDisplay branchStackPurple = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_PURPLE), "branchStackPurple", "purple", false);
        branchStackPurple.spritePos(PositioningConstants.CARD_BRANCHSTACK_PURPLE.x, PositioningConstants.CARD_BRANCHSTACK_PURPLE.y);
        stage.addActor(branchStackPurple);


        CardDisplay publicStackGreen = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_GREEN), "publicStackGreen", "green", false);
        publicStackGreen.spritePos(PositioningConstants.CARD_PUBLICSTACK_GREEN.x, PositioningConstants.CARD_PUBLICSTACK_GREEN.y);
        stage.addActor(publicStackGreen);

        CardDisplay publicStackYellow = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_YELLOW), "publicStackYellow", "yellow", false);
        publicStackYellow.spritePos(PositioningConstants.CARD_PUBLICSTACK_YELLOW.x, PositioningConstants.CARD_PUBLICSTACK_YELLOW.y);
        stage.addActor(publicStackYellow);

        CardDisplay publicStackRed = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_RED), "publicStackRed", "red", false);
        publicStackRed.spritePos(PositioningConstants.CARD_PUBLICSTACK_RED.x, PositioningConstants.CARD_PUBLICSTACK_RED.y);
        stage.addActor(publicStackRed);

        CardDisplay publicStackBlue = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_BLUE), "publicStackBlue", "blue", false);
        publicStackBlue.spritePos(PositioningConstants.CARD_PUBLICSTACK_BLUE.x, PositioningConstants.CARD_PUBLICSTACK_BLUE.y);
        stage.addActor(publicStackBlue);

        CardDisplay publicStackPurple = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_PURPLE), "publicStackPurple", "purple", false);
        publicStackPurple.spritePos(PositioningConstants.CARD_PUBLICSTACK_PURPLE.x, PositioningConstants.CARD_PUBLICSTACK_PURPLE.y);
        stage.addActor(publicStackPurple);

        CardDisplay drawStack = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "drawStack", "", false);
        drawStack.spritePos(PositioningConstants.CARD_DRAWSTACK.x, PositioningConstants.CARD_DRAWSTACK.y);
        stage.addActor(drawStack);

        stage.addActor(hudBar);

        //handcards

        CardDisplay handCard1 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard1", "", true);
        handCard1.spritePos(PositioningConstants.CARD_HANDCARD_1.x, PositioningConstants.CARD_HANDCARD_1.y);
        stage.addActor(handCard1);

        CardDisplay handCard2 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard2", "", true);
        handCard2.spritePos(PositioningConstants.CARD_HANDCARD_2.x, PositioningConstants.CARD_HANDCARD_2.y);
        stage.addActor(handCard2);

        CardDisplay handCard3 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard3", "", true);
        handCard3.spritePos(PositioningConstants.CARD_HANDCARD_3.x, PositioningConstants.CARD_HANDCARD_3.y);
        stage.addActor(handCard3);

        CardDisplay handCard4 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard4", "", true);
        handCard4.spritePos(PositioningConstants.CARD_HANDCARD_4.x, PositioningConstants.CARD_HANDCARD_4.y);
        stage.addActor(handCard4);

        CardDisplay handCard5 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard5", "", true);
        handCard5.spritePos(PositioningConstants.CARD_HANDCARD_5.x, PositioningConstants.CARD_HANDCARD_5.y);
        stage.addActor(handCard5);

        CardDisplay handCard6 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard6", "", true);
        handCard6.spritePos(PositioningConstants.CARD_HANDCARD_6.x, PositioningConstants.CARD_HANDCARD_6.y);
        stage.addActor(handCard6);

        CardDisplay handCard7 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard7", "", true);
        handCard7.spritePos(PositioningConstants.CARD_HANDCARD_7.x, PositioningConstants.CARD_HANDCARD_7.y);
        stage.addActor(handCard7);

        CardDisplay handCard8 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard8", "", true);
        handCard8.spritePos(PositioningConstants.CARD_HANDCARD_8.x, PositioningConstants.CARD_HANDCARD_8.y);
        stage.addActor(handCard8);

        //this is a test!!
        handCard1.setCard(new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_FIVE), "blueFive", "blue", 5));

        playerOverview();


    }

    @Override
    public void pause() {
        this.state = State.PAUSE;
    }

    @Override
    public void resume() {
        this.state = State.RUN;
    }

    @Override
    public void hide() {

    }

    public void playerOverview() {
        switch (keltis.gameLogic.getPlayerArrayList().size()) {
            case 4:
                playerPicture4.setPosition(300, 675);
                stage.addActor(playerPicture4);
                player4 = playerLabel(keltis.gameLogic.getPlayerArrayList().get(3), 300, 600);
                stage.addActor(player4);
            case 3:
                playerPicture3.setPosition(50, 675);
                stage.addActor(playerPicture3);
                player3 = playerLabel(keltis.gameLogic.getPlayerArrayList().get(2), 50, 600);
                stage.addActor(player3);
            case 2:
                playerPicture2.setPosition(300, 900);
                stage.addActor(playerPicture2);
                playerPicture1.setPosition(50, 900);
                stage.addActor(playerPicture1);
                player2 = playerLabel(keltis.gameLogic.getPlayerArrayList().get(1),300 , 825);
                stage.addActor(player2);
                player1 = playerLabel(keltis.gameLogic.getPlayerArrayList().get(0), 50, 825);
                stage.addActor(player1);
                break;
            default:
        }
    }


    public Label playerLabel(Player player, int x, int y) {
        Label label = new Label(player.getName() + ": " + keltis.gameLogic.getScoreOfPlayer(player), new Skin(Gdx.files.internal("skin_shade/uiskin.json")));
        label.setWidth(200);
        label.setHeight(100);
        label.setFontScale(2);
        switch (player.getColor()){
            case "blue":
                label.setColor(Color.BLUE);
                break;
            case "red":
                label.setColor(Color.RED);
                break;
            case "yellow":
                label.setColor(Color.YELLOW);
                break;
            case "green":
                label.setColor(Color.GREEN);
                break;
            default:
        }
        label.setPosition(x, y);
        return label;
    }

    public void advanceFigure(String figure) {
        playerHashMap.get(figure).moveUp();
    }


    

    public ShamrockDialog getShamrockDialog() {
        return shamrockDialog;
    }

    public static void setHighlightedCardDisplay(CardDisplay cardDisplay){

        highlightedCardDisplay = cardDisplay;
    }

    public static CardDisplay getHighlightedCardDisplay() {
        return highlightedCardDisplay;
    }
}
