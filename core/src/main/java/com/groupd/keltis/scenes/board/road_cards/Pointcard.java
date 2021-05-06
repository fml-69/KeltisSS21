package com.groupd.keltis.scenes.board.road_cards;

import com.badlogic.gdx.graphics.Texture;

public class Pointcard extends Roadcards {
    private int points;
    public Pointcard(Texture texture, Position position, int points) {
        super(texture,position);
        this.points = points;
    }
}
