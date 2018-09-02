package com.walk.angel.angelwalk.Data.board;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BoardData {
    @SerializedName("index")
    private int boardIndex;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("creatorName")
    private String creatorName;

    @SerializedName("isLike")
    private boolean isLike;

    @SerializedName("isLikeCount")
    private int isLikeCount;

    @SerializedName("comments")
    private ArrayList<CommentData> arrayListOfCommentData = new ArrayList<>();

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public int getIsLikeCount() {
        return isLikeCount;
    }

    public void setIsLikeCount(int isLikeCount) {
        this.isLikeCount = isLikeCount;
    }

    public ArrayList<CommentData> getArrayListOfCommentData() {
        return arrayListOfCommentData;
    }

    public void setArrayListOfCommentData(ArrayList<CommentData> arrayListOfCommentData) {
        this.arrayListOfCommentData = arrayListOfCommentData;
    }
}
