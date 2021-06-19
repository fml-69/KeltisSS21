package com.groupd.keltis.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupd.keltis.management.BranchStackStatus;
import com.groupd.keltis.management.PlayerMove;

import java.util.ArrayList;

public class JsonConverter {

    public static<T> String convertToJson(T object){
        String json = "";
        try{
            json = new ObjectMapper().writeValueAsString(object);
        }catch (Exception e){
            
        }
        return json;
    }

    public static PlayerMove convertToPlayerMove(String json){
        PlayerMove playermove = new PlayerMove();
        try {
            playermove = new ObjectMapper().readValue(json, PlayerMove.class);
        } catch (Exception ex) {

        }
        return playermove;
    }

    public static BranchStackStatus convertToBranchStackStatus(String json){
        BranchStackStatus branchStackStatus = new BranchStackStatus("","","","","");
        try {
            branchStackStatus = new ObjectMapper().readValue(json, BranchStackStatus.class);
        } catch (Exception ex) {

        }
        return branchStackStatus;
    }

    public static ArrayList convertToArrayList(String json){
        ArrayList arrayList = new ArrayList();
        try {
            arrayList = new ObjectMapper().readValue(json, ArrayList.class);
        } catch (Exception ex) {

        }
        return arrayList;
    }
}