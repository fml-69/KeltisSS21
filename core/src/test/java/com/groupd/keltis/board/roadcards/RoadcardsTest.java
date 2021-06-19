package com.groupd.keltis.board.roadcards;

import com.badlogic.gdx.graphics.Texture;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoadcardsTest {
    /*@Mock
    private Texture textureMock;
    @Mock
    private Position positionMock;

    @InjectMocks
    private Roadcards roadcard;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRoadcardsConstructorWishstone(){
        when(positionMock.getX()).thenReturn(120);

        roadcard = new Wishstone(textureMock, positionMock);
        Roadcards roadcards1 = roadcard;

        assertEquals(roadcards1, roadcard);
        assertEquals(120, roadcard.getPosition().getX());

        verify(positionMock,times(2)).getX();
    }

    @Test
    public void testRoadcardsConstructorShamrock(){
        when(positionMock.getX()).thenReturn(100);

        roadcard = new Shamrock(textureMock, positionMock);
        Roadcards roadcards1 = roadcard;

        assertEquals(roadcards1, roadcard);
        assertEquals(100, roadcard.getPosition().getX());

        verify(positionMock,times(2)).getX();
    }
    @Test
    public void testRoadCardsConstructorPointcards(){
        when(positionMock.getX()).thenReturn(140);

        roadcard = new Pointcard(textureMock, positionMock,3);
        Pointcard pointcard = (Pointcard) roadcard;

        assertEquals(pointcard,roadcard);
        assertEquals(140,pointcard.getPosition().getX());
        assertEquals(3,pointcard.getPoints());

        verify(positionMock,times(2)).getX();
    }

    @Test
    public void testRoadcardsSpritePosReturn200And400(){
        roadcard = new Roadcards(textureMock, positionMock);
        roadcard.spritePos(200,400);
        assertEquals(200, (int) roadcard.getSprite().getX());
        assertEquals(400, (int) roadcard.getSprite().getY());
    }
    */
}


