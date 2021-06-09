package com.groupd.keltis.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupd.keltis.management.PlayerMove;

public class ObjectToJson {

    public static String convertToJson(PlayerMove playerMove){
        String json = "";
        try{
            json = new ObjectMapper().writeValueAsString(playerMove);
        }catch (Exception e){
            
        }
        return json;
    }

    public static PlayerMove convertToObject(String json){
        PlayerMove playermove = new PlayerMove();
        try {
            playermove = new ObjectMapper().readValue(json, PlayerMove.class);
        } catch (Exception ex) {

        }
        return playermove;
    }
}