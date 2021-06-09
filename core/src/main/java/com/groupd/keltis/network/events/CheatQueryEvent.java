package com.groupd.keltis.network.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CheatQueryEvent extends NetworkEvent{

    public String message;


    @Override
    public int getEventID() {
        return 4;
    }

    @Override
    public void encode(DataOutputStream dataOut) throws IOException {
        super.encode(dataOut);
        dataOut.writeUTF(this.message);
    }

    @Override
    public void decode(DataInputStream dataIn) throws IOException {
        super.decode(dataIn);
        this.message = dataIn.readUTF();
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }


}
