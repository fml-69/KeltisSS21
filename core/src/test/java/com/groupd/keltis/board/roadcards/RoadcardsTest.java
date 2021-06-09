package com.groupd.keltis.board.roadcards;

import com.badlogic.gdx.graphics.Texture;
import com.groupd.keltis.scenes.board.road_cards.Position;
import com.groupd.keltis.scenes.board.road_cards.Roadcards;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class RoadcardsTest {
    @Mock
    private Texture texture;

    private Position position;
    private Roadcards roadcards;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        this.position = new Position(120,300,3,6);
        texture = mock(Texture.class);
        roadcards = null;
    }

    @Test
    public void testRoadcardsConstructor(){
        Roadcards roadcards = null;
        assertNull(roadcards);

        roadcards = new Roadcards(texture,position);
        Roadcards roadcards1 = roadcards;
        assertEquals(roadcards1,roadcards);

        assertEquals(120,roadcards.getPosition().getX());
    }

    @Test
    public void testRoadcardsSpritePosReturn200And400(){
        roadcards = new Roadcards(texture,position);
        roadcards.spritePos(200,400);
        assertEquals(200, (int) roadcards.getSprite().getX());
        assertEquals(400, (int) roadcards.getSprite().getY());
    }
}


