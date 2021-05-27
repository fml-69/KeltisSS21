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

public class CardDisplay extends Actor {

    private Keltis keltis;
    private Sprite sprite;
    private String name;
    private String color;
    private int value;
    private Card currentCard = null;
    private Texture emptyHandcardTexture;
    private CardDisplay itsaMe = this;
    private Texture highlightTexture;

    public CardDisplay(Keltis keltis, Texture texture, final String name, boolean isHandCard){
        this.keltis = keltis;
        this.highlightTexture = keltis.assetManager.get(AssetPaths.CARD_HIGHLIGHT);
        if(isHandCard){
            this.emptyHandcardTexture = keltis.assetManager.get(AssetPaths.CARD_EMPTY_HANDCARD);
            sprite = new Sprite(emptyHandcardTexture);
        } else{
            sprite = new Sprite(texture);
        }
        this.name = name;
        spritePos(sprite.getX(), sprite.getY());

        setTouchable(Touchable.enabled);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(Board.getHighlightedCardDisplay()!=null){
                    if(Board.getHighlightedCardDisplay().getCard()!=null) {
                        setCard(Board.getHighlightedCardDisplay().getCard());
                    }
                    Board.setHighlightedCardDisplay(null);
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
        this.color = card.getCardColor();
        this.currentCard = card;
        sprite = new Sprite(card.getTexture());
    }

    public Card getCard(){
        return currentCard;
    }

}