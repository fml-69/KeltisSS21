package com.groupd.keltis.scenes.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.accelerometer.ShakeDetector;
import com.groupd.keltis.management.BranchStackStatus;
import com.groupd.keltis.management.RoadcardsStatus;
import com.groupd.keltis.network.NetworkClient;
import com.groupd.keltis.network.events.CardDisplaySyncEvent;
import com.groupd.keltis.network.events.CheatAccuseEvent;
import com.groupd.keltis.network.events.CheatQueryEvent;
import com.groupd.keltis.network.events.NetworkEvent;
import com.groupd.keltis.management.PlayerMove;
import com.groupd.keltis.network.events.NextPlayerEvent;
import com.groupd.keltis.network.events.RoadcardsRemoveSyncEvent;
import com.groupd.keltis.network.events.RoadcardsSyncEvent;
import com.groupd.keltis.network.events.TurnEvent;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.scenes.board.actors.Card;
import com.groupd.keltis.scenes.board.actors.CardDisplay;
import com.groupd.keltis.scenes.board.actors.Figure;
import com.groupd.keltis.scenes.board.actors.IngameMenuButton;
import com.groupd.keltis.scenes.board.road_cards.Position;
import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.RoadcardsList;
import com.groupd.keltis.utils.AssetPaths;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.utils.BranchStackToJson;
import com.groupd.keltis.utils.ObjectToJson;
import com.groupd.keltis.utils.PositioningConstants;
import com.groupd.keltis.utils.RoadcardsToJson;
import com.groupd.keltis.utils.StringToJson;

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

    private String greenStackTop = "";
    private String yellowStackTop = "";
    private String redStackTop = "";
    private String blueStackTop = "";
    private String purpleStackTop = "";

    public CardDisplay branchStackGreen;
    public CardDisplay branchStackYellow;
    public CardDisplay branchStackRed;
    public CardDisplay branchStackBlue;
    public CardDisplay branchStackPurple;

    private String lastPlayerNick = "";

    private ArrayList<Card> branchCards = new ArrayList<>();

    private boolean sent = false;
    private boolean set = true;

    private boolean isCheatingDialogShowing = false;

    private static ArrayList<CardDisplay> handCardDisplayList = new ArrayList<>();

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

                                // send cheat event to server
                                CheatAccuseEvent cheatAccuseEvent = new CheatAccuseEvent();
                                cheatAccuseEvent.setAccuser(NetworkClient.INSTANCE.getNickName());
                                NetworkClient.INSTANCE.sendEvent(cheatAccuseEvent);
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
        NetworkClient.INSTANCE.receiveEvents();
        if (keltis.gameLogic.verifyEndingCondition()) {
            //Gdx.app.exit();
        }
        setTextOfScore();

        stage.draw();
        checkShaking(player);
    }
    public void setTextOfScore(){
        switch (keltis.gameLogic.getPlayerArrayList().size()){
            case 4:
                player4.setText(keltis.gameLogic.getPlayerArrayList().get(3).getNick() + ": " + keltis.gameLogic.getPlayerArrayList().get(3).getOverallScore());
            case 3:
                player3.setText(keltis.gameLogic.getPlayerArrayList().get(2).getNick() + ": " + keltis.gameLogic.getPlayerArrayList().get(2).getOverallScore());
            case 2:
                player2.setText(keltis.gameLogic.getPlayerArrayList().get(1).getNick() + ": " + keltis.gameLogic.getPlayerArrayList().get(1).getOverallScore());
            case 1:
                player1.setText(keltis.gameLogic.getPlayerArrayList().get(0).getNick() + ": " + keltis.gameLogic.getPlayerArrayList().get(0).getOverallScore());
            default:
        }
    }

    public void showDialog(Dialog dialog, Stage stage, float scale) {
        dialog.show(stage);
        dialog.setScale(scale);
        dialog.setOrigin(Align.center);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        state = State.RUN;

        stage.addActor(board);
        stage.addActor(branches);

        for (Roadcards roadcards : roadcardsList.getRoadcardsArrayList()) {
            stage.addActor(roadcards);
        }


        initializeFiguresOnBoard();


        playerOverview();


        branchStackGreen = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_GREEN), "branchStackGreen", "green", false, false);
        branchStackGreen.spritePos(PositioningConstants.CARD_BRANCHSTACK_GREEN.x, PositioningConstants.CARD_BRANCHSTACK_GREEN.y);
        stage.addActor(branchStackGreen);

        branchStackYellow = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_YELLOW), "branchStackYellow", "yellow", false, false);
        branchStackYellow.spritePos(PositioningConstants.CARD_BRANCHSTACK_YELLOW.x, PositioningConstants.CARD_BRANCHSTACK_YELLOW.y);
        stage.addActor(branchStackYellow);

        branchStackRed = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_RED), "branchStackRed", "red", false, false);
        branchStackRed.spritePos(PositioningConstants.CARD_BRANCHSTACK_RED.x, PositioningConstants.CARD_BRANCHSTACK_RED.y);
        stage.addActor(branchStackRed);

        branchStackBlue = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_BLUE), "branchStackBlue", "blue", false, false);
        branchStackBlue.spritePos(PositioningConstants.CARD_BRANCHSTACK_BLUE.x, PositioningConstants.CARD_BRANCHSTACK_BLUE.y);
        stage.addActor(branchStackBlue);

        branchStackPurple = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_PURPLE), "branchStackPurple", "purple", false, false);
        branchStackPurple.spritePos(PositioningConstants.CARD_BRANCHSTACK_PURPLE.x, PositioningConstants.CARD_BRANCHSTACK_PURPLE.y);
        stage.addActor(branchStackPurple);

        CardDisplay drawStack = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "drawStack", "", false, true);
        drawStack.spritePos(PositioningConstants.CARD_DRAWSTACK.x, PositioningConstants.CARD_DRAWSTACK.y);
        stage.addActor(drawStack);

        stage.addActor(hudBar);


        //Menu button on board
        IngameMenuButton button = new IngameMenuButton(keltis, keltis.assetManager.get(AssetPaths.BOARD_MENU_BUTTON));
        stage.addActor(button.getButton());

        //handcards

        CardDisplay handCard1 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard1", "", true, false);
        handCard1.spritePos(PositioningConstants.CARD_HANDCARD_1.x, PositioningConstants.CARD_HANDCARD_1.y);
        handCardDisplayList.add(handCard1);
        stage.addActor(handCard1);

        CardDisplay handCard2 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard2", "", true, false);
        handCard2.spritePos(PositioningConstants.CARD_HANDCARD_2.x, PositioningConstants.CARD_HANDCARD_2.y);
        handCardDisplayList.add(handCard2);
        stage.addActor(handCard2);

        CardDisplay handCard3 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard3", "", true, false);
        handCard3.spritePos(PositioningConstants.CARD_HANDCARD_3.x, PositioningConstants.CARD_HANDCARD_3.y);
        handCardDisplayList.add(handCard3);
        stage.addActor(handCard3);

        CardDisplay handCard4 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard4", "", true, false);
        handCard4.spritePos(PositioningConstants.CARD_HANDCARD_4.x, PositioningConstants.CARD_HANDCARD_4.y);
        handCardDisplayList.add(handCard4);
        stage.addActor(handCard4);

        CardDisplay handCard5 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard5", "", true, false);
        handCard5.spritePos(PositioningConstants.CARD_HANDCARD_5.x, PositioningConstants.CARD_HANDCARD_5.y);
        handCardDisplayList.add(handCard5);
        stage.addActor(handCard5);

        CardDisplay handCard6 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard6", "", true, false);
        handCard6.spritePos(PositioningConstants.CARD_HANDCARD_6.x, PositioningConstants.CARD_HANDCARD_6.y);
        handCardDisplayList.add(handCard6);
        stage.addActor(handCard6);

        CardDisplay handCard7 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard7", "", true, false);
        handCard7.spritePos(PositioningConstants.CARD_HANDCARD_7.x, PositioningConstants.CARD_HANDCARD_7.y);
        handCardDisplayList.add(handCard7);
        stage.addActor(handCard7);

        CardDisplay handCard8 = new CardDisplay(keltis, keltis.assetManager.get(AssetPaths.CARD_BACK), "handCard8", "", true, false);
        handCard8.spritePos(PositioningConstants.CARD_HANDCARD_8.x, PositioningConstants.CARD_HANDCARD_8.y);
        handCardDisplayList.add(handCard8);
        stage.addActor(handCard8);

        for(CardDisplay cardDisplay: handCardDisplayList){
            if(cardDisplay.isEmpty()){
                cardDisplay.setCard(keltis.gameLogic.getDrawPile().remove(keltis.gameLogic.getDrawPile().size() - 1));
            }
        }
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

    public void setUpGame(int playerCount){
        // TODO: 07.06.2021 Hier Spiel aufsetzen
    }

    public void initializeFiguresOnBoard() {
        switch (keltis.gameLogic.getPlayerArrayList().size()) {
            case 4:
                playerHashMap.putAll(keltis.gameLogic.getPlayerArrayList().get(3).getFigures());
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
            case 3:
                playerHashMap.putAll(keltis.gameLogic.getPlayerArrayList().get(2).getFigures());
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
            case 2:
                playerHashMap.putAll(keltis.gameLogic.getPlayerArrayList().get(1).getFigures());
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
            case 1:
                playerHashMap.putAll(keltis.gameLogic.getPlayerArrayList().get(0).getFigures());
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
                break;
            default:
        }

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
                player2 = playerLabel(keltis.gameLogic.getPlayerArrayList().get(1),300 , 825);
                stage.addActor(player2);
            case 1:
                playerPicture1.setPosition(50, 900);
                stage.addActor(playerPicture1);
                player1 = playerLabel(keltis.gameLogic.getPlayerArrayList().get(0), 50, 825);
                stage.addActor(player1);
                break;
            default:
        }
    }


    public Label playerLabel(Player player, int x, int y) {
        Label label = new Label(player.getNick() + ": " + keltis.gameLogic.getScoreOfPlayer(player), new Skin(Gdx.files.internal("skin_shade/uiskin.json")));
        label.setWidth(200);
        label.setHeight(100);
        label.setFontScale(2);
        switch (player.getColor()){
            case BLUE:
                label.setColor(Color.BLUE);
                break;
            case RED:
                label.setColor(Color.RED);
                break;
            case YELLOW:
                label.setColor(Color.YELLOW);
                break;
            case GREEN:
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

    public static ArrayList<CardDisplay> getHandCardDisplayList() {
        return handCardDisplayList;
    }

    @Override
    public void onNetworkEvent(NetworkEvent event) {
        if(event instanceof TurnEvent){
            Gdx.app.log("NETWORK", "TURN RECEIVED");
            PlayerMove playerMove = ObjectToJson.convertToObject(((TurnEvent) event).getJson());
            lastPlayerNick = playerMove.getNick();
            keltis.gameLogic.playCard(keltis.gameLogic.getPlayer(playerMove.getNick()), (Card) keltis.cardHelper.getCardHashmap().get(playerMove.getCardName()), keltis.gameLogic.getPileColor(playerMove.getColor()));
            if(!keltis.gameLogic.getPlayer(playerMove.getNick()).greenEmpty()){
                branchStackGreen.setCard(keltis.gameLogic.getPlayer(playerMove.getNick()).getLastGreen());
            }
            if(!keltis.gameLogic.getPlayer(playerMove.getNick()).yellowEmpty()){
                branchStackYellow.setCard(keltis.gameLogic.getPlayer(playerMove.getNick()).getLastYellow());
            }
            if(!keltis.gameLogic.getPlayer(playerMove.getNick()).redEmpty()){
                branchStackRed.setCard(keltis.gameLogic.getPlayer(playerMove.getNick()).getLastRed());
            }
            if(!keltis.gameLogic.getPlayer(playerMove.getNick()).blueEmpty()){
                branchStackBlue.setCard(keltis.gameLogic.getPlayer(playerMove.getNick()).getLastBlue());
            }
            if(!keltis.gameLogic.getPlayer(playerMove.getNick()).purpleEmpty()){
                branchStackPurple.setCard(keltis.gameLogic.getPlayer(playerMove.getNick()).getLastPurple());
            }
        }
        else if(event instanceof CheatQueryEvent){
            showDialog(new InfoDialog("Schummelverdacht",
                    keltis.assetManager.get(AssetPaths.DIALOG_SKIN),((CheatQueryEvent) event).message),stage, 3);
        }
        else if(event instanceof CardDisplaySyncEvent){
            Gdx.app.log("NETWORK", "RECEIVED CARD STATUS");
            set = false;
            BranchStackStatus branchStackStatus = BranchStackToJson.convertToObject(((CardDisplaySyncEvent) event).getJson());
            branchCards.add((Card) keltis.cardHelper.getCardHashmap().get(branchStackStatus.getGreen()));
            branchCards.add((Card) keltis.cardHelper.getCardHashmap().get(branchStackStatus.getYellow()));
            branchCards.add((Card) keltis.cardHelper.getCardHashmap().get(branchStackStatus.getRed()));
            branchCards.add((Card) keltis.cardHelper.getCardHashmap().get(branchStackStatus.getBlue()));
            branchCards.add((Card) keltis.cardHelper.getCardHashmap().get(branchStackStatus.getPurple()));
        } else if(event instanceof NextPlayerEvent){
            keltis.gameLogic.setTurnPlayer(keltis.gameLogic.getPlayer(lastPlayerNick));
            if(!keltis.gameLogic.getCurrentPlayer().greenEmpty()){
                branchStackGreen.setCard(keltis.gameLogic.getCurrentPlayer().getLastGreen());
            } else{
                branchStackGreen.setCard(new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_GREEN), "emptyGreen", "", -1));
            }
            if(!keltis.gameLogic.getCurrentPlayer().yellowEmpty()){
                branchStackYellow.setCard(keltis.gameLogic.getCurrentPlayer().getLastYellow());
            } else{
                branchStackYellow.setCard(new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_YELLOW), "emptyYellow", "", -1));
            }
            if(!keltis.gameLogic.getCurrentPlayer().redEmpty()){
                branchStackRed.setCard(keltis.gameLogic.getCurrentPlayer().getLastRed());
            } else{
                branchStackRed.setCard(new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_RED), "emptyRed", "", -1));
            }
            if(!keltis.gameLogic.getCurrentPlayer().blueEmpty()){
                branchStackBlue.setCard(keltis.gameLogic.getCurrentPlayer().getLastBlue());
            } else{
                branchStackBlue.setCard(new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_BLUE), "emptyBlue", "", -1));
            }
            if(!keltis.gameLogic.getCurrentPlayer().purpleEmpty()){
                branchStackPurple.setCard(keltis.gameLogic.getCurrentPlayer().getLastPurple());
            } else{
                branchStackPurple.setCard(new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_STACK_PURPLE), "emptyPurple", "", -1));
            }
        } else if(event instanceof RoadcardsSyncEvent){
            ArrayList<RoadcardsStatus> roadcardsStatusArrayList = RoadcardsToJson.convertToObject(((RoadcardsSyncEvent) event).getJson());
            ArrayList<Position> positionArrayList = new ArrayList<>();

            int i = 0;
            for(RoadcardsStatus roadcardsStatus:roadcardsStatusArrayList){
                positionArrayList.add(keltis.positionHelper.getPositionHashMap().get(roadcardsStatus.getPosition()));
                i++;
            }
            roadcardsList.addRoadcards(keltis,positionArrayList);
            for (Roadcards roadcards : roadcardsList.getRoadcardsArrayList()) {
                stage.addActor(roadcards);
            }
        } else if(event instanceof RoadcardsRemoveSyncEvent){
            String roadcardName = StringToJson.convertToObject(((RoadcardsRemoveSyncEvent) event).getJson());
            Gdx.app.log(roadcardName,"warum");
            for(Roadcards roadcards:roadcardsList.getRoadcardsArrayList()){
                if(roadcards.getName().equals(roadcardName)){
                    Gdx.app.log("ttttttt","dedfewf");
                    roadcards.addAction(Actions.removeActor());
                }
            }
        }

    }

    public void setRoadcardsList(RoadcardsList roadcardsList) {
        this.roadcardsList = roadcardsList;
    }
}
