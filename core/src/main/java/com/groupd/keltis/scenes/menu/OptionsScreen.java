package com.groupd.keltis.scenes.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.management.SceneManager;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.utils.AssetPaths;

public class OptionsScreen extends AbstractScene {

    private Table table;

    private TextButton mainMenuTB;
    private TextButton instructionsTB;
    private TextButton audioButton;
    private OrthographicCamera camera;
    private Image image;

    private Skin skin;

    public OptionsScreen(Keltis keltis) {

        super(keltis);

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT);
        stage = new Stage(new StretchViewport(Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT, camera));
        keltis.batch.setProjectionMatrix(camera.combined);
        image = new Image((Texture) keltis.assetManager.get(AssetPaths.BOARD_BACKGROUND));
    }

    private void initTable(){
        table = new Table();

        table.setFillParent(true);
        
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal(AssetPaths.MENU_ASSET));

        mainMenuTB = new TextButton("HAUPTMENU", skin);
        instructionsTB = new TextButton("SPIELANLEITUNG", skin);
        audioButton = new TextButton("MUSIK AUSSCHALTEN", skin);

        table.add(instructionsTB).width(Keltis.SCALE_WIDTH/5f).height(Keltis.SCALE_HEIGHT/6f);
        table.row().pad(50, 0, 50, 0);
        table.add(audioButton).width(Keltis.SCALE_WIDTH/5f).height(Keltis.SCALE_HEIGHT/6f);
        table.row();
        table.add(mainMenuTB).width(Keltis.SCALE_WIDTH/5f).height(Keltis.SCALE_HEIGHT/6f);
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
        stage.addActor(image);
        initTable();
        initChangeListenerButtons();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
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
