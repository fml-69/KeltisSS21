package com.groupd.keltis.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkClientChannel {

    protected Socket socket;
    protected DataInputStream dataIn;
    protected DataOutputStream dataOut;
    protected String nickName;


    public NetworkClientChannel(Socket socket) throws IOException {

        this.socket = socket;

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        dataIn = new DataInputStream(in);
        dataOut = new DataOutputStream(out);

    }

    public DataInputStream getDataIn() {
        return dataIn;
    }

    public DataOutputStream getDataOut() {
        return dataOut;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }
}
