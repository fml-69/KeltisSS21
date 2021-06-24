package com.groupd.keltis.cheating;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.groupd.keltis.network.events.CheatQueryEvent;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.utils.ColorFigures;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class CheatTest {

    @Before
    public void setUp() {
        //open the mock
        MockitoAnnotations.openMocks(this);

        //used to mock the Gdx.input and Gdx.app
        Gdx.input = mock(Input.class);
        Gdx.app = mock(Application.class);
    }

    @Test
    public void testCheating() {

        //argument captor allows to capture an argument passed to a method in order to inspect it
        ArgumentCaptor<String> recipientCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<CheatQueryEvent> eventCaptor = ArgumentCaptor.forClass(CheatQueryEvent.class);

        ArrayList<Player> players = new ArrayList<>();
        players.add(new PlayerTestable(null, "P1", ColorFigures.BLUE, true));
        players.add(new PlayerTestable(null, "P2", ColorFigures.RED, false));
        players.add(new PlayerTestable(null, "P3", ColorFigures.GREEN, false));

        ServerRunnableTestable srt = new ServerRunnableTestable();
        srt.setPlayers(players);
        srt.setPlayerCheatStatus("P1", true);
        srt.checkCheat("P2");

        Mockito.verify(srt.networkServer, times(3)).sendEvent(recipientCaptor.capture(), eventCaptor.capture());

        assertEquals(eventCaptor.getAllValues().get(0).message,
                "Du wurdest beim Schummeln erwischt und verlierst 4 Punkte.");
    }

}
