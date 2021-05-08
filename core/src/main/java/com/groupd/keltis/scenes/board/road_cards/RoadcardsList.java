package com.groupd.keltis.scenes.board.road_cards;

import com.groupd.keltis.Keltis;
import com.groupd.keltis.utils.AssetPaths;

import java.util.ArrayList;
import java.util.Collections;

public class RoadcardsList {
    private ArrayList<Roadcards> roadcardsArrayList = new ArrayList<>();
    private int pointer = 0;

    private void addRoadcards(Keltis keltis, ArrayList<Position> roadcardsPositionArray){
        for(int i = 0;i<9;i++){
            Shamrock shamrock = new Shamrock(keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),roadcardsPositionArray.get(pointer));
            pointer++;
            roadcardsArrayList.add(shamrock);
            Wishstone wishstone = new Wishstone(keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),roadcardsPositionArray.get(pointer));
            pointer++;
            roadcardsArrayList.add(wishstone);
        }
        for(int i = 0;i<2;i++){
            Pointcard pointcard_one = new Pointcard(keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDONE),roadcardsPositionArray.get(pointer),1);
            pointer++;
            roadcardsArrayList.add(pointcard_one);
            Pointcard pointcard_three = new Pointcard(keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTHREE),roadcardsPositionArray.get(pointer),3);
            pointer++;
            roadcardsArrayList.add(pointcard_three);
        }
        for(int i = 0;i<3;i++){
            Pointcard pointcard_two = new Pointcard(keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO),roadcardsPositionArray.get(pointer),2);
            pointer++;
            roadcardsArrayList.add(pointcard_two);
        }
    }

    private void mixPositions(ArrayList<Position> roadcardsPositionArray){
        Collections.shuffle(roadcardsPositionArray);
    }

    public void assignRoadcards(Keltis keltis){
        ArrayList<Position> roadcardsPositionArray = new ArrayList<>();
        generateArray(roadcardsPositionArray);
        mixPositions(roadcardsPositionArray);
        addRoadcards(keltis, roadcardsPositionArray);
    }

    public ArrayList<Roadcards> getRoadcardsArrayList() {
        return roadcardsArrayList;
    }
    public void generateArray(ArrayList<Position> roadcardsPositionArray){
        roadcardsPositionArray.add(new Position(610,400,1,3));
        roadcardsPositionArray.add(new Position(610,584,1,5));
        roadcardsPositionArray.add(new Position(610,676,1,6));
        roadcardsPositionArray.add(new Position(610,768,1,7));
        roadcardsPositionArray.add(new Position(610,952,1,9));

        roadcardsPositionArray.add(new Position(830,308,2,2));
        roadcardsPositionArray.add(new Position(830,492,2,4));
        roadcardsPositionArray.add(new Position(830,676,2,6));
        roadcardsPositionArray.add(new Position(830,768,2,7));
        roadcardsPositionArray.add(new Position(830,952,2,9));

        roadcardsPositionArray.add(new Position(1050,308,3,2));
        roadcardsPositionArray.add(new Position(1050,400,3,3));
        roadcardsPositionArray.add(new Position(1050,584,3,5));
        roadcardsPositionArray.add(new Position(1050,768,3,7));
        roadcardsPositionArray.add(new Position(1050,952,3,9));

        roadcardsPositionArray.add(new Position(1275,308,4,2));
        roadcardsPositionArray.add(new Position(1275,492,4,4));
        roadcardsPositionArray.add(new Position(1275,584,4,5));
        roadcardsPositionArray.add(new Position(1275,768,4,7));
        roadcardsPositionArray.add(new Position(1275,952,4,9));

        roadcardsPositionArray.add(new Position(1495,400,5,3));
        roadcardsPositionArray.add(new Position(1495,492,5,4));
        roadcardsPositionArray.add(new Position(1495,676,5,6));
        roadcardsPositionArray.add(new Position(1495,768,5,7));
        roadcardsPositionArray.add(new Position(1495,952,5,9));
    }
}
