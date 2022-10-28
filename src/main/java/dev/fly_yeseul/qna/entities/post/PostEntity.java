package dev.fly_yeseul.qna.entities.post;

import javax.xml.crypto.Data;
import java.util.Date;

public class PostEntity {
    private int index;
    private String userEmail;
    private String userNickname;
    private byte[] photoData;
    private String content;
    private Date postedAt;
    private int likes;

    public PostEntity() {
    }
    public PostEntity(byte[] photoData) {
        this.photoData = photoData;
    }

    public PostEntity(int index, String userEmail, String userNickname, byte[] photoData, String content, Date postedAt, int likes) {
        this.index = index;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.photoData = photoData;
        this.content = content;
        this.postedAt = postedAt;
        this.likes = likes;
    }

    public  int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
