package com.groupd.keltis.board.roadcards;

import com.badlogic.gdx.graphics.Texture;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.scenes.board.road_cards.Pointcard;
import com.groupd.keltis.scenes.board.road_cards.Position;
import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.RoadcardsList;
import com.groupd.keltis.scenes.board.road_cards.Shamrock;
import com.groupd.keltis.scenes.board.road_cards.Wishstone;
import com.groupd.keltis.utils.AssetPaths;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RoadcardsListTest {
    @Mock
    private Keltis keltis;
    @Mock
    private Texture texture;
    @InjectMocks
    private RoadcardsList roadcardsList;

    private ArrayList<Position> roadcardsPositionArray;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        roadcardsList = new RoadcardsList();
        roadcardsPositionArray = new ArrayList<>();
        roadcardsList.generateArray(roadcardsPositionArray);
        texture = mock(Texture.class);
    }

    @Test
    public void testAddRoadcardsReturnCount() {
        /*
        when(keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK)).thenReturn(texture);
        when(keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE)).thenReturn(texture);
        when(keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDONE)).thenReturn(texture);
        when(keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO)).thenReturn(texture);
        when(keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTHREE)).thenReturn(texture);

        roadcardsList.addRoadcards(keltis,roadcardsPositionArray);

        int counterShamrock =0;
        int counterPointcards=0;
        int counterWishstone=0;
        for(Roadcards roadcards : roadcardsList.getRoadcardsArrayList()){
            if (roadcards instanceof Wishstone) {
               counterWishstone++;
            } else if (roadcards instanceof Shamrock) {
                counterShamrock++;
            } else if (roadcards instanceof Pointcard) {
             counterPointcards++;
            }
        }
        assertEquals(9,counterShamrock);
        assertEquals(9,counterWishstone);
        assertEquals(7,counterPointcards);

        verify(keltis,times(9)).assetManager.get(AssetPaths.ROADCARD_SHAMROCK);
        verify(keltis,times(9)).assetManager.get(AssetPaths.ROADCARD_WISHSTONE);
        verify(keltis,times(2)).assetManager.get(AssetPaths.ROADCARD_POINTCARDONE);
        verify(keltis,times(3)).assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO);
        verify(keltis,times(2)).assetManager.get(AssetPaths.ROADCARD_POINTCARDTHREE);
        */
    }
    @Test
    public void testAddRoadcardsReturnPosition(){
        /*
        when(keltis.assetManager.get(AssetPaths.ROADCARD_SHAMROCK)).thenReturn(texture);
        when(keltis.assetManager.get(AssetPaths.ROADCARD_WISHSTONE)).thenReturn(texture);
        when(keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDONE)).thenReturn(texture);
        when(keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO)).thenReturn(texture);
        when(keltis.assetManager.get(AssetPaths.ROADCARD_POINTCARDTHREE)).thenReturn(texture);

        roadcardsList.addRoadcards(keltis,roadcardsPositionArray);

        int pointerPosition =0;
        for(Roadcards roadcards : roadcardsList.getRoadcardsArrayList()){
            assertEquals(roadcardsPositionArray.get(pointerPosition),roadcards.getPosition());
            pointerPosition++;
        }

        verify(keltis,times(9)).assetManager.get(AssetPaths.ROADCARD_SHAMROCK);
        verify(keltis,times(9)).assetManager.get(AssetPaths.ROADCARD_WISHSTONE);
        verify(keltis,times(2)).assetManager.get(AssetPaths.ROADCARD_POINTCARDONE);
        verify(keltis,times(3)).assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO);
        verify(keltis,times(2)).assetManager.get(AssetPaths.ROADCARD_POINTCARDTHREE);
        */
    }
}
