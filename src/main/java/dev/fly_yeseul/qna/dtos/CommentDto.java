package dev.fly_yeseul.qna.dtos;

import dev.fly_yeseul.qna.entities.post.CommentEntity;

import java.util.Date;

public class CommentDto extends CommentEntity {
    private int postIndex;


    @Override
    public int getPostIndex() {
        return postIndex;
    }

    @Override
    public void setPostIndex(int postIndex) {
        this.postIndex = postIndex;
    }
}
