package com.groupd.keltis.scenes.board.road_cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.utils.AssetPaths;

import java.util.ArrayList;
import java.util.Collections;

public class Road_cards_list {
    private ArrayList<Abstract_roadcards> roadcardsArrayList = new ArrayList<>();

    private void addRoadcards(Keltis keltis){
        for(int i = 0;i<9;i++){
            Image imageShamrock = new Image((Texture) keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK));
            Shamrock shamrock = new Shamrock(imageShamrock);
            roadcardsArrayList.add(shamrock);

            Image imageWishStone = new Image((Texture) keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE));
            Wish_stone wish_stone = new Wish_stone(imageWishStone);
            roadcardsArrayList.add(wish_stone);
        }
        for(int i = 0;i<2;i++){
            Image imagePointcardOne = new Image((Texture) keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDONE));
            Point_card point_card_one = new Point_card(imagePointcardOne,1);
            roadcardsArrayList.add(point_card_one);
            Image imagePointcardThree = new Image((Texture) keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTHREE));
            Point_card point_card_three = new Point_card(imagePointcardThree,3);
            roadcardsArrayList.add(point_card_three);
        }
        for(int i = 0;i<3;i++){
            Image imagePointcardTwo = new Image((Texture) keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO));
            Point_card point_card_two = new Point_card(imagePointcardTwo,2);
            roadcardsArrayList.add(point_card_two);
        }
    }

    private void mixRoadcards(){
        Collections.shuffle(this.roadcardsArrayList);
    }

    public void assignRoadcards(Keltis keltis){
        addRoadcards(keltis);
        mixRoadcards();
        /*for(Abstract_roadcards abstract_roadcards : this.roadcardsArrayList){
            //auf Feld zuweisen
        }*/
    }

    public ArrayList<Abstract_roadcards> getRoadcardsArrayList() {
        return roadcardsArrayList;
    }
}
