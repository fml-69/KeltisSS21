package com.groupd.keltis.network.events;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TurnEvent extends NetworkEvent {

    private String cardName;
    private String cardColor;
    private int cardNumber;
    private String color;
    private String nick;

    public TurnEvent(){

    }

    public TurnEvent(String nick, String cardName, String cardColor, int cardNumber, String color) {
        this.nick = nick;
        this.cardName = cardName;
        this.cardColor = cardColor;
        this.cardNumber = cardNumber;
        this.color = color;
    }



    @Override
    public int getEventID() {
        return 3;
    }

    @Override
    public void encode(DataOutputStream dataOut) throws IOException {
        super.encode(dataOut);
        dataOut.writeUTF(nick);
        dataOut.writeUTF(cardName);
        dataOut.writeUTF(cardColor);
        dataOut.writeInt(cardNumber);
        dataOut.writeUTF(color);

    }


    @Override
    public void decode(DataInputStream dataIn) throws IOException {
        super.decode(dataIn);
        nick = dataIn.readUTF();
        cardName = dataIn.readUTF();
        cardColor = dataIn.readUTF();
        cardNumber = dataIn.readInt();
        color = dataIn.readUTF();

    }

    public String getCardName() {
        return cardName;
    }

    public String getCardColor() {
        return cardColor;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getColor() {
        return color;
    }

    public String getNick() {
        return nick;
    }

}
