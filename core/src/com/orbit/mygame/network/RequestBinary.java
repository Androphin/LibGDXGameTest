package com.orbit.mygame.network;

import com.orbit.mygame.GameConstants;

public class RequestBinary {
    byte[] paket;

    //6 byte
    byte[] header;
    int protocolVersion; //2 byte
    int paketID; //2 byte
    int paketSize; //2 byte

    //1024 byte max
    byte[] payload;

    public RequestBinary(String type){
        protocolVersion = GameConstants.PROTOCOL_VERSION;

        this.header = new byte[6];

        switch (type){
            case "register":
                paketID = PaketType.REGISTER;
                register();
                break;
        }
    }

    public byte[] register(){

        this.paketSize = payload.length;

        this.paket = this.header;
        return this.paket;
    }

    public byte[] getBytes(){
        return this.paket;
    }
}
