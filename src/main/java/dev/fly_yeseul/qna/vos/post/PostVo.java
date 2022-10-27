package dev.fly_yeseul.qna.vos.post;

import dev.fly_yeseul.qna.entities.post.PostEntity;
import dev.fly_yeseul.qna.enums.post.PostResult;

public class PostVo extends PostEntity {
    private PostResult result;

    public PostResult getResult() {
        return result;
    }

    public void setResult(PostResult result) {
        this.result = result;
    }
}
