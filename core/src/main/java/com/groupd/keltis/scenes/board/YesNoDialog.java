package com.groupd.keltis.scenes.board;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class YesNoDialog extends Dialog {

    private Callback callback;

    public YesNoDialog(String title, Skin skin, Callback callback) {
        super(title, skin);
        init();
        this.callback = callback;
    }

    private void init() {
        Label label = new Label("Bist du sicher, dass einer deiner Gegenspieler schummelt?", getSkin());
        text(label);

        button("Ja", true);
        button("Nein", false);
    }

    @Override
    protected void result(Object bool) {
        callback.result((boolean) bool);
    }

    interface Callback {
        void result(boolean result);
    }
}