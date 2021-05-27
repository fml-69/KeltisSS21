package com.groupd.keltis.scenes.lobby;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.management.SceneManager;
import com.groupd.keltis.network.NetworkClient;
import com.groupd.keltis.network.events.JoinEvent;
import com.groupd.keltis.network.events.NetworkEvent;
import com.groupd.keltis.network.events.StartGameEvent;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.utils.AssetPaths;

public class LobbyScene extends AbstractScene {

    List<String> uIList;
    Array <String> playerList = new Array<>();

    public LobbyScene(Keltis keltis) {
        super(keltis);

        stage = new Stage(new ScreenViewport());

    }

    @Override
    public void onNetworkEvent(NetworkEvent event) {
        if(event instanceof JoinEvent){
            playerList.add(((JoinEvent) event).nick);
            uIList.setItems(playerList);
        }
        else if(event instanceof StartGameEvent){
            keltis.sceneManager.setScene(SceneManager.GAMESTATE.PLAYING);
        }
    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

    }


    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal(AssetPaths.MENU_ASSET));

        VerticalGroup vg = new VerticalGroup().space(3).pad(5).fill();
        vg.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(vg);


        uIList = new List<>(skin);
        vg.addActor(uIList);


        TextButton readyButton = new TextButton("Ready", skin);
        vg.addActor(readyButton);

        readyButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                NetworkClient.INSTANCE.sendEvent(new StartGameEvent());
                return true;
            }
        });

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
        NetworkClient.INSTANCE.receiveEvents();
    }

}

