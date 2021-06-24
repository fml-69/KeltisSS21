package com.groupd.keltis;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.groupd.keltis.management.GameLogic;
import com.groupd.keltis.management.SceneManager;
import com.groupd.keltis.utils.AssetPaths;
import com.groupd.keltis.utils.CardHelper;
import com.groupd.keltis.utils.PositionHelper;
import com.groupd.keltis.utils.RoadcardsHelper;

public class Keltis extends Game {

	public SceneManager sceneManager;
	public AssetManager assetManager;
	public GameLogic gameLogic;
	public SpriteBatch batch;
	public CardHelper cardHelper;
	public PositionHelper positionHelper;
	public RoadcardsHelper roadcardsHelper;

	public static int SCALE_WIDTH = 1920;
	public static int SCALE_HEIGHT = 1080;
	public int scaleFactorX;
	public int scaleFactorY;

	public static int FPS = 60;

	private Music music;
	private Sound selectCard;
	private Sound playCard;


	@Override
	public void create () {

		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/background_music.wav"));
		music.setLooping(true);
		music.play();

		selectCard = Gdx.audio.newSound(Gdx.files.internal("sounds/select_card.wav"));
		playCard = Gdx.audio.newSound(Gdx.files.internal("sounds/play_card.wav"));

		if(Gdx.graphics.getHeight()<Gdx.graphics.getWidth()){
			scaleFactorX = Gdx.graphics.getWidth()/SCALE_WIDTH;
			scaleFactorY = Gdx.graphics.getHeight()/SCALE_HEIGHT;
		} else{
			scaleFactorX = Gdx.graphics.getHeight()/SCALE_WIDTH;
			scaleFactorY = Gdx.graphics.getWidth()/SCALE_HEIGHT;
		}
		gameLogic = new GameLogic();
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		loadAssets();
		sceneManager = new SceneManager(this);
		cardHelper = new CardHelper(this);
		positionHelper = new PositionHelper();
		roadcardsHelper = new RoadcardsHelper(this);

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
		assetManager.load(AssetPaths.BOARD_MENU_BUTTON, Texture.class);
		assetManager.load(AssetPaths.BOARD_PLAYER_BLUE, Texture.class);
		assetManager.load(AssetPaths.ROADCARD_SHAMROCK,Texture.class);
		assetManager.load(AssetPaths.ROADCARD_WISHSTONE,Texture.class);
		assetManager.load(AssetPaths.ROADCARD_POINTCARDONE,Texture.class);
		assetManager.load(AssetPaths.ROADCARD_POINTCARDTWO,Texture.class);
		assetManager.load(AssetPaths.ROADCARD_POINTCARDTHREE,Texture.class);
		assetManager.load(AssetPaths.BOARD_PLAYER_GREEN, Texture.class);
		assetManager.load(AssetPaths.BOARD_PLAYER_RED, Texture.class);
		assetManager.load(AssetPaths.BOARD_PLAYER_YELLOW, Texture.class);

		assetManager.load(AssetPaths.CARD_BLUE_ZERO, Texture.class);
		assetManager.load(AssetPaths.CARD_BLUE_ONE, Texture.class);
		assetManager.load(AssetPaths.CARD_BLUE_TWO, Texture.class);
		assetManager.load(AssetPaths.CARD_BLUE_THREE, Texture.class);
		assetManager.load(AssetPaths.CARD_BLUE_FOUR, Texture.class);
		assetManager.load(AssetPaths.CARD_BLUE_FIVE, Texture.class);
		assetManager.load(AssetPaths.CARD_BLUE_SIX, Texture.class);
		assetManager.load(AssetPaths.CARD_BLUE_SEVEN, Texture.class);
		assetManager.load(AssetPaths.CARD_BLUE_EIGHT, Texture.class);
		assetManager.load(AssetPaths.CARD_BLUE_NINE, Texture.class);
		assetManager.load(AssetPaths.CARD_BLUE_TEN, Texture.class);

		assetManager.load(AssetPaths.CARD_GREEN_ZERO, Texture.class);
		assetManager.load(AssetPaths.CARD_GREEN_ONE, Texture.class);
		assetManager.load(AssetPaths.CARD_GREEN_TWO, Texture.class);
		assetManager.load(AssetPaths.CARD_GREEN_THREE, Texture.class);
		assetManager.load(AssetPaths.CARD_GREEN_FOUR, Texture.class);
		assetManager.load(AssetPaths.CARD_GREEN_FIVE, Texture.class);
		assetManager.load(AssetPaths.CARD_GREEN_SIX, Texture.class);
		assetManager.load(AssetPaths.CARD_GREEN_SEVEN, Texture.class);
		assetManager.load(AssetPaths.CARD_GREEN_EIGHT, Texture.class);
		assetManager.load(AssetPaths.CARD_GREEN_NINE, Texture.class);
		assetManager.load(AssetPaths.CARD_GREEN_TEN, Texture.class);

		assetManager.load(AssetPaths.CARD_PURPLE_ZERO, Texture.class);
		assetManager.load(AssetPaths.CARD_PURPLE_ONE, Texture.class);
		assetManager.load(AssetPaths.CARD_PURPLE_TWO, Texture.class);
		assetManager.load(AssetPaths.CARD_PURPLE_THREE, Texture.class);
		assetManager.load(AssetPaths.CARD_PURPLE_FOUR, Texture.class);
		assetManager.load(AssetPaths.CARD_PURPLE_FIVE, Texture.class);
		assetManager.load(AssetPaths.CARD_PURPLE_SIX, Texture.class);
		assetManager.load(AssetPaths.CARD_PURPLE_SEVEN, Texture.class);
		assetManager.load(AssetPaths.CARD_PURPLE_EIGHT, Texture.class);
		assetManager.load(AssetPaths.CARD_PURPLE_NINE, Texture.class);
		assetManager.load(AssetPaths.CARD_PURPLE_TEN, Texture.class);

		assetManager.load(AssetPaths.CARD_RED_ZERO, Texture.class);
		assetManager.load(AssetPaths.CARD_RED_ONE, Texture.class);
		assetManager.load(AssetPaths.CARD_RED_TWO, Texture.class);
		assetManager.load(AssetPaths.CARD_RED_THREE, Texture.class);
		assetManager.load(AssetPaths.CARD_RED_FOUR, Texture.class);
		assetManager.load(AssetPaths.CARD_RED_FIVE, Texture.class);
		assetManager.load(AssetPaths.CARD_RED_SIX, Texture.class);
		assetManager.load(AssetPaths.CARD_RED_SEVEN, Texture.class);
		assetManager.load(AssetPaths.CARD_RED_EIGHT, Texture.class);
		assetManager.load(AssetPaths.CARD_RED_NINE, Texture.class);
		assetManager.load(AssetPaths.CARD_RED_TEN, Texture.class);

		assetManager.load(AssetPaths.CARD_YELLOW_ZERO, Texture.class);
		assetManager.load(AssetPaths.CARD_YELLOW_ONE, Texture.class);
		assetManager.load(AssetPaths.CARD_YELLOW_TWO, Texture.class);
		assetManager.load(AssetPaths.CARD_YELLOW_THREE, Texture.class);
		assetManager.load(AssetPaths.CARD_YELLOW_FOUR, Texture.class);
		assetManager.load(AssetPaths.CARD_YELLOW_FIVE, Texture.class);
		assetManager.load(AssetPaths.CARD_YELLOW_SIX, Texture.class);
		assetManager.load(AssetPaths.CARD_YELLOW_SEVEN, Texture.class);
		assetManager.load(AssetPaths.CARD_YELLOW_EIGHT, Texture.class);
		assetManager.load(AssetPaths.CARD_YELLOW_NINE, Texture.class);
		assetManager.load(AssetPaths.CARD_YELLOW_TEN, Texture.class);

		assetManager.load(AssetPaths.CARD_BACK, Texture.class);

		assetManager.load(AssetPaths.CARD_EMPTY_STACK_BLUE, Texture.class);
		assetManager.load(AssetPaths.CARD_EMPTY_STACK_GREEN, Texture.class);
		assetManager.load(AssetPaths.CARD_EMPTY_STACK_PURPLE, Texture.class);
		assetManager.load(AssetPaths.CARD_EMPTY_STACK_RED, Texture.class);
		assetManager.load(AssetPaths.CARD_EMPTY_STACK_YELLOW, Texture.class);

		assetManager.load(AssetPaths.CARD_EMPTY_HANDCARD, Texture.class);

		assetManager.load(AssetPaths.CARD_HIGHLIGHT, Texture.class);
		assetManager.load(AssetPaths.CARD_HIGHLIGHT_PLAYABLE, Texture.class);
		
		assetManager.load(AssetPaths.MENU_ASSET, Skin.class);
		assetManager.load(AssetPaths.DIALOG_SKIN, Skin.class);

		assetManager.load(AssetPaths.INSTRUCTION_ASSET_1, Texture.class);
		assetManager.load(AssetPaths.INSTRUCTION_ASSET_2, Texture.class);
		assetManager.load(AssetPaths.INSTRUCTION_ASSET_3, Texture.class);
		assetManager.load(AssetPaths.INSTRUCTION_ASSET_4, Texture.class);
		assetManager.load(AssetPaths.INSTRUCTION_ASSET_5, Texture.class);
		assetManager.load(AssetPaths.INSTRUCTION_ASSET_6, Texture.class);
		assetManager.load(AssetPaths.INSTRUCTION_ASSET_7, Texture.class);
		assetManager.load(AssetPaths.INSTRUCTION_ASSET_8, Texture.class);
		assetManager.load(AssetPaths.INSTRUCTION_ASSET_9, Texture.class);

		assetManager.load(AssetPaths.MENU_BACKGROUND_0, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_1, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_2, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_3, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_4, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_5, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_6, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_7, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_8, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_9, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_10, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_11, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_12, Texture.class);
		assetManager.load(AssetPaths.MENU_BACKGROUND_13, Texture.class);

		assetManager.finishLoading();
	}

	public Sound getSelectCard() {
		return selectCard;
	}

	public Sound getPlayCard() {
		return playCard;
	}

	public Music getMusic(){
		return music;
	}
}