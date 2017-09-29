package com.example.anas.foodpatrol.model;

import com.google.gson.JsonObject;

import org.json.JSONObject;

/**
 * Created by Anas on 28/09/2017.
 */

public class ResponseModel {
    private Boolean status;
    private String pesan;
    private JsonObject data;

    public Boolean getStatus() {
        return status;
    }

    public String getPesan() {
        return pesan;
    }

    public JsonObject getData() {
        return data;
    }
}

