package dev.fly_yeseul.qna.vos.member;

import dev.fly_yeseul.qna.entities.member.UserEntity;
import dev.fly_yeseul.qna.enums.member.user.EditResult;

public class EditVo extends UserEntity {
    private EditResult result;

    public EditResult getResult() {
        return result;
    }

    public void setResult(EditResult result) {
        this.result = result;
    }
}
