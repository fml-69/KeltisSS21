package com.groupd.keltis.scenes.board.road_cards;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class Abstract_roadcards {
    private Image image;
    public Abstract_roadcards (Image image){
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
