package com.groupd.keltis;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.groupd.keltis.management.SceneManager;
import com.groupd.keltis.utils.AssetPaths;

public class Keltis extends Game {

	public SceneManager sceneManager;
	public AssetManager assetManager;

	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;

	public static int SCALE_WIDTH = 1920;
	public static int SCALE_HEIGHT = 1080;
	public static int FPS = 60;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		assetManager = new AssetManager();
		loadAssets();
		sceneManager = new SceneManager(this);
	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		assetManager.dispose();
		sceneManager.dispose();
	}

	private void loadAssets(){
		assetManager.load(AssetPaths.BOARD_BACKGROUND, Texture.class);
		assetManager.load(AssetPaths.BOARD_BRANCHES, Texture.class);
		//assetManager.load(AssetPaths.MENU_ASSET, Texture.class);
		assetManager.finishLoading();
	}
}
