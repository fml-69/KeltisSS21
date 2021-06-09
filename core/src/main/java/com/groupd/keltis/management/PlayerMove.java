package com.groupd.keltis.management;

import com.groupd.keltis.scenes.board.actors.Card;
import com.groupd.keltis.utils.ColorPile;

import java.awt.Color;

public class PlayerMove {
    private String nick;
    private Card card;
    private ColorPile color;

    public PlayerMove(){
    }
    public PlayerMove(String nick, Card card, ColorPile color) {
        this.nick = nick;
        this.card = card;
        this.color = color;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public ColorPile getColor() {
        return color;
    }

    public void setColor(ColorPile color) {
        this.color = color;
    }
}
