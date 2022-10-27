package dev.fly_yeseul.qna.vos.post;

import dev.fly_yeseul.qna.entities.post.PostEntity;
import dev.fly_yeseul.qna.enums.post.ReadResult;

public class ReadVo extends PostEntity {
    private ReadResult result;

    public ReadResult getResult() {
        return result;
    }

    public void setResult(ReadResult result) {
        this.result = result;
    }
}
