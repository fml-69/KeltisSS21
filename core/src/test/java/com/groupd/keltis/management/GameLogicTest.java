package com.groupd.keltis.management;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.groupd.keltis.scenes.board.Board;
import com.groupd.keltis.scenes.board.actors.Figure;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.scenes.board.road_cards.Pointcard;
import com.groupd.keltis.scenes.board.road_cards.Position;
import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.Shamrock;
import com.groupd.keltis.scenes.board.road_cards.Wishstone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameLogicTest {
    @Mock
    private Board boardMock;
    @Mock
    private Pointcard pointcardMock;
    @Mock
    private Pointcard pointcard2Mock;
    @Mock
    private Player playerMock;
    @Mock
    private Wishstone wishstoneMock;
    @Mock
    private Figure figureMock;
    @Mock
    private Shamrock shamrockMock;
    @Mock
    private Position positionMock;

    @InjectMocks
    private GameLogic gameLogic;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        Gdx.app = mock(Application.class);

        gameLogic = new GameLogic();
        gameLogic.setBoard(boardMock);
    }

    @Test
    public void testCheckCardReturnAddPointcard(){
        ArrayList<Pointcard> pointcardArrayList = new ArrayList<>();
        pointcardArrayList.add(pointcardMock);
        pointcardArrayList.add(pointcard2Mock);

        when(pointcardMock.getPoints()).thenReturn(2);
        when(pointcard2Mock.getPoints()).thenReturn(3);
        when(playerMock.getPointCards()).thenReturn(pointcardArrayList);

        gameLogic.checkCard(playerMock, pointcardMock);
        gameLogic.checkCard(playerMock, pointcard2Mock);

        assertEquals(pointcardArrayList,playerMock.getPointCards());
        assertEquals(2,playerMock.getPointCards().get(0).getPoints());
        assertEquals(3,playerMock.getPointCards().get(1).getPoints());

        verify(pointcardMock,times(1)).getPoints();
        verify(pointcard2Mock,times(1)).getPoints();
        verify(playerMock,times(5)).getPointCards();
    }

    @Test
    public void testCheckCardReturnAddWishstone() throws IOException {
        when(playerMock.getWishingStones()).thenReturn(1);
        try {
            gameLogic.checkCard(playerMock,wishstoneMock);
        } catch (Exception e){
            assertEquals(e.getClass(),NullPointerException.class);
        }
        assertEquals(1,playerMock.getWishingStones());

        verify(playerMock,times(1)).getWishingStones();
    }

    @Test
    public void testCheckCardReturnMoveFigureBecauseOfShamrock(){
        ArrayList<Player> playerArrayList = new ArrayList<>();
        playerArrayList.add(playerMock);
        gameLogic.setPlayerArrayList(playerArrayList);

        when(playerMock.getTurn()).thenReturn(true);
        when(playerMock.getNick()).thenReturn("");
        when(figureMock.getCurrentFieldPosition()).thenReturn(1);

        assertEquals(true,gameLogic.checkCard(playerMock,shamrockMock));
        assertEquals(figureMock.getCurrentFieldPosition(),1);

        verify(playerMock,times(1)).getTurn();
        verify(playerMock,times(1)).getNick();
        verify(figureMock,times(1)).getCurrentFieldPosition();
    }

    @Test
    public void testCheckIfCardIsOnFieldReturnTrue(){
        when(figureMock.getBranch()).thenReturn(2);
        when(figureMock.getCurrentFieldPosition()).thenReturn(3);
        when(wishstoneMock.getPosition()).thenReturn(positionMock);
        when(positionMock.getBranch()).thenReturn(2);
        when(positionMock.getField()).thenReturn(3);

        ArrayList<Roadcards> roadcardsArrayList = new ArrayList<>();
        roadcardsArrayList.add(wishstoneMock);

        gameLogic.setRoadCardsList(roadcardsArrayList);

        try {
            assertTrue(gameLogic.checkIfCardIsOnField(playerMock, figureMock));
        } catch (Exception e){
            assertEquals(e.getClass(),NullPointerException.class);
        }

        verify(figureMock,times(1)).getBranch();
        verify(figureMock,times(1)).getCurrentFieldPosition();
        verify(wishstoneMock,times(2)).getPosition();
    }

    @Test
    public void testCheckIfCardIsOnFieldReturnFalse(){
        when(figureMock.getBranch()).thenReturn(2);
        when(figureMock.getCurrentFieldPosition()).thenReturn(4);
        when(wishstoneMock.getPosition()).thenReturn(positionMock);
        when(positionMock.getBranch()).thenReturn(2);
        when(positionMock.getField()).thenReturn(3);

        ArrayList<Roadcards> roadcardsArrayList = new ArrayList<>();
        roadcardsArrayList.add(wishstoneMock);

        gameLogic.setRoadCardsList(roadcardsArrayList);

        assertFalse(gameLogic.checkIfCardIsOnField(playerMock, figureMock));

        verify(figureMock,times(1)).getBranch();
        verify(figureMock,times(1)).getCurrentFieldPosition();
        verify(wishstoneMock,times(2)).getPosition();
    }


}
