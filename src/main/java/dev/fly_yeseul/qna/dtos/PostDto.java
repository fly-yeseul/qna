package dev.fly_yeseul.qna.dtos;

import dev.fly_yeseul.qna.entities.post.PostEntity;

public class PostDto extends PostEntity {
        public byte[] profile;

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }
}
