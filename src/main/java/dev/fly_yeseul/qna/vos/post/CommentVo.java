package dev.fly_yeseul.qna.vos.post;

import dev.fly_yeseul.qna.entities.post.CommentEntity;
import dev.fly_yeseul.qna.enums.post.CommentResult;

public class CommentVo extends CommentEntity {
    private CommentResult result;

    public CommentResult getResult() {
        return result;
    }

    public void setResult(CommentResult result) {
        this.result = result;
    }
}
