package com.groupd.keltis.PlayerTests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.scenes.board.actors.Figure;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.scenes.board.road_cards.Pointcard;
import com.groupd.keltis.utils.AssetPaths;
import com.groupd.keltis.utils.ColorFigures;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PlayerTests {

    @Mock
    private Pointcard pointcardMock;
    @Mock
    private Pointcard pointcard2Mock;

    @Mock
    private Keltis keltisMock;
    @Mock
    private AssetManager assetManagerMock;
    @Mock
    private Texture textureMock;

    private HashMap<String, Figure> figureHashMap;

    private Player player;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        Gdx.app = mock(Application.class);
        keltisMock.assetManager = assetManagerMock;
        when(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE)).thenReturn(textureMock);
        when(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_RED)).thenReturn(textureMock);
        when(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN)).thenReturn(textureMock);
        when(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW)).thenReturn(textureMock);
    }

    @Test
    public void whenPlayerColorEqualsColorCreateRightFigures(){


        for(int i = 0; i < 4; i++){
            figureHashMap = new HashMap<>();
            switch (i){
                case 0:
                    player = new Player(keltisMock, "TEST", ColorFigures.BLUE,false);
                    figureHashMap.put("blue1", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),"blue1",1));
                    figureHashMap.put("blue2", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),"blue2",2));
                    figureHashMap.put("blue3", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),"blue3",3));
                    figureHashMap.put("blue4", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),"blue4",4));
                    figureHashMap.put("blue5", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE),"blue5",5));
                    break;
                case 1:
                    player = new Player(keltisMock, "TEST", ColorFigures.RED,false);
                    figureHashMap.put("red1", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_RED),"red1",1));
                    figureHashMap.put("red2", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_RED),"red2",2));
                    figureHashMap.put("red3", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_RED),"red3",3));
                    figureHashMap.put("red4", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_RED),"red4",4));
                    figureHashMap.put("red5", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_RED),"red5",5));
                    break;
                case 2:
                    player = new Player(keltisMock, "TEST", ColorFigures.GREEN,false);
                    figureHashMap.put("green1", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),"green1",1));
                    figureHashMap.put("green2", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),"green2",2));
                    figureHashMap.put("green3", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),"green3",3));
                    figureHashMap.put("green4", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),"green4",4));
                    figureHashMap.put("green5", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN),"green5",5));
                    break;
                case 3:
                    player = new Player(keltisMock, "TEST", ColorFigures.YELLOW,false);
                    figureHashMap.put("yellow1", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),"yellow1",1));
                    figureHashMap.put("yellow2", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),"yellow2",2));
                    figureHashMap.put("yellow3", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),"yellow3",3));
                    figureHashMap.put("yellow4", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),"yellow4",4));
                    figureHashMap.put("yellow5", new Figure(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW),"yellow5",5));
                    break;
            }
            Assertions.assertEquals(figureHashMap.toString(),player.getFigures().toString());
        }
    }

    @Test
    public void giveBackRightColor(){

        for(int i = 0; i < 4; i++){
            switch (i){
                case 0:
                    player = new Player(keltisMock, "TEST", ColorFigures.BLUE,false);
                    Assertions.assertEquals(ColorFigures.BLUE,player.getColor());
                    break;
                case 1:
                    player = new Player(keltisMock, "TEST", ColorFigures.RED,false);
                    Assertions.assertEquals(ColorFigures.RED,player.getColor());
                    break;
                case 2:
                    player = new Player(keltisMock, "TEST", ColorFigures.GREEN,false);
                    Assertions.assertEquals(ColorFigures.GREEN,player.getColor());
                    break;
                case 3:
                    player = new Player(keltisMock, "TEST", ColorFigures.YELLOW,false);
                    Assertions.assertEquals(ColorFigures.YELLOW,player.getColor());
                    break;
            }

        }
    }
    @Test
    public void giveBackRightHost(){
        for(int i = 0; i < 2; i++){
            switch (i){
                case 0:
                    player = new Player(keltisMock, "TEST", ColorFigures.BLUE,false);
                    Assertions.assertFalse(player.isHost());
                    break;
                case 1:
                    player = new Player(keltisMock, "TEST", ColorFigures.RED,true);
                    Assertions.assertTrue(player.isHost());
                    break;
            }

        }
    }
    @Test
    public void setOldNickToNewNick(){
        player = new Player(keltisMock, "TEST", ColorFigures.BLUE,false);
        Assertions.assertEquals("TEST",player.getNick());
        player.setNick("Changed");
        Assertions.assertEquals("Changed",player.getNick());
    }
    @Test
    public void giveBackRightAmountOfWishstones(){
        player = new Player(keltisMock, "TEST", ColorFigures.BLUE,false);
        Assertions.assertEquals(0,player.getWishingStones());
        player.addWishStone();
        Assertions.assertEquals(1,player.getWishingStones());
        player.addWishStone();
        Assertions.assertEquals(2,player.getWishingStones());
        player.addWishStone();
        Assertions.assertEquals(3,player.getWishingStones());
        player.addWishStone();
        Assertions.assertEquals(4,player.getWishingStones());
    }
    @Test
    public void verifyFalseEndingCondition1(){
        player = new Player(keltisMock, "TEST", ColorFigures.BLUE,false);
        Assertions.assertFalse(player.verifyEndCondition());
    }
    @Test
    public void verifyFalseEndingCondition2(){
        player = new Player(keltisMock, "TEST", ColorFigures.BLUE,false);
        player.getFigures().get("blue1").setCurrentFieldPosition(9);
        player.getFigures().get("blue2").setCurrentFieldPosition(9);
        player.getFigures().get("blue3").setCurrentFieldPosition(9);
        Assertions.assertFalse(player.verifyEndCondition());
    }
    @Test
    public void verifyTrueEndingCondition(){
        player = new Player(keltisMock, "TEST", ColorFigures.BLUE,false);

        player.getFigures().get("blue1").setCurrentFieldPosition(9);
        player.getFigures().get("blue2").setCurrentFieldPosition(9);
        player.getFigures().get("blue3").setCurrentFieldPosition(9);
        player.getFigures().get("blue4").setCurrentFieldPosition(9);
        player.getFigures().get("blue5").setCurrentFieldPosition(9);

        Assertions.assertTrue(player.verifyEndCondition());
    }
    @Test
    public void verifyRightScoreAfterAcquiringOnlyWishingstonse(){
        player = new Player(keltisMock, "TEST", ColorFigures.BLUE,false);
        player.addWishStone();
        player.addWishStone();
        Assertions.assertEquals(2,player.getOverallScore());
    }

    @Test
    public void verifyRightScoreAfterAcquiringOnlyPointCards(){
        when(pointcard2Mock.getPoints()).thenReturn(2);
        when(pointcardMock.getPoints()).thenReturn(1);
        player = new Player(keltisMock, "TEST", ColorFigures.BLUE,false);
        player.getPointCards().add(pointcardMock);
        player.getPointCards().add(pointcard2Mock);
        Assertions.assertEquals(-1,player.getOverallScore());
    }

}
