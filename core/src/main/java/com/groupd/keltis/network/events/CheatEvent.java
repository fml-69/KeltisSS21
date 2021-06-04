package com.groupd.keltis.network.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CheatEvent extends NetworkEvent{
    public boolean cheat;

    @Override
    public int getEventID() {
        return 5;
    }

    @Override
    public void encode(DataOutputStream dataOut) throws IOException {
        super.encode(dataOut);
        dataOut.writeBoolean(cheat);
    }

    @Override
    public void decode(DataInputStream dataIn) throws IOException {
        super.decode(dataIn);
        cheat = dataIn.readBoolean();
    }

    public void setCheat(boolean cheat){
        this.cheat = cheat;
    }

    public boolean getCheat(){
        return this.cheat;
    }

}
