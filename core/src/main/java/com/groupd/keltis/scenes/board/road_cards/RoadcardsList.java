package com.groupd.keltis.scenes.board.road_cards;

import com.groupd.keltis.Keltis;
import com.groupd.keltis.utils.AssetPaths;

import java.util.ArrayList;
import java.util.Collections;

public class RoadcardsList {
    private ArrayList<Roadcards> roadcardsArrayList = new ArrayList<>();
    private ArrayList<Position> roadcardsPositionArray = new ArrayList<>();

    public void addRoadcards(Keltis keltis, ArrayList<Position> roadcardsPositionArray){
        roadcardsArrayList.add(new Shamrock("shamrockOne",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),roadcardsPositionArray.get(0)));
        roadcardsArrayList.add(new Shamrock("shamrockTwo",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),roadcardsPositionArray.get(1)));
        roadcardsArrayList.add(new Shamrock("shamrockThree",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),roadcardsPositionArray.get(2)));
        roadcardsArrayList.add(new Shamrock("shamrockFour",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),roadcardsPositionArray.get(3)));
        roadcardsArrayList.add(new Shamrock("shamrockFive",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),roadcardsPositionArray.get(4)));
        roadcardsArrayList.add(new Shamrock("shamrockSix",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),roadcardsPositionArray.get(5)));
        roadcardsArrayList.add(new Shamrock("shamrockSeven",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),roadcardsPositionArray.get(6)));
        roadcardsArrayList.add(new Shamrock("shamrockEight",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),roadcardsPositionArray.get(7)));
        roadcardsArrayList.add(new Shamrock("shamrockNine",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),roadcardsPositionArray.get(8)));

        roadcardsArrayList.add(new Wishstone("wishstoneOne",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),roadcardsPositionArray.get(9)));
        roadcardsArrayList.add(new Wishstone("wishstoneTwo",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),roadcardsPositionArray.get(10)));
        roadcardsArrayList.add(new Wishstone("wishstoneThree",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),roadcardsPositionArray.get(11)));
        roadcardsArrayList.add(new Wishstone("wishstoneFour",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),roadcardsPositionArray.get(12)));
        roadcardsArrayList.add(new Wishstone("wishstoneFive",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),roadcardsPositionArray.get(13)));
        roadcardsArrayList.add(new Wishstone("wishstoneSix",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),roadcardsPositionArray.get(14)));
        roadcardsArrayList.add(new Wishstone("wishstoneSeven",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),roadcardsPositionArray.get(15)));
        roadcardsArrayList.add(new Wishstone("wishstoneEight",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),roadcardsPositionArray.get(16)));
        roadcardsArrayList.add(new Wishstone("wishstoneNine",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),roadcardsPositionArray.get(17)));

        roadcardsArrayList.add(new Pointcard("pointcardOne",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDONE),roadcardsPositionArray.get(18),1));
        roadcardsArrayList.add(new Pointcard("pointcardTwo",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDONE),roadcardsPositionArray.get(19),1));
        roadcardsArrayList.add(new Pointcard("pointcardThree",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO),roadcardsPositionArray.get(20),2));
        roadcardsArrayList.add(new Pointcard("pointcardFour",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO),roadcardsPositionArray.get(21),2));
        roadcardsArrayList.add(new Pointcard("pointcardFive",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO),roadcardsPositionArray.get(22),2));
        roadcardsArrayList.add(new Pointcard("pointcardSix",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTHREE),roadcardsPositionArray.get(23),3));
        roadcardsArrayList.add(new Pointcard("pointcardSeven",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTHREE),roadcardsPositionArray.get(24),3));
    }

    public void mixPositions(ArrayList<Position> roadcardsPositionArray){
        Collections.shuffle(roadcardsPositionArray);
    }

    public void assignRoadcards(Keltis keltis){
        generateArray();
        addRoadcards(keltis, roadcardsPositionArray);
    }

    public ArrayList<Roadcards> getRoadcardsArrayList() {
        return roadcardsArrayList;
    }
    public ArrayList<Position> generateArray(){
        roadcardsPositionArray.add(new Position("positionOne",610,308,1,2));
        roadcardsPositionArray.add(new Position("positionTwo",610,400,1,3));
        roadcardsPositionArray.add(new Position("positionThree",610,584,1,5));
        roadcardsPositionArray.add(new Position("positionFour",610,676,1,6));
        roadcardsPositionArray.add(new Position("positionFive",610,768,1,7));

        roadcardsPositionArray.add(new Position("positionSix",830,308,2,2));
        roadcardsPositionArray.add(new Position("positionSeven",830,492,2,4));
        roadcardsPositionArray.add(new Position("positionEight",830,676,2,6));
        roadcardsPositionArray.add(new Position("positionNine",830,768,2,7));
        roadcardsPositionArray.add(new Position("positionTen",830,860,2,8));

        roadcardsPositionArray.add(new Position("positionEleven",1050,308,3,2));
        roadcardsPositionArray.add(new Position("positionTwelve",1050,400,3,3));
        roadcardsPositionArray.add(new Position("positionThirteen",1050,584,3,5));
        roadcardsPositionArray.add(new Position("positionFourteen",1050,768,3,7));
        roadcardsPositionArray.add(new Position("positionFifteen",1050,860,3,8));

        roadcardsPositionArray.add(new Position("positionSixteen",1275,308,4,2));
        roadcardsPositionArray.add(new Position("positionSeventeen",1275,492,4,4));
        roadcardsPositionArray.add(new Position("positionEighteen",1275,584,4,5));
        roadcardsPositionArray.add(new Position("positionNineteen",1275,768,4,7));
        roadcardsPositionArray.add(new Position("positionTwenty",1275,860,4,8));

        roadcardsPositionArray.add(new Position("positionTwentyOne",1495,308,5,2));
        roadcardsPositionArray.add(new Position("positionTwentyTwo",1495,400,5,3));
        roadcardsPositionArray.add(new Position("positionTwentyThree",1495,492,5,4));
        roadcardsPositionArray.add(new Position("positionTwentyFour",1495,676,5,6));
        roadcardsPositionArray.add(new Position("positionTwentyFive",1495,768,5,7));

        mixPositions(roadcardsPositionArray);

        return roadcardsPositionArray;
    }
}
