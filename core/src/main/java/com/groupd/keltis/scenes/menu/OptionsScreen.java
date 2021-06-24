package com.groupd.keltis.scenes.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.management.SceneManager;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.utils.AssetPaths;

public class OptionsScreen extends AbstractScene {

    private Table table;

    private TextButton mainMenuTB;
    private TextButton instructionsTB;
    private TextButton audioButton;
    private Skin skin;

    public OptionsScreen(Keltis keltis) {

        super(keltis);


        stage = new Stage(new ScreenViewport());
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        initTable();
        initChangeListenerButtons();
    }

    private void initTable(){
        table = new Table();

        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal(AssetPaths.MENU_ASSET));

        mainMenuTB = new TextButton("HAUPTMENU", skin);
        instructionsTB = new TextButton("SPIELANLEITUNG", skin);
        audioButton = new TextButton("MUSIK AUSSCHALTEN", skin);

        table.add(instructionsTB).width(Gdx.graphics.getWidth() * 1/5f).height(Gdx.graphics.getHeight() * 1/6f);
        table.row().pad(50, 0, 50, 0);
        table.add(audioButton).width(Gdx.graphics.getWidth() * 1/5f).height(Gdx.graphics.getHeight() * 1/6f);
        table.row();
        table.add(mainMenuTB).width(Gdx.graphics.getWidth() * 1/5f).height(Gdx.graphics.getHeight() * 1/6f);
        table.row();
    }

    private void initChangeListenerButtons() {
        mainMenuTB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                keltis.sceneManager.setScene(SceneManager.GAMESTATE.MENU);
            }
        });
        instructionsTB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                keltis.sceneManager.setScene(SceneManager.GAMESTATE.TEXT_INSTRUCTIONS);
            }
        });
        audioButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(keltis.getMusic().isPlaying()){
                    audioButton.setText("MUSIK ANSCHALTEN");
                    keltis.getMusic().stop();
                }else{
                    audioButton.setText("MUSIK AUSSCHALTEN");
                    keltis.getMusic().play();
                }
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
    public void dispose() {

    }
}
