package com.walk.angel.angelwalk.Data.board;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class BoardData {

    @SerializedName("nickName")
    private String nickName;

    @SerializedName("seq")
    private int boardIndex;

    @SerializedName("title")
    private String title;

    @Nullable
    @SerializedName("content")
    private String content;

    @SerializedName("likeCount")
    private int likeCount;

    @SerializedName("date")
    private String CreateDate;

    @Nullable
    @SerializedName("likeStatus")
    private int likeStatus;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public void setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    public void setContent(@Nullable String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public int getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(int likeStatus) {
        this.likeStatus = likeStatus;
    }
}
