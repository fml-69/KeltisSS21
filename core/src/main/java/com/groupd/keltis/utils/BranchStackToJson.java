package com.groupd.keltis.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupd.keltis.management.BranchStackStatus;

public class BranchStackToJson {

    public static String convertToJson(BranchStackStatus branchStackStatus){
        String json = "";
        try{
            json = new ObjectMapper().writeValueAsString(branchStackStatus);
        }catch (Exception e){

        }
        return json;
    }

    public static BranchStackStatus convertToObject(String json){
        BranchStackStatus branchStackStatus = new BranchStackStatus("","","","","");
        try {
            branchStackStatus = new ObjectMapper().readValue(json, BranchStackStatus.class);
        } catch (Exception ex) {

        }
        return branchStackStatus;
    }
}