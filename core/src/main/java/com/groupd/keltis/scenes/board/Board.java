package com.groupd.keltis.scenes.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.accelerometer.ShakeDetector;
import com.groupd.keltis.scenes.AbstractScene;
import com.groupd.keltis.utils.AssetPaths;


public class Board extends AbstractScene {

    private OrthographicCamera camera;
    private World world;
    private Texture board;
    private Texture branches;


    public Board(final Keltis keltis){
        super(keltis);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT);
        keltis.batch.setProjectionMatrix(camera.combined);
        keltis.shapeRenderer.setProjectionMatrix(camera.combined);
        world = new World(new Vector2(0f, 0f), false);

        board = keltis.assetManager.get(AssetPaths.BOARD_BACKGROUND);
        branches = keltis.assetManager.get(AssetPaths.BOARD_BRANCHES);
    }

    @Override
    public void update(float delta) {
        world.step(1/(float)Keltis.FPS, 6, 2);
        stage.act(delta);
        if(ShakeDetector.phoneIsShaking()) {
            ShakeDetector.wasShaken();
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        keltis.batch.begin();
        keltis.batch.draw(board,0,0, Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT);
        keltis.batch.draw(branches,0,0, Keltis.SCALE_WIDTH, Keltis.SCALE_HEIGHT);
        keltis.batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void show() {

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
