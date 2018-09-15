package com.walk.angel.angelwalk.Data.board;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommentData implements Serializable {
    @SerializedName("seq")
    private int commentIndex;

    @SerializedName("content")
    private String content;

    @SerializedName("date")
    private String createDate;

    @SerializedName("nickName")
    private String nickName;

    public int getCommentIndex() {
        return commentIndex;
    }

    public void setCommentIndex(int commentIndex) {
        this.commentIndex = commentIndex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
