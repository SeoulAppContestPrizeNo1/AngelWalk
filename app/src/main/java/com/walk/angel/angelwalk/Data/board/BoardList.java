package com.walk.angel.angelwalk.Data.board;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BoardList {
    @SerializedName("stat")
    private String result;

    @SerializedName("data")
    private ArrayList<BoardData> arrayListOfBoardData = new ArrayList<>();

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<BoardData> getArrayListOfBoardData() {
        return arrayListOfBoardData;
    }

    public void setArrayListOfBoardData(ArrayList<BoardData> arrayListOfBoardData) {
        this.arrayListOfBoardData = arrayListOfBoardData;
    }
}
