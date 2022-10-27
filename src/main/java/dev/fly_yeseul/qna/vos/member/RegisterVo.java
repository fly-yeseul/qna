package dev.fly_yeseul.qna.vos.member;

import dev.fly_yeseul.qna.entities.member.UserEntity;
import dev.fly_yeseul.qna.enums.member.user.RegisterResult;

public class RegisterVo extends UserEntity {
    private RegisterResult result;

    public RegisterResult getResult() {
        return result;
    }

    public RegisterVo setResult(RegisterResult result) {
        this.result = result;
        return this;
    }
}
