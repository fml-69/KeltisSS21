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
import com.groupd.keltis.scenes.board.Board;
import com.groupd.keltis.scenes.board.actors.Figure;
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

    public static void checkRoadcards(String playerName, HashMap<String, Figure> playerHashMap, RoadcardsList roadcardsList, HashMap<String, Player> player, Board board){
        int fieldPlayer = playerHashMap.get(playerName).getCurrentFieldPosition();
        int branchPlayer = Integer.valueOf(playerName.substring(playerName.length()-1));
        String colorPlayer = playerName.substring(0,playerName.length()-1);
        Wishstone wishstone = null;
        for(int i = 0; i < roadcardsList.getRoadcardsArrayList().size();i++){
            if(fieldPlayer == roadcardsList.getRoadcardsArrayList().get(i).getPosition().getField() && branchPlayer == roadcardsList.getRoadcardsArrayList().get(i).getPosition().getBranch()){
                if(roadcardsList.getRoadcardsArrayList().get(i).getClass() == Wishstone.class){
                    roadcardsList.getRoadcardsArrayList().get(i).addAction(Actions.removeActor());
                    wishstone = (Wishstone) roadcardsList.getRoadcardsArrayList().get(i);
                    switch(colorPlayer){
                        case "blue": player.get("player1").addWishingStones(); break;
                        case "red": player.get("player2").addWishingStones(); break;
                        case "green": player.get("player3").addWishingStones(); break;
                        case "yellow": player.get("player4").addWishingStones(); break;
                    }
                    break;
                } else if (roadcardsList.getRoadcardsArrayList().get(i).getClass() == Shamrock.class){
                    board.showDialog(board.getShamrockDialog(),board.stage,3);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            long time = System.currentTimeMillis();
                            while (System.currentTimeMillis() < time + 2000){}
                            Gdx.app.postRunnable(new Runnable() {
                                @Override
                                public void run() {
                                    board.getShamrockDialog().hide();
                                }
                            });
                        }
                    }).start();
                    playerHashMap.get(playerName).getSprite().setY(playerHashMap.get(playerName).getSprite().getY()+5*18);
                    playerHashMap.get(playerName).moveUp();

                } else if (roadcardsList.getRoadcardsArrayList().get(i).getClass() == Pointcard.class){
                    Pointcard pointcard = (Pointcard) roadcardsList.getRoadcardsArrayList().get(i);
                    switch(colorPlayer){
                        case "blue": player.get("player1").addPointcards(pointcard.getPoints()); break;
                        case "red": player.get("player2").addPointcards(pointcard.getPoints()); break;
                        case "green": player.get("player3").addPointcards(pointcard.getPoints()); break;
                        case "yellow": player.get("player4").addPointcards(pointcard.getPoints()); break;
                    }
                }
            }
        }
        if(wishstone !=null){
            roadcardsList.getRoadcardsArrayList().remove(wishstone);
        }
    }
}
