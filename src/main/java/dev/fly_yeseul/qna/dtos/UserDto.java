package dev.fly_yeseul.qna.dtos;

import dev.fly_yeseul.qna.entities.member.UserEntity;

public class UserDto extends UserEntity {
    private int postCount;
    private int follower;
    private int following;

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }
}
