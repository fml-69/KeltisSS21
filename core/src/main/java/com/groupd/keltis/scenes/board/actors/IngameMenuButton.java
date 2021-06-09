package com.groupd.keltis.scenes.board.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.groupd.keltis.Keltis;
import com.groupd.keltis.management.SceneManager;

public class IngameMenuButton extends Actor {

    private Button button;

    public IngameMenuButton(Keltis keltis, Texture texture){

        button = new Button((Drawable) new TextureRegionDrawable(new TextureRegion(texture)));

        button.setPosition(50, 50);

        button.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                keltis.sceneManager.setScene(SceneManager.GAMESTATE.INGAME_MENU);
                return true;
            }
        });
    }

    public Button getButton(){
        return this.button;
    }

}
