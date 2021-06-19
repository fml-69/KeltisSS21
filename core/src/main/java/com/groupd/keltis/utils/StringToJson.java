package com.groupd.keltis.utils;

import com.badlogic.gdx.Gdx;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

public class StringToJson {

    public static String convertToJson(String s){
        String json = "";
        try{
            json = new ObjectMapper().writeValueAsString(s);
        }catch (Exception e){

        }
        Gdx.app.log(json,"hallo");
        return json;
    }

    public static String convertToObject(String json){
        Gdx.app.log(json,"....");
        String s = json;


        return s;
    }
}
