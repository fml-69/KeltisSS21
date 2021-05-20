package com.groupd.keltis.scenes.board;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;




public class InfoDialog extends Dialog {

    private boolean cheated;

    public InfoDialog(String title, Skin skin,boolean cheated) {
        super(title, skin);
        init();
        this.cheated=cheated;
    }

    private void init() {
        if(cheated) {
            Label label = new Label("Ein Spieler hat geschummelt!", getSkin());
            text(label);

            button("Ok");
        }
        else{
            Label label = new Label("Kein Spieler hat geschummelt!", getSkin());
            text(label);

            button("Ok");
        }
    }

}
