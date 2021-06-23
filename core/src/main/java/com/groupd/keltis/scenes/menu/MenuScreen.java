package com.groupd.keltis.scenes.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
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

    private Image background;
    private Texture [] background_animated;

    private OrthographicCamera camera;

    private int framecounter, backgroundcounter;

    public MenuScreen(final Keltis keltis){
        super(keltis);

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT);

        background_animated = new Texture[14];
        stage = new Stage(new StretchViewport(Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT, camera));
        keltis.batch.setProjectionMatrix(camera.combined);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        initBackgroundTextures();
        background = new Image(background_animated[0]);
        stage.addActor(background);

        framecounter = 0;
        backgroundcounter = 0;

        initTable();

        initChangeListenerButtons();


    }

    private void initTable(){
        table = new Table();



        table.setFillParent(true);
        //table.setDebug(true);

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

    private void initBackgroundTextures() {
        background_animated[0] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_0);
        background_animated[1] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_1);
        background_animated[2] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_2);
        background_animated[3] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_3);
        background_animated[4] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_4);
        background_animated[5] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_5);
        background_animated[6] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_6);
        background_animated[7] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_7);
        background_animated[8] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_8);
        background_animated[9] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_9);
        background_animated[10] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_10);
        background_animated[11] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_11);
        background_animated[12] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_12);
        background_animated[13] = (Texture) keltis.assetManager.get(AssetPaths.MENU_BACKGROUND_13);
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
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        if(framecounter>2&&backgroundcounter!=0){
            framecounter= 0;
            background.setDrawable(new TextureRegionDrawable(new TextureRegion(background_animated[backgroundcounter])));
            backgroundcounter++;
            if(backgroundcounter==14){
                backgroundcounter = 0;
            }
        }
        if(backgroundcounter==0&&framecounter>3){
            if(backgroundcounter==0&&framecounter>60){
                backgroundcounter++;
            }else{
                background.setDrawable(new TextureRegionDrawable(new TextureRegion(background_animated[backgroundcounter])));
            }
        }
        framecounter++;
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
