package dev.fly_yeseul.qna.vos.member;

import dev.fly_yeseul.qna.entities.SessionEntity;
import dev.fly_yeseul.qna.entities.member.UserEntity;
import dev.fly_yeseul.qna.enums.member.user.LoginResult;

public class LoginVo extends UserEntity {
    private LoginResult result;
    private SessionEntity sessionEntity;

    public LoginResult getResult() {
        return result;
    }

    public LoginVo setResult(LoginResult result) {
        this.result = result;
        return this;
    }

    public SessionEntity getSessionEntity() {
        return sessionEntity;
    }

    public LoginVo setSessionEntity(SessionEntity sessionEntity) {
        this.sessionEntity = sessionEntity;
        return this;
    }
}
