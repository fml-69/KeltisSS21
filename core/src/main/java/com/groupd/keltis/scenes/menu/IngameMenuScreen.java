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

public class IngameMenuScreen  extends AbstractScene {
    private Table table;
    private Skin skin;
    private TextButton continueTB;
    private TextButton exitToMainMenu;

    public IngameMenuScreen(final Keltis keltis){
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

        continueTB = new TextButton("FORTSETZEN", skin);
        exitToMainMenu = new TextButton("HAUPTMENU", skin);

        table.add(continueTB).width(Gdx.graphics.getWidth() * 1/5f).height(Gdx.graphics.getHeight() * 1/6f);
        table.row().pad(50, 0, 50, 0);
        table.add(exitToMainMenu).width(Gdx.graphics.getWidth() * 1/5f).height(Gdx.graphics.getHeight() * 1/6f);
    }

    private void initChangeListenerButtons(){
        exitToMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                keltis.sceneManager.setScene(SceneManager.GAMESTATE.MENU);
            }
        });

        continueTB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                keltis.sceneManager.setScene(SceneManager.GAMESTATE.PLAYING);
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 150/255f, 20/255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
}
