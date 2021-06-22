package com.groupd.keltis.utils;

import com.google.gson.Gson;
import com.groupd.keltis.management.RoadcardsStatus;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RoadcardsToJson {
    public static String convertToJson(ArrayList<RoadcardsStatus> roadcardsArrayList){
        String json = "";
        try{
            Gson gson = new Gson();
            json = gson.toJson(roadcardsArrayList);
        }catch (Exception e){

        }
        return json;
    }

    public static ArrayList<RoadcardsStatus> convertToObject(String json){
        ArrayList<RoadcardsStatus> roadcardsStatusArrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject;
            for(int i = 0;i<jsonArray.length();i++){
                String jsonString = jsonArray.get(i).toString();
                jsonObject = new JSONObject(jsonString);
                String name = jsonObject.getString("name").trim();
                String position = jsonObject.getString("position").trim();
                roadcardsStatusArrayList.add(new RoadcardsStatus(name,position));
            }
        } catch (Exception ex) {

        }
        return roadcardsStatusArrayList;
    }
}
