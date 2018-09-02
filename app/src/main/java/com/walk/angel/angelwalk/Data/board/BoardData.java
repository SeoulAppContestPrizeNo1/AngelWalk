package com.walk.angel.angelwalk.Data.board;

public class BoardData {
    private int boardIndex;
    private String title;
    private String content;
    private String creatorName;
    private boolean isLike;
    private int isLikeCount;
    private String comment;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
