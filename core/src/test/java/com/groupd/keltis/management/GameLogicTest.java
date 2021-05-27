package com.groupd.keltis.management;

import com.badlogic.gdx.graphics.Texture;
import com.groupd.keltis.scenes.board.actors.Figure;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.scenes.board.road_cards.Pointcard;
import com.groupd.keltis.scenes.board.road_cards.Position;
import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.Shamrock;
import com.groupd.keltis.scenes.board.road_cards.Wishstone;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class GameLogicTest {
    @Mock
    private Texture texture;

    @InjectMocks
    private GameLogic gameLogic;

    private Player player;
    private Figure figure;
    private Roadcards roadcards;
    private Roadcards roadcards2;
    private Roadcards roadcards3;

    private Position position;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        texture = mock(Texture.class);
        gameLogic = new GameLogic();
        player = new Player();
        position = new Position(120,300,3,3);
    }

    @Test
    public void testCheckCardReturnAddPointcard(){
        roadcards = new Pointcard(texture,position,2);
        roadcards2 = new Pointcard(texture,position,3);
        roadcards3 = new Pointcard(texture,position,1);

        ArrayList<Pointcard> pointcardArrayList = new ArrayList<>();
        pointcardArrayList.add((Pointcard)roadcards);
        pointcardArrayList.add((Pointcard)roadcards2);
        pointcardArrayList.add((Pointcard)roadcards3);

        gameLogic.checkCard(player,null,roadcards);
        gameLogic.checkCard(player,null,roadcards2);
        gameLogic.checkCard(player,null,roadcards3);

        assertEquals(pointcardArrayList,player.getPointCards());
    }

    @Test
    public void testCheckCardReturnAddWishstone(){
        roadcards = new Wishstone(texture,position);
        roadcards2 = new Wishstone(texture,position);

        gameLogic.checkCard(player,null,roadcards);
        gameLogic.checkCard(player,null,roadcards2);

        int counterWishstone = player.getWishingStones();
        assertEquals(2,counterWishstone);
    }

    @Test
    public void testCheckCardReturnMoveFigureBecauseOfShamrock(){
        roadcards = new Shamrock(texture,position);
        figure = new Figure(texture,"blue1",3);
        player.getFigures().put("blue1",figure);

        assertEquals(0,figure.getCurrentFieldPosition());

        gameLogic.checkCard(player,figure,roadcards);

        //wie soll getestet werden, dass die Figur ein Feld weitergezogen ist???????
    }

    @Test
    public void testCheckIfCardIsOnFieldReturnTrue(){
        figure = new Figure(texture,"blue1",3);
        figure.setCurrentFieldPosition(3);
        player.getFigures().put("blue1",figure);

        roadcards = new Wishstone(texture,position);
        roadcards2 = new Pointcard(texture,new Position(100,300,1,2),3);
        roadcards3 = new Pointcard(texture,new Position(790,800,4,7),1);

        ArrayList<Roadcards> roadcardsArrayList = new ArrayList<>();
        roadcardsArrayList.add((Wishstone)roadcards);
        roadcardsArrayList.add((Pointcard)roadcards2);
        roadcardsArrayList.add((Pointcard)roadcards3);

        gameLogic.setRoadCardsList(roadcardsArrayList);

        assertTrue(gameLogic.checkIfCardIsOnField(player, figure));
    }

    @Test
    public void testCheckIfCardIsOnFieldReturnFalse(){
        figure = new Figure(texture,"blue1",5);
        figure.setCurrentFieldPosition(3);
        player.getFigures().put("blue1",figure);

        roadcards = new Shamrock(texture,position);
        roadcards2 = new Pointcard(texture,new Position(100,300,1,2),3);
        roadcards3 = new Pointcard(texture,new Position(790,800,4,7),1);

        ArrayList<Roadcards> roadcardsArrayList = new ArrayList<>();
        roadcardsArrayList.add((Shamrock)roadcards);
        roadcardsArrayList.add((Pointcard)roadcards2);
        roadcardsArrayList.add((Pointcard)roadcards3);

        gameLogic.setRoadCardsList(roadcardsArrayList);

        assertFalse(gameLogic.checkIfCardIsOnField(player, figure));
    }
}
