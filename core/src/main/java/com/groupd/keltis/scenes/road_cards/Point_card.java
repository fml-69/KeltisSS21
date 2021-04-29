package com.groupd.keltis.scenes.road_cards;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Point_card extends Abstract_roadcards {
    private int points;
    public Point_card(Image image, int points) {
        super(image);
        this.points = points;
    }
}
