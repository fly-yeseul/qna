package dev.fly_yeseul.qna.dtos;

import dev.fly_yeseul.qna.entities.post.CommentEntity;

import java.util.Date;

public class CommentDto extends CommentEntity {
    public byte[] profile;

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }
}
