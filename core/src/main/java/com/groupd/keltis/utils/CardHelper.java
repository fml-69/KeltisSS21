package com.groupd.keltis.utils;

import com.groupd.keltis.Keltis;
import com.groupd.keltis.scenes.board.actors.Card;

import java.util.HashMap;

public class CardHelper {

    HashMap<String, Card> cardHashmap = new HashMap<>();

    public CardHelper(Keltis keltis){
        cardHashmap.put("", new Card(keltis.assetManager.get(AssetPaths.CARD_EMPTY_HANDCARD),"","",-1));
        cardHashmap.put("blueZero", new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_ZERO), "blueZero", "blue", 0));
        cardHashmap.put("blueOne", new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_ONE), "blueOne", "blue", 1));
        cardHashmap.put("blueTwo", new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_TWO), "blueTwo", "blue", 2));
        cardHashmap.put("blueThree", new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_THREE), "blueThree", "blue", 3));
        cardHashmap.put("blueFour", new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_FOUR), "blueFour", "blue", 4));
        cardHashmap.put("blueFive", new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_FIVE), "blueFive", "blue", 5));
        cardHashmap.put("blueSix", new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_SIX), "blueSix", "blue", 6));
        cardHashmap.put("blueSeven", new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_SEVEN), "blueSeven", "blue", 7));
        cardHashmap.put("blueEight", new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_EIGHT), "blueEight", "blue", 8));
        cardHashmap.put("blueNine", new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_NINE), "blueNine", "blue", 9));
        cardHashmap.put("blueTen", new Card(keltis.assetManager.get(AssetPaths.CARD_BLUE_TEN), "blueTen", "blue", 10));

        cardHashmap.put("greenZero", new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_ZERO), "greenZero", "green", 0));
        cardHashmap.put("greenOne", new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_ONE), "greenOne", "green", 1));
        cardHashmap.put("greenTwo", new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_TWO), "greenTwo", "green", 2));
        cardHashmap.put("greenThree", new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_THREE), "greenThree", "green", 3));
        cardHashmap.put("greenFour", new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_FOUR), "greenFour", "green", 4));
        cardHashmap.put("greenFive", new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_FIVE), "greenFive", "green", 5));
        cardHashmap.put("greenSix", new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_SIX), "greenSix", "green", 6));
        cardHashmap.put("greenSeven", new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_SEVEN), "greenSeven", "green", 7));
        cardHashmap.put("greenEight", new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_EIGHT), "greenEight", "green", 8));
        cardHashmap.put("greenNine", new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_NINE), "greenNine", "green", 9));
        cardHashmap.put("greenTen", new Card(keltis.assetManager.get(AssetPaths.CARD_GREEN_TEN), "greenTen", "green", 10));

        cardHashmap.put("purpleZero", new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_ZERO), "purpleZero", "purple", 0));
        cardHashmap.put("purpleOne", new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_ONE), "purpleOne", "purple", 1));
        cardHashmap.put("purpleTwo", new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_TWO), "purpleTwo", "purple", 2));
        cardHashmap.put("purpleThree", new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_THREE), "purpleThree", "purple", 3));
        cardHashmap.put("purpleFour", new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_FOUR), "purpleFour", "purple", 4));
        cardHashmap.put("purpleFive", new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_FIVE), "purpleFive", "purple", 5));
        cardHashmap.put("purpleSix", new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_SIX), "purpleSix", "purple", 6));
        cardHashmap.put("purpleSeven", new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_SEVEN), "purpleSeven", "purple", 7));
        cardHashmap.put("purpleEight", new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_EIGHT), "purpleEight", "purple", 8));
        cardHashmap.put("purpleNine", new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_NINE), "purpleNine", "purple", 9));
        cardHashmap.put("purpleTen", new Card(keltis.assetManager.get(AssetPaths.CARD_PURPLE_TEN), "purpleTen", "purple", 10));

        cardHashmap.put("redZero", new Card(keltis.assetManager.get(AssetPaths.CARD_RED_ZERO), "redZero", "red", 0));
        cardHashmap.put("redOne", new Card(keltis.assetManager.get(AssetPaths.CARD_RED_ONE), "redOne", "red", 1));
        cardHashmap.put("redTwo", new Card(keltis.assetManager.get(AssetPaths.CARD_RED_TWO), "redTwo", "red", 2));
        cardHashmap.put("redThree", new Card(keltis.assetManager.get(AssetPaths.CARD_RED_THREE), "redThree", "red", 3));
        cardHashmap.put("redFour", new Card(keltis.assetManager.get(AssetPaths.CARD_RED_FOUR), "redFour", "red", 4));
        cardHashmap.put("redFive", new Card(keltis.assetManager.get(AssetPaths.CARD_RED_FIVE), "redFive", "red", 5));
        cardHashmap.put("redSix", new Card(keltis.assetManager.get(AssetPaths.CARD_RED_SIX), "redSix", "red", 6));
        cardHashmap.put("redSeven", new Card(keltis.assetManager.get(AssetPaths.CARD_RED_SEVEN), "redSeven", "red", 7));
        cardHashmap.put("redEight", new Card(keltis.assetManager.get(AssetPaths.CARD_RED_EIGHT), "redEight", "red", 8));
        cardHashmap.put("redNine", new Card(keltis.assetManager.get(AssetPaths.CARD_RED_NINE), "redNine", "red", 9));
        cardHashmap.put("redTen", new Card(keltis.assetManager.get(AssetPaths.CARD_RED_TEN), "redTen", "red", 10));

        cardHashmap.put("yellowZero", new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_ZERO), "yellowZero", "yellow", 0));
        cardHashmap.put("yellowOne", new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_ONE), "yellowOne", "yellow", 1));
        cardHashmap.put("yellowTwo", new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_TWO), "yellowTwo", "yellow", 2));
        cardHashmap.put("yellowThree", new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_THREE), "yellowThree", "yellow", 3));
        cardHashmap.put("yellowFour", new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_FOUR), "yellowFour", "yellow", 4));
        cardHashmap.put("yellowFive", new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_FIVE), "yellowFive", "yellow", 5));
        cardHashmap.put("yellowSix", new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_SIX), "yellowSix", "yellow", 6));
        cardHashmap.put("yellowSeven", new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_SEVEN), "yellowSeven", "yellow", 7));
        cardHashmap.put("yellowEight", new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_EIGHT), "yellowEight", "yellow", 8));
        cardHashmap.put("yellowNine", new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_NINE), "yellowNine", "yellow", 9));
        cardHashmap.put("yellowTen", new Card(keltis.assetManager.get(AssetPaths.CARD_YELLOW_TEN), "yellowTen", "yellow", 10));
    }

    public HashMap getCardHashmap(){
        return cardHashmap;
    }
}
