package com.groupd.keltis.utils;

import com.groupd.keltis.scenes.board.road_cards.Position;

import java.util.HashMap;

public class PositionHelper {
    HashMap<String, Position> positionHashMap = new HashMap<>();

    public PositionHelper(){
        positionHashMap.put("positionOne",new Position("positionOne",610,308,1,2));
        positionHashMap.put("positionTwo",new Position("positionTwo",610,400,1,3));
        positionHashMap.put("positionThree",new Position("positionThree",610,584,1,5));
        positionHashMap.put("positionFour",new Position("positionFour",610,676,1,6));
        positionHashMap.put("positionFive",new Position("positionFive",610,768,1,7));

        positionHashMap.put("positionSix",new Position("positionSix",830,308,2,2));
        positionHashMap.put("positionSeven",new Position("positionSeven",830,492,2,4));
        positionHashMap.put("positionEight",new Position("positionEight",830,676,2,6));
        positionHashMap.put("positionNine",new Position("positionNine",830,768,2,7));
        positionHashMap.put("positionTen",new Position("positionTen",830,860,2,8));

        positionHashMap.put("positionEleven",new Position("positionEleven",1050,308,3,2));
        positionHashMap.put("positionTwelve",new Position("positionOneTwelve",1050,400,3,3));
        positionHashMap.put("positionThirteen",new Position("positionThirteen",1050,584,3,5));
        positionHashMap.put("positionFourteen",new Position("positionFourteen",1050,768,3,7));
        positionHashMap.put("positionFifteen",new Position("positionFifteen",1050,860,3,8));

        positionHashMap.put("positionSixteen",new Position("positionSixteen",1275,308,4,2));
        positionHashMap.put("positionSeventeen",new Position("positionSeventeen",1275,492,4,4));
        positionHashMap.put("positionEighteen",new Position("positionEighteen",1275,584,4,5));
        positionHashMap.put("positionNineteen",new Position("positionNineteen",1275,768,4,7));
        positionHashMap.put("positionTwenty",new Position("positionTwenty",1275,860,4,8));

        positionHashMap.put("positionTwentyOne",new Position("positionTwentyOne",1495,308,5,2));
        positionHashMap.put("positionTwentyTwo",new Position("positionTwentyTwo",1495,400,5,3));
        positionHashMap.put("positionTwentyThree",new Position("positionTwentyThree",1495,492,5,4));
        positionHashMap.put("positionTwentyFour",new Position("positionTwentyFour",1495,676,5,6));
        positionHashMap.put("positionTwentyFive",new Position("positionTwentyFive",1495,768,5,7));
    }

    public HashMap<String, Position> getPositionHashMap() {
        return positionHashMap;
    }
}
