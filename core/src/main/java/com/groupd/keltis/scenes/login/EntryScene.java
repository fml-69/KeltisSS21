package com.groupd.keltis.scenes.login;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.management.SceneManager;
import com.groupd.keltis.network.NetworkClient;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.server.ServerRunnable;
import com.groupd.keltis.utils.AssetPaths;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class EntryScene extends AbstractScene {

    private TextField text;
    private Label errorLabel;
    private TextField textIP;
    private TextField textPort;



    public EntryScene(Keltis keltis) {
        super(keltis);

        stage = new Stage(new ScreenViewport());

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


        Label label = new Label("Enter your name", skin);
        vg.addActor(label);

        text = new TextField("", skin);
        vg.addActor(text);

        Label labelIP = new Label("Enter IP", skin);
        vg.addActor(labelIP);
        textIP = new TextField("127.0.0.1", skin);
        vg.addActor(textIP);

        Label labelPort = new Label("Enter port", skin);
        vg.addActor(labelPort);
        textPort = new TextField("20000", skin);
        vg.addActor(textPort);


        Button hostButton = new TextButton("Host", skin);
        vg.addActor(hostButton);

        Button startButton = new TextButton("Start", skin);
        vg.addActor(startButton);

        errorLabel = new Label("", skin);
        vg.addActor(errorLabel);


        hostButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                CountDownLatch serverStartLatch = new CountDownLatch(1);

                // start server & provide port
                Thread serverThread = new Thread(new ServerRunnable(Integer.parseInt(textPort.getText()), serverStartLatch));
                serverThread.setDaemon(true);
                serverThread.start();

                try {
                    if(!serverStartLatch.await(2, TimeUnit.SECONDS)){
                        errorLabel.setText("Latch failed!");
                    } else {
                        requestEntry();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    errorLabel.setText("Could not start server.");
                    Thread.currentThread().interrupt();
                }

                return true;
            }
        });

        startButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                requestEntry();
                return true;
            }
        });



    }

    private void requestEntry() {
        if (text.getText().isEmpty()) {
            errorLabel.setText("No name, no game.");
        }
        else{

            // create client & connect it to server
            NetworkClient client = new NetworkClient(textIP.getText(), Integer.parseInt(textPort.getText()), text.getText());
            if(!client.isConnected()){
                errorLabel.setText(client.getMessage());

            } else {
                keltis.sceneManager.setScene(SceneManager.GAMESTATE.PLAYING);
            }

        }
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
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
