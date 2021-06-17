package com.groupd.keltis.management;

public class BranchStackStatus {
    private String green;
    private String yellow;
    private String red;
    private String blue;
    private String purple;

    public BranchStackStatus(String green, String yellow, String red, String blue, String purple){
        this.green = green;
        this.yellow = yellow;
        this.red = red;
        this.blue = blue;
        this.purple = purple;
    }

    public String getGreen() {
        return green;
    }

    public String getYellow() {
        return yellow;
    }

    public String getRed() {
        return red;
    }

    public String getBlue() {
        return blue;
    }

    public String getPurple() {
        return purple;
    }
}
