package com.groupd.keltis.scenes.board;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class IngameMenuDialog extends Dialog {

    private Callback callback;

    public IngameMenuDialog(String title, Skin skin, Callback callback) {
        super(title, skin);
        init();
        this.callback = callback;
    }

    private void init() {
        Label label = new Label("Willst du zum Hauptmenu gehen?", getSkin());
        text(label);

        button("Hauptmenu", true);
        button("Spiel fortsetzen", false);
    }

    @Override
    protected void result(Object bool) {
        callback.result((boolean) bool);
    }

    interface Callback {
        void result(boolean result);
    }
}