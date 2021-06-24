package com.groupd.keltis.cheating;

import com.groupd.keltis.Keltis;
import com.groupd.keltis.scenes.board.actors.Player;
import com.groupd.keltis.utils.ColorFigures;

public class PlayerTestable extends Player {

    public PlayerTestable(Keltis keltis, String nick, ColorFigures color, boolean host) {
        this.keltis = keltis;
        this.nick = nick;
        this.color = color;
    }
}
