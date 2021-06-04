package com.groupd.keltis.network.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CheatAccuseEvent extends NetworkEvent{

    public String accuser;

    @Override
    public int getEventID() {
        return 3;
    }

    @Override
    public void encode(DataOutputStream dataOut) throws IOException {
        super.encode(dataOut);
        dataOut.writeUTF(accuser);
    }

    @Override
    public void decode(DataInputStream dataIn) throws IOException {
        super.decode(dataIn);
        accuser = dataIn.readUTF();
    }

    public void setAccuser(String accuser){
        this.accuser = accuser;
    }

    public String getAccuser(){
        return this.accuser;
    }
}
