package com.walk.angel.angelwalk.Data;

import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("res")
    private String result;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
