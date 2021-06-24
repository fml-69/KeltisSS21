package com.groupd.keltis.scenes.board.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.network.NetworkClient;
import com.groupd.keltis.network.events.NextPlayerEvent;
import com.groupd.keltis.scenes.board.Board;
import com.groupd.keltis.utils.AssetPaths;
import com.groupd.keltis.utils.ColorPile;

public class CardDisplay extends Actor {

    private final Keltis keltis;
    private Sprite sprite;
    private String name;
    private String color;
    private int value;
    private Card currentCard = null;
    private Texture emptyHandcardTexture;
    private final CardDisplay itsaMe = this;
    private final Texture highlightTexture;
    private final Sprite highlightPlayableTexture;
    private final boolean isHandCard;
    private final boolean isDrawPile;
    private boolean isEmpty = true;
    private boolean countUp = true;
    private float counter;
    private ColorPile colorPile;

    public CardDisplay(Keltis keltis, Texture texture, final String name, String color, boolean isHandCard, boolean isDrawPile){
        this.keltis = keltis;
        this.highlightTexture = keltis.assetManager.get(AssetPaths.CARD_HIGHLIGHT);
        this.highlightPlayableTexture = new Sprite((Texture) keltis.assetManager.get(AssetPaths.CARD_HIGHLIGHT_PLAYABLE));
        this.isHandCard = isHandCard;
        this.isDrawPile = isDrawPile;
        if(isHandCard){
            this.emptyHandcardTexture = keltis.assetManager.get(AssetPaths.CARD_EMPTY_HANDCARD);
            sprite = new Sprite(emptyHandcardTexture);
        } else{
            this.color = color;
            sprite = new Sprite(texture);
        }
        switch (color){
            case "green":
                this.colorPile = ColorPile.GREEN;
                break;
            case "yellow":
                this.colorPile = ColorPile.YELLOW;
                break;
            case "red":
                this.colorPile = ColorPile.RED;
                break;
            case "blue":
                this.colorPile = ColorPile.BLUE;
                break;
            case "purple":
                this.colorPile = ColorPile.PURPLE;
                break;
        }
        this.name = name;
        spritePos(sprite.getX(), sprite.getY());

        setTouchable(Touchable.enabled);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(Board.getHighlightedCardDisplay()!=null
                        && Board.getHighlightedCardDisplay().getCard()!=null
                        && Board.getHighlightedCardDisplay().getCard().getCardColor().equals(color)
                        && Board.getHighlightedCardDisplay().isHandCard
                        && keltis.gameLogic.getPlayer(keltis.gameLogic.getPlayerNick()).getTurn()
                        && !Board.getHighlightedCardDisplay().isDrawPile
                        && keltis.gameLogic.isAllowPlay()
                ){
                    keltis.getPlayCard().play();
                    Gdx.input.vibrate(50);
                    setCard(Board.getHighlightedCardDisplay().getCard());
                    Board.getHighlightedCardDisplay().cardTaken();
                    Board.setHighlightedCardDisplay(null);
                    keltis.gameLogic.setAllowPlay(false);
                    keltis.gameLogic.sendTurnEvent(keltis.gameLogic.getPlayer(keltis.gameLogic.getPlayerNick()), currentCard, colorPile);
                } else if(isDrawPile
                        && keltis.gameLogic.getPlayer(keltis.gameLogic.getPlayerNick()).getTurn()
                        && keltis.gameLogic.isAllowDraw()){
                    keltis.getPlayCard().play();
                    Gdx.input.vibrate(50);
                    Board.setHighlightedCardDisplay(null);
                    keltis.gameLogic.drawCard(keltis.gameLogic.getPlayer(keltis.gameLogic.getPlayerNick()));
                    NetworkClient client = NetworkClient.INSTANCE;
                    NextPlayerEvent nextPlayerEvent = new NextPlayerEvent();
                    client.sendEvent(nextPlayerEvent);
                } else{
                    Gdx.input.vibrate(50);
                    keltis.getSelectCard().play();
                    Board.setHighlightedCardDisplay(itsaMe);
                }
                Gdx.app.log("CARDTOUCH: Touch down asset with name ", name);
                return true;
            }
        });
    }

    public void spritePos(float x, float y){
        sprite.setPosition(x, y);
        highlightPlayableTexture.setPosition(x-15,y-15);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(keltis.gameLogic.getPlayer(keltis.gameLogic.getPlayerNick()).getTurn()
                &&!isDrawPile
                &&!isHandCard
                &&Board.getHighlightedCardDisplay()!=null
                &&!keltis.gameLogic.isAllowDraw()){
            if(Board.getHighlightedCardDisplay().isHandCard) {
                highlightPlayableTexture.draw(batch, counter/100);
                if (countUp) {
                    counter++;
                } else {
                    counter--;
                }
                if (counter == 100) {
                    countUp = false;
                }
                if (counter == 40) {
                    countUp = true;
                }
            }
        }
        if(keltis.gameLogic.getPlayer(keltis.gameLogic.getPlayerNick()).getTurn()
                &&isDrawPile
                &&keltis.gameLogic.isAllowDraw()){
            highlightPlayableTexture.draw(batch, counter/100);
            if(countUp){
                counter++;
            } else{
                counter--;
            }
            if(counter == 100){
                countUp = false;
            }
            if(counter==40){
                countUp = true;
            }
        }
        if(keltis.gameLogic.getPlayer(keltis.gameLogic.getPlayerNick()).getTurn()
                &&isHandCard
                &&Board.getHighlightedCardDisplay()!=null
                &&keltis.gameLogic.isAllowPlay()){
            if(!Board.getHighlightedCardDisplay().isHandCard) {
                highlightPlayableTexture.draw(batch, counter/100);
                if (countUp) {
                    counter++;
                } else {
                    counter--;
                }
                if (counter == 100) {
                    countUp = false;
                }
                if (counter == 40) {
                    countUp = true;
                }
            }
        }
        if(keltis.gameLogic.getPlayer(keltis.gameLogic.getPlayerNick()).getTurn()
                &&isHandCard
                &&Board.getHighlightedCardDisplay()==null
                &&keltis.gameLogic.isAllowPlay()){
            highlightPlayableTexture.draw(batch, counter/100);
            if (countUp) {
                counter++;
            } else {
                counter--;
            }
            if (counter == 100) {
                countUp = false;
            }
            if (counter == 40) {
                countUp = true;
            }
        }
        if(Board.getHighlightedCardDisplay()==this){
            batch.draw(highlightTexture, sprite.getX(), sprite.getY());
        }
        sprite.draw(batch);
    }

    public String getName(){
        return this.name;
    }

    public void setCard(Card card){
        this.value = card.getNumber();
        this.currentCard = card;
        this.color = card.getCardColor();
        setSprite(card.getTexture());
        isEmpty = false;
    }

    public void setSprite(Texture texture){
        float x = sprite.getX();
        float y = sprite.getY();
        sprite = new Sprite(texture);
        spritePos(x, y);
    }

    public Card getCard(){
        return currentCard;
    }

    public void cardTaken(){
        if(isHandCard) {
            setSprite(emptyHandcardTexture);
        }
        isEmpty = true;
    }

    public boolean isEmpty(){
        return isEmpty;
    }

}