package com.groupd.keltis.scenes.instruction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.management.SceneManager;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.utils.AssetPaths;

public class TextInstructionScreen extends AbstractScene {

    private Skin skin;

    private Texture [] instructions;
    private Image image;

    private int counter;
    private int framecounter;

    public TextInstructionScreen(final Keltis keltis) {
        super(keltis);

        stage = new Stage(new ScreenViewport());
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        instructions = new Texture[9];
    }

    @Override
    public void update(float delta) {
        Gdx.gl.glClearColor(1f, 150/255f, 40/255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 150/255f, 40/255f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        if(Gdx.input.isTouched()&&framecounter >= 30){
            if(counter == 9){
                keltis.sceneManager.setScene(SceneManager.GAMESTATE.SETTINGS);
                return;
            }
            image.setDrawable(new TextureRegionDrawable(new TextureRegion(instructions[counter])));
            counter++;
            framecounter=0;
        }
        if(framecounter<Integer.MAX_VALUE){
            framecounter++;
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal(AssetPaths.MENU_ASSET));

        instructions[0] = (Texture) keltis.assetManager.get(AssetPaths.INSTRUCTION_ASSET_1);
        instructions[1] = (Texture) keltis.assetManager.get(AssetPaths.INSTRUCTION_ASSET_2);
        instructions[2] = (Texture) keltis.assetManager.get(AssetPaths.INSTRUCTION_ASSET_3);
        instructions[3] = (Texture) keltis.assetManager.get(AssetPaths.INSTRUCTION_ASSET_4);
        instructions[4] = (Texture) keltis.assetManager.get(AssetPaths.INSTRUCTION_ASSET_5);
        instructions[5] = (Texture) keltis.assetManager.get(AssetPaths.INSTRUCTION_ASSET_6);
        instructions[6] = (Texture) keltis.assetManager.get(AssetPaths.INSTRUCTION_ASSET_7);
        instructions[7] = (Texture) keltis.assetManager.get(AssetPaths.INSTRUCTION_ASSET_8);
        instructions[8] = (Texture) keltis.assetManager.get(AssetPaths.INSTRUCTION_ASSET_9);

        image = new Image(instructions[0]);

        counter = 0;
        framecounter = 0;

        stage.addActor(image);
        counter++;
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
