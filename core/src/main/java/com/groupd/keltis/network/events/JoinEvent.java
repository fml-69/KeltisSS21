package com.groupd.keltis.network.events;


import com.groupd.keltis.utils.ColorFigures;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class JoinEvent extends NetworkEvent{

    public String nick;
    public ColorFigures playerColor;
    public boolean host;

    public JoinEvent(){

    }

    public JoinEvent(String nick, ColorFigures playerColor, boolean host){
        this.nick = nick;
        this.playerColor = playerColor;
        this.host = host;
    }

    @Override
    public void encode(DataOutputStream dataOut) throws IOException {
        super.encode(dataOut);
        dataOut.writeUTF(nick);
        // send color as number
        dataOut.writeInt(playerColor.ordinal());
        dataOut.writeBoolean(host);
    }

    @Override
    public void decode(DataInputStream dataIn) throws IOException {
        super.decode(dataIn);
        nick = dataIn.readUTF();
        this.playerColor = ColorFigures.values()[dataIn.readInt()];
        this.host = dataIn.readBoolean();
    }

    @Override
    public int getEventID() {
        return 1;
    }



}
