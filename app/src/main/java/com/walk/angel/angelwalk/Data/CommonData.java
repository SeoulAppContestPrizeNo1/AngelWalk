package com.walk.angel.angelwalk.Data;

import com.google.gson.annotations.SerializedName;

public class CommonData {

    @SerializedName("stat")
    String result;

    @SerializedName("msg")
    String msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
