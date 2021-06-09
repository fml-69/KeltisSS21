package com.groupd.keltis.server;

public class Player {

    public String nick;
    public boolean host;
    public boolean cheat;

    public Player(String nick, boolean host){
        this.nick = nick;
        this.host = host;
    }

    public void setCheat(boolean cheat){
        this.cheat = cheat;
    }

    public boolean getCheat(){
        return this.cheat;
    }

    public String getNick(){
        return this.nick;
    }
}
