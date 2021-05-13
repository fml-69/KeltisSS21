package com.groupd.keltis.scenes.board;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ShamrockDialog extends Dialog {
    public ShamrockDialog(String title, Skin skin) {
        super(title, skin);
        init();
    }

    private void init() {
        Label label = new Label("Deine Figur zieht ein Feld weiter", getSkin());
        text(label);
    }
}