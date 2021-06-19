package com.groupd.keltis.utils;

import com.groupd.keltis.Keltis;
import com.groupd.keltis.scenes.board.road_cards.Pointcard;
import com.groupd.keltis.scenes.board.road_cards.Position;
import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.Shamrock;
import com.groupd.keltis.scenes.board.road_cards.Wishstone;

import java.util.HashMap;

public class RoadcardsHelper {
    HashMap<String, Roadcards> roadcardsHashMapHashmap = new HashMap<>();
    Position position = new Position("",1,1,1,1);

    public RoadcardsHelper(Keltis keltis) {
        roadcardsHashMapHashmap.put("shamrockOne",new Shamrock("shamrockOne",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),position));
        roadcardsHashMapHashmap.put("shamrockTwo",new Shamrock("shamrockTwo",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),position));
        roadcardsHashMapHashmap.put("shamrockThree",new Shamrock("shamrockThree",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),position));
        roadcardsHashMapHashmap.put("shamrockFour",new Shamrock("shamrockFour",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),position));
        roadcardsHashMapHashmap.put("shamrockFive",new Shamrock("shamrockFive",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),position));
        roadcardsHashMapHashmap.put("shamrockSix",new Shamrock("shamrockSix",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),position));
        roadcardsHashMapHashmap.put("shamrockSeven",new Shamrock("shamrockSeven",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),position));
        roadcardsHashMapHashmap.put("shamrockEight",new Shamrock("shamrockEight",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),position));
        roadcardsHashMapHashmap.put("shamrockNine",new Shamrock("shamrockNine",keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK),position));

        roadcardsHashMapHashmap.put("wishstoneOne",new Wishstone("wishstoneOne",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),position));
        roadcardsHashMapHashmap.put("wishstoneTwo",new Wishstone("wishstoneTwo",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),position));
        roadcardsHashMapHashmap.put("wishstoneThree",new Wishstone("wishstoneThree",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),position));
        roadcardsHashMapHashmap.put("wishstoneFour",new Wishstone("wishstoneFour",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),position));
        roadcardsHashMapHashmap.put("wishstoneFive",new Wishstone("wishstoneFive",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),position));
        roadcardsHashMapHashmap.put("wishstoneSix",new Wishstone("wishstoneSix",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),position));
        roadcardsHashMapHashmap.put("wishstoneSeven",new Wishstone("wishstoneSeven",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),position));
        roadcardsHashMapHashmap.put("wishstoneEight",new Wishstone("wishstoneEight",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),position));
        roadcardsHashMapHashmap.put("wishstoneNine",new Wishstone("wishstoneNine",keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE),position));

        roadcardsHashMapHashmap.put("pointcardOne",new Pointcard("pointcardOne",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDONE),position,1));
        roadcardsHashMapHashmap.put("pointcardTwo",new Pointcard("pointcardTwo",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDONE),position,1));
        roadcardsHashMapHashmap.put("pointcardThree",new Pointcard("pointcardThree",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO),position,2));
        roadcardsHashMapHashmap.put("pointcardFour",new Pointcard("pointcardFour",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO),position,2));
        roadcardsHashMapHashmap.put("pointcardFive",new Pointcard("pointcardFive",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO),position,2));
        roadcardsHashMapHashmap.put("pointcardSix",new Pointcard("pointcardSix",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTHREE),position,3));
        roadcardsHashMapHashmap.put("pointcardSeven",new Pointcard("pointcardSeven",keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTHREE),position,3));

    }

    public HashMap<String, Roadcards> getRoadcardsHashMapHashmap() {
        return roadcardsHashMapHashmap;
    }
}
