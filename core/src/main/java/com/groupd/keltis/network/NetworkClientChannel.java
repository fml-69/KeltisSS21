package com.groupd.keltis.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetworkClientChannel {

    public Socket socket;
    public DataInputStream dataIn;
    public DataOutputStream dataOut;


    public NetworkClientChannel(Socket socket) throws IOException {

        this.socket = socket;

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        dataIn = new DataInputStream(in);
        dataOut = new DataOutputStream(out);

    }




}
