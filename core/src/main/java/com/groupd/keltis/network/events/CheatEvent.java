package com.groupd.keltis.network.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CheatEvent extends NetworkEvent{
    public boolean cheat;
    public String nick;


    @Override
    public int getEventID() {
        return 5;
    }

    @Override
    public void encode(DataOutputStream dataOut) throws IOException {
        super.encode(dataOut);
        dataOut.writeBoolean(cheat);
        dataOut.writeUTF(nick);
    }

    @Override
    public void decode(DataInputStream dataIn) throws IOException {
        super.decode(dataIn);
        cheat = dataIn.readBoolean();
        nick = dataIn.readUTF();
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

    public void setNick(String nick){
        this.nick = nick;
    }

}
