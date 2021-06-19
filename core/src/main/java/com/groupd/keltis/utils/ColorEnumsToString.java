package com.groupd.keltis.utils;

public class ColorEnumsToString {

    static public String getPlayerColor(ColorFigures colorFigures){
        switch (colorFigures){
            case RED:
                return "red";
            case BLUE:
                return "blue";
            case GREEN:
                return "green";
            case YELLOW:
                return "yellow";
            default:
                return "unknown";
        }
    }
    static public ColorFigures getPlayerColor(String colorFigures){
        switch (colorFigures){
            case "red":
                return ColorFigures.RED;
            case "blue":
                return ColorFigures.BLUE;
            case "green":
                return ColorFigures.GREEN;
            case "yellow":
                return ColorFigures.YELLOW;
            default:
                return ColorFigures.UNKNOWN;
        }
    }

    static public String getPileColor(ColorPile colorPile){
        switch (colorPile){
            case RED:
                return "red";
            case BLUE:
                return "blue";
            case GREEN:
                return "green";
            case YELLOW:
                return "yellow";
            case PURPLE:
                return "purple";
            case DISCARD:
                return "discard";
            default:
                return "unknown";
        }
    }
    static public ColorPile getPileColor(String colorPile){
        switch (colorPile){
            case "red":
                return ColorPile.RED;
            case "blue":
                return ColorPile.BLUE;
            case "green":
                return ColorPile.GREEN;
            case "yellow":
                return ColorPile.YELLOW;
            case "purple":
                return ColorPile.PURPLE;
            default:
                return ColorPile.DISCARD;
        }
    }
}
