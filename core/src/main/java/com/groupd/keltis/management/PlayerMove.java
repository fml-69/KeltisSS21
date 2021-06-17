package com.groupd.keltis.management;

import com.groupd.keltis.scenes.board.actors.Card;
import com.groupd.keltis.utils.ColorPile;

import java.awt.Color;

public class PlayerMove {
    private String nick;
    private String cardName;
    private String color;

    public PlayerMove(){
    }
    public PlayerMove(String nick, String cardName, String color) {
        this.nick = nick;
        this.cardName = cardName;
        this.color = color;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String card) {
        this.cardName = card;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
