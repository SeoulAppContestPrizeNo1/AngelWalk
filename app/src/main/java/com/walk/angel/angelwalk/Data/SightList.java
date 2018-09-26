package com.walk.angel.angelwalk.Data;

import com.google.gson.annotations.SerializedName;
import com.walk.angel.angelwalk.Data.board.BoardData;

import java.util.ArrayList;

public class SightList {

    @SerializedName("stat")
    private String result;

    @SerializedName("msg")
    private String message;

    @SerializedName("data")
    private ArrayList<SightsData> arrayListOfSightData = new ArrayList<>();

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<SightsData> getArrayListOfSightData() {
        return arrayListOfSightData;
    }

    public void setArrayListOfSightData(ArrayList<SightsData> arrayListOfSightData) {
        this.arrayListOfSightData = arrayListOfSightData;
    }
}
