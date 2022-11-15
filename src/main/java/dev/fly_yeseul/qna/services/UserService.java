package dev.fly_yeseul.qna.services;

import dev.fly_yeseul.qna.entities.SessionEntity;
import dev.fly_yeseul.qna.entities.member.UserEntity;
import dev.fly_yeseul.qna.enums.member.user.LoginResult;
import dev.fly_yeseul.qna.enums.member.user.RegisterResult;
import dev.fly_yeseul.qna.mappers.IUserMapper;
import dev.fly_yeseul.qna.utils.CryptoUtil;
import dev.fly_yeseul.qna.vos.member.LoginVo;
import dev.fly_yeseul.qna.vos.member.RegisterVo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service(value = "dev.fly_yeseul.qna.services.UserService")
public class UserService {
    private final IUserMapper userMapper;


    @Autowired
    public UserService(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void expireSession(SessionEntity sessionEntity) {
        sessionEntity.setExpired(true);
        this.userMapper.updateSession(sessionEntity);
    }

    public void extendSession(SessionEntity sessionEntity) {
        sessionEntity.setUpdatedAt(new Date());
        sessionEntity.setExpiresAt(DateUtils.addMinutes(sessionEntity.getUpdatedAt(), 60));
        this.userMapper.updateSession(sessionEntity);
    }

    public SessionEntity getSession(
            String key
    ) {
        return this.userMapper.selectSessionByKey(key);
    }

    public UserEntity getUser(
            String email
    ) {
        return this.userMapper.selectUserByEmail(email);
    }

    public void login(
            LoginVo loginVo,
            HttpServletRequest request
    ) {
        loginVo.setPassword(CryptoUtil.hashSha512(loginVo.getPassword()));
        UserEntity userEntity = this.userMapper.selectUser(loginVo);
        if (userEntity == null) {
            loginVo.setResult(LoginResult.FAILURE);
            return;
        }
        loginVo.setEmail(userEntity.getEmail());
        loginVo.setPassword(userEntity.getPassword());
        loginVo.setName(userEntity.getName());
        loginVo.setNickname(userEntity.getNickname());

        // 세션 만료 처리
        this.userMapper.updateSessionExpiredByEmail(loginVo.getEmail());

        // 세션 처리
        String sessionKey = String.format("%s%s%s%f%f",
                new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()),
                loginVo.getEmail(),
                loginVo.getPassword(),
                Math.random(),
                Math.random());

        String userAgent = request.getHeader("User-Agent");
        sessionKey = CryptoUtil.hashSha512(sessionKey);
        userAgent = CryptoUtil.hashSha512(userAgent);
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setCreatedAt(new Date());
        sessionEntity.setUpdatedAt(new Date());
        sessionEntity.setExpired(false);
        sessionEntity.setExpiresAt(DateUtils.addMinutes(sessionEntity.getCreatedAt(), 60));
        sessionEntity.setUserEmail(loginVo.getEmail());
        sessionEntity.setKey(sessionKey);
        sessionEntity.setUa(userAgent);
        this.userMapper.insertSession(sessionEntity);

        loginVo.setSessionEntity(sessionEntity);
        loginVo.setResult(LoginResult.SUCCESS);
    }

    public void register(
            RegisterVo registerVo
    ) {
        registerVo.setPassword(CryptoUtil.hashSha512(registerVo.getPassword()));
        if (this.userMapper.selectUserCountByEmail(registerVo.getEmail()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE);
            return;
        }
        if (this.userMapper.insertUser(registerVo) == 0) {
            registerVo.setResult(RegisterResult.FAILURE);
        } else {
            registerVo.setResult(RegisterResult.SUCCESS);
        }
    }

    public UserEntity[] getProfiles(){
        return this.userMapper.selectProfile();
    }

    public UserEntity getProfile(int postIndex) {return this.userMapper.selectProfileByPostIndex();}

    public void modifyProfilePhoto(UserEntity userEntity){
        this.userMapper.updateProfilePhoto(userEntity);
    }
}
