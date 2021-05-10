package com.groupd.keltis;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.groupd.keltis.management.SceneManager;
import com.groupd.keltis.utils.AssetPaths;

public class Keltis extends Game {

	public SceneManager sceneManager;
	public AssetManager assetManager;

	public SpriteBatch batch;

	public static int SCALE_WIDTH = 1920;
	public static int SCALE_HEIGHT = 1080;
	public int scaleFactorX;
	public int scaleFactorY;

	public static int FPS = 60;


	@Override
	public void create () {
		if(Gdx.graphics.getHeight()<Gdx.graphics.getWidth()){
			scaleFactorX = Gdx.graphics.getWidth()/SCALE_WIDTH;
			scaleFactorY = Gdx.graphics.getHeight()/SCALE_HEIGHT;
		} else{
			scaleFactorX = Gdx.graphics.getHeight()/SCALE_WIDTH;
			scaleFactorY = Gdx.graphics.getWidth()/SCALE_HEIGHT;
		}

		batch = new SpriteBatch();
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
		assetManager.dispose();
		sceneManager.dispose();
	}

	private void loadAssets(){
		assetManager.load(AssetPaths.BOARD_BACKGROUND, Texture.class);
		assetManager.load(AssetPaths.BOARD_BRANCHES, Texture.class);

		assetManager.load(AssetPaths.BOARD_HUD_BAR, Texture.class);
		assetManager.load(AssetPaths.BOARD_PLAYER_BLUE, Texture.class);
		assetManager.load(AssetPaths.ROADCARD_SHAMROCK,Texture.class);
		assetManager.load(AssetPaths.ROADCARD_WISHSTONE,Texture.class);
		assetManager.load(AssetPaths.ROADCARD_POINTCARDONE,Texture.class);
		assetManager.load(AssetPaths.ROADCARD_POINTCARDTWO,Texture.class);
		assetManager.load(AssetPaths.ROADCARD_POINTCARDTHREE,Texture.class);
		assetManager.load(AssetPaths.BOARD_PLAYER_GREEN, Texture.class);
		assetManager.load(AssetPaths.BOARD_PLAYER_RED, Texture.class);
		assetManager.load(AssetPaths.BOARD_PLAYER_YELLOW, Texture.class);

		assetManager.load(AssetPaths.UI_SKIN, Skin.class);
		//assetManager.load(AssetPaths.MENU_ASSET, Texture.class);
		assetManager.finishLoading();
	}
}