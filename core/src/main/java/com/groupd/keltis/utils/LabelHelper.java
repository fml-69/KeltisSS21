package com.groupd.keltis.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LabelHelper {

    public static Label label(int x, int y){
        Label label = new Label("", new Skin(Gdx.files.internal("skin_shade/uiskin.json")));
        label.setWidth(200);
        label.setHeight(100);
        label.setFontScale(2);
        label.setPosition(x, y);
        return label;
    }

    public static Label labelWithColor(ColorFigures colorFigures, int x, int y) {
        Label label = new Label("", new Skin(Gdx.files.internal("skin_shade/uiskin.json")));
        label.setWidth(200);
        label.setHeight(100);
        label.setFontScale(2);
        switch (colorFigures){
            case BLUE:
                label.setColor(Color.BLUE);
                break;
            case RED:
                label.setColor(Color.RED);
                break;
            case YELLOW:
                label.setColor(Color.YELLOW);
                break;
            case GREEN:
                label.setColor(Color.GREEN);
                break;
            default:
        }
        label.setPosition(x, y);
        return label;
    }
}
