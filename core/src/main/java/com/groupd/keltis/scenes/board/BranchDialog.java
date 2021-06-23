package com.groupd.keltis.scenes.board;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class BranchDialog extends Dialog {
    private Callback callback;

    public BranchDialog(String title, Skin skin, Callback callback) {
        super(title, skin);
        this.callback = callback;
        init();
    }
    private void init() {
        Label label = new Label("Waehle einen Pfad fuer deinen Extrazug:", getSkin());
        text(label);

        button("Gruen", 1);
        button("Gelb", 2);
        button("Rot", 3);
        button("Blau", 4);
        button("Lila", 5);
    }
    @Override
    protected void result(Object i) {
        callback.result((int) i);
    }

    interface Callback {
        void result(int result);
    }
}
