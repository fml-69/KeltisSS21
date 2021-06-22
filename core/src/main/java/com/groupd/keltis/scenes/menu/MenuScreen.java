package com.groupd.keltis.scenes.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.management.SceneManager;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.utils.AssetPaths;

public class MenuScreen extends AbstractScene {

    private Table table;
    private Skin skin;
    private TextButton newGame;
    private TextButton preferences;
    private TextButton exit;

    private Image board;

    public MenuScreen(final Keltis keltis){
        super(keltis);

        stage = new Stage(new ScreenViewport());
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        initTable();

        initChangeListenerButtons();
    }

    private void initTable(){
        table = new Table();

        board = new Image((Texture) keltis.assetManager.get(AssetPaths.BOARD_BACKGROUND));

        table.setFillParent(true);
        //table.setDebug(true);
        stage.addActor(board);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal(AssetPaths.MENU_ASSET));

        newGame = new TextButton("SPIEL STARTEN", skin);
        preferences = new TextButton("OPTIONEN", skin);
        exit = new TextButton("BEENDEN", skin);

        //before .uniform and .fill methods were used
        table.add(newGame).width(Gdx.graphics.getWidth() * 1/5f).height(Gdx.graphics.getHeight() * 1/6f);
        table.row().pad(50, 0, 50, 0);
        table.add(preferences).width(Gdx.graphics.getWidth() * 1/5f).height(Gdx.graphics.getHeight() * 1/6f);
        table.row();
        table.add(exit).width(Gdx.graphics.getWidth() * 1/5f).height(Gdx.graphics.getHeight() * 1/6f);

    }

    private void initChangeListenerButtons(){
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                keltis.sceneManager.setScene(SceneManager.GAMESTATE.LOGIN);
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                keltis.sceneManager.setScene(SceneManager.GAMESTATE.SETTINGS);
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 150/255f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
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
    public void dispose() {
        // TODO Auto-generated method stub
    }
}
