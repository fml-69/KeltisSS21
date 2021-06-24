package com.groupd.keltis.scenes.board;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;




public class InfoDialog extends Dialog {

    public String message;

    public InfoDialog(String title, Skin skin, String message) {
        super(title, skin);
        this.message = message;
        init();
    }

    private void init() {
        Label label = new Label(message, getSkin());
        text(label);
        button("Ok");
    }

}
