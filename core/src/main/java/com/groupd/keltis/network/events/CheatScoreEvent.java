package com.groupd.keltis.network.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CheatScoreEvent extends NetworkEvent{
        public String nick;
        public int score;


        @Override
        public int getEventID() {
            return 9;
        }

        @Override
        public void encode(DataOutputStream dataOut) throws IOException {
            super.encode(dataOut);
            dataOut.writeUTF(nick);
            dataOut.writeInt(score);
        }

        @Override
        public void decode(DataInputStream dataIn) throws IOException {
            super.decode(dataIn);
            nick = dataIn.readUTF();
            score = dataIn.readInt();
        }

        public void setScore(int score){
            this.score = score;
        }

        public int getScore(){
            return this.score;
        }

        public String getNick(){
            return this.nick;
        }

        public void setNick(String nick){
            this.nick = nick;
        }

    }
