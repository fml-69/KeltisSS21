package com.groupd.keltis.board.roadcards;

import com.groupd.keltis.scenes.board.road_cards.Position;

import org.junit.Test;
import static org.junit.Assert.*;

public class PositionTest {
    @Test
    public void testPositionConstructor(){
        Position position = new Position(120,300,3,6);
        assertEquals(120,position.getX());
        assertEquals(300,position.getY());
        assertEquals(3,position.getBranch());
        assertEquals(6,position.getField());
    }
    @Test
    public void testSetPosition(){
        Position position = new Position(120,300,3,6);
        position.setY(200);
        position.setX(400);
        assertEquals(200,position.getY());
        assertEquals(400,position.getX());
    }
}
