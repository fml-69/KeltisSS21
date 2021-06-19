package com.groupd.keltis.scenes.board.road_cards;

public class Position {
    private String name;
    private int x;
    private int y;
    private int branch;
    private int field;

    public Position(String name, int x, int y, int branch, int field) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.branch = branch;
        this.field = field;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBranch() {
        return branch;
    }

    public int getField() {
        return field;
    }

    public String getName() {
        return name;
    }
}
