package com.walk.angel.angelwalk.retrofit.data;

import com.google.gson.annotations.SerializedName;

public class MessageData {

    @SerializedName("user_id")
    private String id;

    @SerializedName("user_password")
    private String password;

    @SerializedName("user_nickname")
    private String nickName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
