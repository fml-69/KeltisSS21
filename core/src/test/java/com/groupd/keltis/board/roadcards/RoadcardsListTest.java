package com.groupd.keltis.board.roadcards;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.scenes.board.road_cards.Pointcard;
import com.groupd.keltis.scenes.board.road_cards.Position;
import com.groupd.keltis.scenes.board.road_cards.Roadcards;
import com.groupd.keltis.scenes.board.road_cards.RoadcardsList;
import com.groupd.keltis.scenes.board.road_cards.Shamrock;
import com.groupd.keltis.scenes.board.road_cards.Wishstone;
import com.groupd.keltis.utils.AssetPaths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoadcardsListTest {
    @Mock
    private Keltis keltisMock;
    @Mock
    private AssetManager assetManagerMock;
    @Mock
    private Texture textureMock;

    @InjectMocks
    private RoadcardsList roadcardsList;

    private ArrayList<Position> roadcardsPositionArray;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        roadcardsList = new RoadcardsList();
        roadcardsPositionArray = new ArrayList<>();
        roadcardsList.generateArray(roadcardsPositionArray);
        keltisMock.assetManager = assetManagerMock;
    }

    @Test
    public void testAddRoadcardsReturnCount() {
        when(keltisMock.assetManager.get(AssetPaths.ROADCARD_SHAMROCK)).thenReturn(textureMock);
        when(keltisMock.assetManager.get(AssetPaths.ROADCARD_WISHSTONE)).thenReturn(textureMock);
        when(keltisMock.assetManager.get(AssetPaths.ROADCARD_POINTCARDONE)).thenReturn(textureMock);
        when(keltisMock.assetManager.get(AssetPaths.ROADCARD_POINTCARDTWO)).thenReturn(textureMock);
        when(keltisMock.assetManager.get(AssetPaths.ROADCARD_POINTCARDTHREE)).thenReturn(textureMock);

        roadcardsList.addRoadcards(keltisMock,roadcardsPositionArray);

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
        assertEquals(25,roadcardsList.getRoadcardsArrayList().size());

        verify(keltisMock.assetManager,times(9)).get(AssetPaths.ROADCARD_SHAMROCK);
        verify(keltisMock.assetManager,times(9)).get(AssetPaths.ROADCARD_WISHSTONE);
        verify(keltisMock.assetManager,times(2)).get(AssetPaths.ROADCARD_POINTCARDONE);
        verify(keltisMock.assetManager,times(3)).get(AssetPaths.ROADCARD_POINTCARDTWO);
        verify(keltisMock.assetManager,times(2)).get(AssetPaths.ROADCARD_POINTCARDTHREE);
    }
    @Test
    public void testgenerateArrayEqual(){
        ArrayList<Position> positionArrayList = new ArrayList<>();
        positionArrayList.add(new Position(610,308,1,2));
        positionArrayList.add(new Position(610,400,1,3));
        positionArrayList.add(new Position(610,584,1,5));
        positionArrayList.add(new Position(610,676,1,6));
        positionArrayList.add(new Position(610,768,1,7));

        positionArrayList.add(new Position(830,308,2,2));
        positionArrayList.add(new Position(830,492,2,4));
        positionArrayList.add(new Position(830,676,2,6));
        positionArrayList.add(new Position(830,768,2,7));
        positionArrayList.add(new Position(830,860,2,8));

        positionArrayList.add(new Position(1050,308,3,2));
        positionArrayList.add(new Position(1050,400,3,3));
        positionArrayList.add(new Position(1050,584,3,5));
        positionArrayList.add(new Position(1050,768,3,7));
        positionArrayList.add(new Position(1050,860,3,8));

        positionArrayList.add(new Position(1275,308,4,2));
        positionArrayList.add(new Position(1275,492,4,4));
        positionArrayList.add(new Position(1275,584,4,5));
        positionArrayList.add(new Position(1275,768,4,7));
        positionArrayList.add(new Position(1275,860,4,8));

        positionArrayList.add(new Position(1495,308,5,2));
        positionArrayList.add(new Position(1495,400,5,3));
        positionArrayList.add(new Position(1495,492,5,4));
        positionArrayList.add(new Position(1495,676,5,6));
        positionArrayList.add(new Position(1495,768,5,7));


        for(int i = 0; i<positionArrayList.size();i++){
            assertEquals(positionArrayList.get(i).getBranch(),roadcardsPositionArray.get(i).getBranch());
            assertEquals(positionArrayList.get(i).getField(),roadcardsPositionArray.get(i).getField());
            assertEquals(positionArrayList.get(i).getX(),roadcardsPositionArray.get(i).getX());
            assertEquals(positionArrayList.get(i).getY(),roadcardsPositionArray.get(i).getY());
        }
    }
}
