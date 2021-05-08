package com.groupd.keltis.scenes.board.road_cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.groupd.keltis.scenes.board.actors.Player;

import java.util.HashMap;

public class Roadcards extends Actor {
    private Sprite sprite;
    private Position position;
    public Roadcards(Texture texture, Position position){
        this.sprite = new Sprite(texture);
        this.position = position;
        spritePos(position.getX(), position.getY());
        setTouchable(Touchable.enabled);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Touch down asset with name ", "roadcard");
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
        sprite.draw(batch);
    }

    public Position getPosition() {
        return position;
    }

    public static void checkRoadcards(String player, HashMap<String, Player> playerHashMap, RoadcardsList roadcardsList){
        int fieldPlayer = playerHashMap.get(player).getCurrentFieldPosition();
        int branchPlayer = Integer.valueOf(player.substring(player.length()-1));
        Wishstone wishstone = null;
        for(int i = 0; i < roadcardsList.getRoadcardsArrayList().size();i++){
            if(fieldPlayer == roadcardsList.getRoadcardsArrayList().get(i).getPosition().getField() && branchPlayer == roadcardsList.getRoadcardsArrayList().get(i).getPosition().getBranch()){
                if(roadcardsList.getRoadcardsArrayList().get(i).getClass() == Wishstone.class){
                    roadcardsList.getRoadcardsArrayList().get(i).addAction(Actions.removeActor());
                    wishstone = (Wishstone) roadcardsList.getRoadcardsArrayList().get(i);
                    //Wish-Stone bei Player hinzufügen
                } else if (roadcardsList.getRoadcardsArrayList().get(i).getClass() == Shamrock.class){

                } else if (roadcardsList.getRoadcardsArrayList().get(i).getClass() == Pointcard.class){

                }
            }
        }
        if(wishstone !=null){
            roadcardsList.getRoadcardsArrayList().remove(wishstone);
            wishstone.addAction(Actions.removeActor());
        }
    }
}
