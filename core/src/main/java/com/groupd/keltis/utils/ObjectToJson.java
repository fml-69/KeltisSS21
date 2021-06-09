package com.groupd.keltis.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupd.keltis.management.PlayerMove;
import com.groupd.keltis.scenes.board.actors.Player;

import java.util.ArrayList;

public class ObjectToJson {

    public static String convertToJson(PlayerMove playerMove){
        String json = "";
        try{
            json = new ObjectMapper().writeValueAsString(playerMove);
        }catch (Exception e){

        }
        return json;
    }

    public static PlayerMove convertToObject(String jsonPlayerMove){
        PlayerMove playermove = new PlayerMove();
        try {
            playermove = new ObjectMapper().readValue(jsonPlayerMove, PlayerMove.class);
        } catch (Exception ex) {

        }
        return playermove;
    }

}