package dev.fly_yeseul.qna.entities.post;

import java.util.Date;

public class CommentEntity {
    private int index;
    private String comment;
    private int postIndex;
    private String userEmail;
    private String userNickname;
    private Date commentAt;
    private int commentLikes;

    public CommentEntity() {

    }

    public CommentEntity(int index, String comment, int postIndex, String userEmail, String userNickname, Date commentAt, int commentLikes) {
        this.index = index;
        this.comment = comment;
        this.postIndex = postIndex;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.commentAt = commentAt;
        this.commentLikes = commentLikes;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(int postIndex) {
        this.postIndex = postIndex;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public Date getCommentAt() {
        return commentAt;
    }

    public void setCommentAt(Date commentAt) {
        this.commentAt = commentAt;
    }

    public int getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(int commentLikes) {
        this.commentLikes = commentLikes;
    }
}
