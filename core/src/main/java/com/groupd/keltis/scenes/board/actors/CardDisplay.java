package com.groupd.keltis.scenes.board.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.scenes.board.Board;
import com.groupd.keltis.utils.AssetPaths;
import com.groupd.keltis.utils.ColorPile;

public class CardDisplay extends Actor {

    private Sprite sprite;
    private String name;
    private String color;
    private int value;
    private Card currentCard = null;
    private Texture emptyHandcardTexture;
    private final CardDisplay itsaMe = this;
    private final Texture highlightTexture;
    private final boolean isHandCard;
    private ColorPile colorPile;

    public CardDisplay(Keltis keltis, Texture texture, final String name, String color, boolean isHandCard){
        this.highlightTexture = keltis.assetManager.get(AssetPaths.CARD_HIGHLIGHT);
        this.isHandCard = isHandCard;
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
                ){
                    setCard(Board.getHighlightedCardDisplay().getCard());
                    Board.getHighlightedCardDisplay().cardTaken();
                    Board.setHighlightedCardDisplay(null);
                    keltis.gameLogic.sendTurnEvent(keltis.gameLogic.getPlayer(keltis.gameLogic.getPlayerNick()), currentCard, colorPile);
                } else{
                    Board.setHighlightedCardDisplay(itsaMe);
                }
                Gdx.app.log("CARDTOUCH: Touch down asset with name ", name);
                return true;
            }
        });
    }

    public void spritePos(float x, float y){
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
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
    }

}