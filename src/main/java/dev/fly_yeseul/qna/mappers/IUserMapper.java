package dev.fly_yeseul.qna.mappers;

import dev.fly_yeseul.qna.entities.SessionEntity;
import dev.fly_yeseul.qna.entities.member.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserMapper {

    int insertUser(UserEntity userEntity);

    int insertSession(SessionEntity sessionEntity);
    UserEntity selectUser(UserEntity userEntity);


    SessionEntity selectSessionByKey(
            @Param(value = "key") String key
    );

    UserEntity selectUserByEmail(
            @Param(value = "email") String email
    );

    int selectUserCountByEmail(
            @Param(value = "email") String email
    );

    int updateSession(SessionEntity sessionEntity);

    int updateSessionExpiredByEmail (
            @Param(value = "email") String email
    );

    int updateProfilePhoto(UserEntity userEntity);

    UserEntity[] selectProfile();

    UserEntity selectProfileByPostIndex();
}
