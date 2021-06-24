package com.groupd.keltis.scenes.board.road_cards;

import com.badlogic.gdx.graphics.Texture;

public class Pointcard extends Roadcards {
    private int points;
    public Pointcard(String name, Texture texture, Position position, int points) {
        super(name,texture,position);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
