package com.snowfiled.responsedata;

import com.snowfiled.bean.Comment;

import java.util.Date;

public class CommentDetail {
    int commentID;
    Date commentTime;
    String commentText;
    int commentRate;
    int userID;
    String userName;
    String userImg;
    int windowID;

    public CommentDetail(Comment comment, String userName, String userImg) {
        this.commentID = comment.getCommentID();
        this.commentTime = comment.getCommentTime();
        this.commentText = comment.getCommentText();
        this.commentRate = comment.getCommentRate();
        this.userID = comment.getUserID();
        this.userName = userName;
        this.userImg = userImg;
        this.windowID = comment.getWindowID();
    }
}
