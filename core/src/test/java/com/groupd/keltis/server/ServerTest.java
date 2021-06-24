package com.groupd.keltis.server;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.network.NetworkServerInterface;
import com.groupd.keltis.network.events.CardDisplaySyncEvent;
import com.groupd.keltis.network.events.JoinEvent;
import com.groupd.keltis.network.events.NextPlayerEvent;
import com.groupd.keltis.network.events.StartGameEvent;
import com.groupd.keltis.network.events.TurnEvent;
import com.groupd.keltis.utils.AssetPaths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServerTest {
    ServerRunnable serverRunnable;
    @Mock
    private Keltis keltisMock;
    @Mock
    private AssetManager assetManagerMock;
    @Mock
    private Texture textureMock;
    @Mock
    private NetworkServerInterface networkServerInterface;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Gdx.app = mock(Application.class);

        keltisMock.assetManager = assetManagerMock;
        when(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_BLUE)).thenReturn(textureMock);
        when(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_RED)).thenReturn(textureMock);
        /*when(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_GREEN)).thenReturn(textureMock);
        when(keltisMock.assetManager.get(AssetPaths.BOARD_PLAYER_YELLOW)).thenReturn(textureMock);*/

        serverRunnable = new ServerRunnable(keltisMock, networkServerInterface);
    }

    @Test
    public void testJoin() {
        serverRunnable.join("Test");
        verify(networkServerInterface,times(1)).broadCast(isA(JoinEvent.class));
    }

    @Test
    public void testJoinMultiple() {
        serverRunnable.join("Test");
        serverRunnable.join("Test2");
        verify(networkServerInterface,times(2)).broadCast(isA(JoinEvent.class));
        verify(networkServerInterface,times(1)).sendEvent(eq("Test2"), isA(JoinEvent.class));
    }

    @Test
    public void testStartGameHost() {
        serverRunnable.join("Test");
        serverRunnable.join("Test2");
        StartGameEvent event = new StartGameEvent();
        serverRunnable.onStartGame(event, "Test");
        verify(networkServerInterface,times(1)).broadCast(event);
    }

    @Test
    public void testStartNonHost() {
        serverRunnable.join("Test");
        serverRunnable.join("Test2");
        StartGameEvent event = new StartGameEvent();
        serverRunnable.onStartGame(event, "Test2");
        verify(networkServerInterface,times(0)).broadCast(event);
    }

    @Test
    public void testBroadcast() {
        serverRunnable.join("Test");
        serverRunnable.join("Test2");
        TurnEvent turnEvent = new TurnEvent();
        serverRunnable.onTurn(turnEvent);
        verify(networkServerInterface,times(1)).broadCast(turnEvent);

        CardDisplaySyncEvent cardDisplaySyncEvent = new CardDisplaySyncEvent();
        serverRunnable.branchStackSync(cardDisplaySyncEvent);
        verify(networkServerInterface,times(1)).broadCast(cardDisplaySyncEvent);

        NextPlayerEvent nextPlayerEvent = new NextPlayerEvent();
        serverRunnable.nextPlayer(nextPlayerEvent);
        verify(networkServerInterface,times(1)).broadCast(nextPlayerEvent);
    }
}
