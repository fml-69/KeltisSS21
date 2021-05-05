package com.groupd.keltis.scenes.login;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.management.SceneManager;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.utils.AssetPaths;

public class EntryScene extends AbstractScene {

    private Stage stage;
    private Button startButton;
    private TextField text;
    private Label errorLabel;

    public EntryScene(Keltis keltis) {
        super(keltis);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {

        Skin skin = keltis.assetManager.get(AssetPaths.UI_SKIN);

        VerticalGroup vg = new VerticalGroup().space(3).pad(5).fill();
        vg.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(vg);


        Label label = new Label("Enter your name", skin);
        //stage.addActor(label);
        vg.addActor(label);

        text = new TextField("", skin);
        text.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.ENTER){
                    requestEntry();
                    return true;
                }
                return super.keyDown(event, keycode);
            }
        });

        vg.addActor(text);

        startButton = new TextButton("Start", skin);
        //stage.addActor(startButton);
        vg.addActor(startButton);

        errorLabel = new Label("", skin);
        vg.addActor(errorLabel);


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
            keltis.sceneManager.setScene(SceneManager.GAMESTATE.PLAYING);

        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resize(int width, int height) {
        //super.resize(width, height);
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
        //super.dispose();
        stage.dispose();

    }
}
