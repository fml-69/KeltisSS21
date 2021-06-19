package com.groupd.keltis.scenes.board;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ShamrockDialog extends Dialog {

    private Label label = new Label("",getSkin());

    public ShamrockDialog(String title, Skin skin) {
        super(title, skin);
        init();
    }

    public void init() {
        text(label);
    }

    public void setText(String currentPlayerNick){
        this.label.setText("Die Figur von "+currentPlayerNick+" darf ein Feld weiterziehen!");
    }

    public Label getLabel() {
        return label;
    }
}